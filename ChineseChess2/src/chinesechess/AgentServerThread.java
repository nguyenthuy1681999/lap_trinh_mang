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
	
	Server server;
	Socket socket;

	DataInputStream input;
	DataOutputStream output;
	// xác định kết nối
	boolean connected = true;

	public AgentServerThread(Server server,Socket sc){
		this.server = server;
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
                                // nhận các thông điệp client gửi
				String msg = input.readUTF().trim();
                                
				if(msg.startsWith("LOGIN__PLAYER")){// đăng nhập
					this.login(msg);
				}
				else if(msg.startsWith("CLIENT_LEAVE")){//đăng xuất
					this.clientLeave(msg);
				}
				else if(msg.startsWith("CHALLENGE")){//gửi thách đấu
					this.challenge(msg);
				}
				else if(msg.startsWith("CHALACC")){//chấp nhận thách đấu
					this.acceptChallenge(msg);
				}
				else if(msg.startsWith("CHAREJECT")){//từ chối thách đấu
					this.declineChallenge(msg);
				}
				else if(msg.startsWith("BUSY")){// đối thủ bận
					this.busy(msg);
				}
				else if(msg.startsWith("MOVE")){//a player's move
					this.move(msg);
				}
				else if(msg.startsWith("GIVEUP")){//surrender
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

                        String playerString[]= msg.split("-");
                        String name = playerString[1];
			this.setName(name);
			Vector v = server.players;

			int size = v.size();

			if(!checkPlayer(playerString[1],playerString[2])){
				output.writeUTF("ERROR_LOGIN");
				input.close();
				output.close();
				socket.close();
				connected = false;
			}
			else{
				v.add(this);
				server.refreshPlayers();
				String playerListMsg = "";
				size=v.size();
				// tạo chuỗi gồm danh sách các người chơi online
				for(int i = 0;i < size; ++i){
					AgentServerThread tempSat = (AgentServerThread)v.get(i);
					playerListMsg = playerListMsg+"|"+tempSat.getName();
				}
				playerListMsg = "NICK_LIST"+playerListMsg;
				Vector tempv = server.players; // những client truy cập vào sever
				size = tempv.size();
				//gửi chuỗi danh sách online cho từng clinet
				for(int i = 0; i < size; ++i){  
					AgentServerThread satTemp = (AgentServerThread)tempv.get(i);
					satTemp.output.writeUTF(playerListMsg);
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
                
	}
	
	public void clientLeave(String msg){
		try{
			Vector tempv = server.players;
			tempv.remove(this);
			int size = tempv.size();
			String nl = "NICK_LIST";

			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)tempv.get(i);
				satTemp.output.writeUTF(nl);
			}
			this.connected = false;
			server.refreshPlayers();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void challenge(String msg){
		try{
			String name1 = this.getName();//challenger's name
			String name2 = msg.substring(9);//challenged player's name
			Vector v = server.players;
			int size = v.size();
			//search for the user who is being challenged
			for(int i = 0;i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name2)){
					satTemp.output.writeUTF("CHALLENGE"+name1);
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
			String name = msg.substring(7);//lấy tên người thách đấu
			Vector v = server.players;
			int size = v.size();
			//xác định tên người thách đấu trong danh sách online
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name)){
					satTemp.output.writeUTF("CHALACC");
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
			String name = msg.substring(9);
			Vector v = server.players;
			int size = v.size();
			
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name)){
					satTemp.output.writeUTF("CHAREJECT");
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
			String name = msg.substring(4);
			Vector v = server.players;
			int size = v.size();
			
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);
				if(satTemp.getName().equals(name)){
					satTemp.output.writeUTF("BUSY");
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
			String name = msg.substring(4,msg.length()-4);// lấy tên đối thủ
			Vector v = server.players;
			int size = v.size();
		    //xác định tên người thách đấu trong danh sách online
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
			String name = msg.substring(6);//lấy tên đối thủ
			Vector v = server.players;
			int size = v.size();
                        System.out.println("Nguoi dau hang: "+this.getName());
                        String winner = name;
                        String loser = this.getName();
                        calculateResult(winner, loser);
			// xác định tên đối thủ
			for(int i = 0; i < size; ++i){
				AgentServerThread satTemp = (AgentServerThread)v.get(i);                             
				if(satTemp.getName().equals(name)){
                                        System.out.println("Nguoi thang: "+name);
					satTemp.output.writeUTF("WINNER");
					break;
				}
			}
                        this.output.writeUTF("LOSER");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}