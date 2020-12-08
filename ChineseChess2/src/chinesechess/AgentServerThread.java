package chinesechess;

import java.util.*;
import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentServerThread extends Thread{
	//the main server who assigned this agent
	Server father;
	Socket socket;
	// data input and output streams
	DataInputStream input;
	DataOutputStream output;
	//thread indicator
	boolean connected = true;
	//Constructor
	public AgentServerThread(Server father,Socket sc){
		this.father = father;
		this.socket = sc;
		try{
			input = new DataInputStream(sc.getInputStream());
			output = new DataOutputStream(sc.getOutputStream());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(connected){
			try{
				String msg = input.readUTF().trim();//take in message from client
                                
				if(msg.startsWith("<#LOGIN__PLAYER#>")){//new user
					this.login(msg);
				}
				else if(msg.startsWith("<#CLIENT_LEAVE#>")){//user leaves
					this.clientLeave(msg);
				}
				else if(msg.startsWith("<#CHALLENGE#>")){//challenge
					this.challenge(msg);
				}
				else if(msg.startsWith("<#CHALACC#>")){//challenge accepted
					this.acceptChallenge(msg);
				}
				else if(msg.startsWith("<#CHAREJECT#>")){//challenge rejected
					this.declineChallenge(msg);
				}
				else if(msg.startsWith("<#BUSY#>")){//other player is busy
					this.busy(msg);
				}
				else if(msg.startsWith("<#MOVE#>")){//a player's move
					this.move(msg);
				}
				else if(msg.startsWith("<#GIVEUP#>")){//surrender
					this.surrender(msg);
				}	
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private Connection getConnection(){
            String jdbcURL = "jdbc:mysql://localhost:3306/co_tuong?com.mysql.cj.jdbc.Driver";
            String jdbcUsername = "root";
            String jdbcPassword = "thuy1608";
              Connection connection = null;
              try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (SQLException e) {
               e.printStackTrace();
                } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
            return connection;
        }
        private boolean checkPlayer(String username, String password) throws Exception{
        boolean status = false;
        String query = "SELECT *FROM co_tuong.tbl_nguoichoi WHERE ten =? AND mat_khau =?;";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
        }
        private void calculateResult(String nameplayerwinner, String nameplayerloser) throws SQLException{
            
            String querylwinner = "UPDATE co_tuong.tbl_nguoichoi\n" +
                                "SET so_tran_thang = so_tran_thang +1 ,diem = diem + 3\n" +
                                "WHERE ten = '"+nameplayerwinner+"';";
            String queryloser = "UPDATE co_tuong.tbl_nguoichoi\n" +
                                "SET so_tran_thua = so_tran_thua +1, diem = diem + 0 \n" +
                                "WHERE ten ='"+nameplayerloser+"';";
        Connection connection1 = getConnection();
            Statement statement1;
         Connection connection2= getConnection();
            Statement statement2; 
            try {
                System.out.println(querylwinner);
                System.out.println(queryloser);
                statement1 = connection1.createStatement();
                statement1.executeUpdate(queryloser);
                statement2 = connection2.createStatement();
                statement2.executeUpdate(querylwinner);
            } catch (SQLException ex) {
                Logger.getLogger(AgentServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }

                 
        }
	public void login(String msg) throws Exception{
		try{
//			String name = msg.substring(13);
                        String playerString[]= msg.split("-");
                        String name = playerString[1];
			this.setName(name);
			Vector v = father.players;
//			boolean nameExists = false;
			int size = v.size();
//			search to see if name already exists
//			for(int i = 0;i < size; ++i){
//				AgentServerThread tempSat = (AgentServerThread)v.get(i);
//				if(tempSat.getName().equals(name)){
//					nameExists = true;
//					break;
//				}
//			}

			if(!checkPlayer(playerString[1],playerString[2])){
				output.writeUTF("<#ERROR_LOGIN#>");
				input.close();
				output.close();
				socket.close();
				connected = false;
			}
			else{
				v.add(this);
				father.refreshPlayers();
				String nickListMsg = "";
				size=v.size();
				//format the concatenated string
				for(int i = 0;i < size; ++i){
					AgentServerThread tempSat = (AgentServerThread)v.get(i);
					nickListMsg = nickListMsg+"|"+tempSat.getName();
				}
				nickListMsg = "<#NICK_LIST#>"+nickListMsg;
				Vector tempv = father.players;
				size = tempv.size();
				//send new user list and online message to everyone
				for(int i = 0; i < size; ++i){
					AgentServerThread satTemp = (AgentServerThread)tempv.get(i);
					satTemp.output.writeUTF(nickListMsg);
					if(satTemp != this){
						satTemp.output.writeUTF("<#MSG#>"+this.getName()+" is online!");
					}
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
                
	}
	
	public void clientLeave(String msg){
		try{
			Vector tempv = father.players;
			tempv.remove(this);
			int size = tempv.size();
			String nl = "<#NICK_LIST#>";
			//send offline message and get refreshed list of players
			
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)tempv.get(i);
				satTemp.output.writeUTF("<#MSG#>"+this.getName()+" is offline!");
				nl = nl+"|"+satTemp.getName();
			}
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)tempv.get(i);
				satTemp.output.writeUTF(nl);
			}
			this.connected = false;
			father.refreshPlayers();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void challenge(String msg){
		try{
			String name1 = this.getName();//challenger's name
			String name2 = msg.substring(13);//challenged player's name
			Vector v = father.players;
			int size = v.size();
			//search for the user who is being challenged
			for(int i = 0;i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name2)){
					satTemp.output.writeUTF("<#CHALLENGE#>"+name1);
					break;
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void acceptChallenge(String msg){
		try{
			String name = msg.substring(11);//challenger's name
			Vector v = father.players;
			int size = v.size();
			//locate the user
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name)){
					satTemp.output.writeUTF("<#CHALACC#>");
					break;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void declineChallenge(String msg){
		try{
			String name = msg.substring(13);//challenger's name
			Vector v = father.players;
			int size = v.size();
			//locate the user
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name)){
					satTemp.output.writeUTF("<#CHAREJECT#>");
					break;
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void busy(String msg){
		try{
			String name = msg.substring(8);//challenger's name
			Vector v = father.players;
			int size = v.size();
			//locate the user
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name)){
					satTemp.output.writeUTF("<#BUSY#>");
					break;
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void move(String msg){
		try{
			String name = msg.substring(8,msg.length()-4);//opponent's name
			Vector v = father.players;
			int size = v.size();
		    //locate opponent
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name)){
					satTemp.output.writeUTF(msg);
					break;
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void surrender(String msg) throws SQLException{
		try{
			String name = msg.substring(10);//opponent's name
			Vector v = father.players;
			int size = v.size();
                        System.out.println("Nguoi dau hang: "+this.getName());
                        String winner = name;
                        String loser = this.getName();
                        calculateResult(winner, loser);
			//locate the user
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);                             
				if(satTemp.getName().equals(name)){
                                        System.out.println("Nguoi thang: "+name);
					satTemp.output.writeUTF("<#WINNER#>");
					break;
				}
			}
                        this.output.writeUTF("<#LOSER#>");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}