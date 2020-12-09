package chinesechess;

import javax.swing.*;
import java.util.*;
import java.io.*;

public class AgentClientThread extends Thread{
	
	Client client;
	boolean connected = true;
	DataInputStream input;
	DataOutputStream output;
	String challenger=null;

	public AgentClientThread(Client client){
		this.client = client;
		try{
			input = new DataInputStream(client.socket.getInputStream());
			output = new DataOutputStream(client.socket.getOutputStream());
			String name = client.userNameT.getText().trim();
                        String password = client.passwordT.getText().trim();
                        // them doi tuong player de gui
                        
			output.writeUTF("LOGIN__PLAYER"+"-"+name+"-"+password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(connected){
			try{
                                // nhận các thông điệp bên server gửi sang
				String msg = input.readUTF().trim();
				if(msg.startsWith("ERROR_LOGIN")){ // lỗi đăng nhập do sai tên hoặc mật khẩu
					this.errorLogin();
				}
				else
                                if(msg.startsWith("NICK_LIST")){ // danh sách nick đang online
					this.listOnline(msg);
				}
				else if(msg.startsWith("SERVER_DOWN")){ // đóng server
					this.serverDown();
				}
				else if(msg.startsWith("CHALLENGE")){ // gửi thách đấu
					this.challenge(msg);
				}
				else if(msg.startsWith("CHALACC")){ // chấp nhận thách đấu
					this.accept();	
				}
				else if(msg.startsWith("CHAREJECT")){ // từ chối thách đấu
					this.decline();
				}
				else if(msg.startsWith("BUSY")){ // đối thủ đang chơi
					this.busy();
				}
				else if(msg.startsWith("MOVE")){ // di chuyên
					this.movePiece(msg);
				}
				else if(msg.startsWith("WINNER")){ // người thắng
					this.alertwinner();
				}
                                else if(msg.startsWith("LOSER")){ // người thua
					this.alertloser();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void errorLogin(){
		try{
			JOptionPane.showMessageDialog(this.client,"Sai tên đăng nhập hoặc mật khẩu!", "Error!",JOptionPane.ERROR_MESSAGE);
			input.close();
			output.close();
			this.client.hostT.setEnabled(!false);
			this.client.portT.setEnabled(!false);
			this.client.userNameT.setEnabled(!false);
                        this.client.passwordT.setEnabled(false);
			this.client.connect.setEnabled(!false);
			this.client.disconnect.setEnabled(!true);
			this.client.challenge.setEnabled(!true);
			this.client.acceptChallenge.setEnabled(false);
			this.client.declineChallenge.setEnabled(false);
			this.client.surrender.setEnabled(false);
			client.socket.close();
			client.socket = null;
			client.act = null;
			connected = false;
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void listOnline(String msg){
		String s = msg.substring(9);
		String[] na = s.split("\\|");
		Vector v = new Vector();
		//tạo list online
		for(int i = 0; i < na.length; ++i){
			if((na[i].trim().length() != 0) && (!na[i].trim().equals(client.userNameT.getText().trim()))){
				v.add(na[i]);
			}
		}
		client.otherPlayersList.setModel(new DefaultComboBoxModel(v));
	}
	
	public void serverDown(){
		this.client.hostT.setEnabled(!false);
		this.client.portT.setEnabled(!false);
		this.client.userNameT.setEnabled(!false);
                this.client.passwordT.setEnabled(false);
		this.client.connect.setEnabled(!false);
		this.client.disconnect.setEnabled(!true);
		this.client.challenge.setEnabled(!true);
		this.client.acceptChallenge.setEnabled(false);
		this.client.declineChallenge.setEnabled(false);
		this.client.surrender.setEnabled(false);
		this.connected = false;
		client.act = null;
		JOptionPane.showMessageDialog(this.client,"Tạm dừng Server!","Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void challenge(String msg){
		try{
			String name = msg.substring(9);
			if(this.challenger == null){//người chơi đang không chơi với ai
				challenger = msg.substring(9);
				this.client.hostT.setEnabled(false);
				this.client.portT.setEnabled(false);
				this.client.userNameT.setEnabled(false);
				this.client.connect.setEnabled(false);
				this.client.disconnect.setEnabled(!true);
				this.client.challenge.setEnabled(!true);
				this.client.acceptChallenge.setEnabled(!false);
				this.client.declineChallenge.setEnabled(!false);
				this.client.surrender.setEnabled(false);
				JOptionPane.showMessageDialog(this.client,challenger+" đã mời bạn!", "Message",JOptionPane.INFORMATION_MESSAGE);
			}
			else{//nếu đã chơi
				this.output.writeUTF("BUSY"+name);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void accept(){
		this.client.hostT.setEnabled(false);
		this.client.portT.setEnabled(false);
		this.client.userNameT.setEnabled(false);
		this.client.connect.setEnabled(false);
		this.client.disconnect.setEnabled(!true);
		this.client.challenge.setEnabled(!true);
		this.client.acceptChallenge.setEnabled(false);
		this.client.declineChallenge.setEnabled(false);
		this.client.surrender.setEnabled(!false);
		JOptionPane.showMessageDialog(this.client,"Lời mời đã được chấp nhận! Quân đỏ đi trước!", "Message",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void decline(){
		this.client.myTurn = false;
		this.client.myColor = 0;
		this.client.hostT.setEnabled(false);
		this.client.portT.setEnabled(false);
		this.client.userNameT.setEnabled(false);
		this.client.connect.setEnabled(false);
		this.client.disconnect.setEnabled(true);
		this.client.challenge.setEnabled(true);
		this.client.acceptChallenge.setEnabled(false);
		this.client.declineChallenge.setEnabled(false);
		this.client.surrender.setEnabled(false);
		JOptionPane.showMessageDialog(this.client,"Lời mời của bạn đã bị từ chối!","Message", JOptionPane.INFORMATION_MESSAGE);
		this.challenger = null;
	}
	
	public void busy(){
		this.client.myTurn = false;
		this.client.myColor = 0;
		this.client.hostT.setEnabled(false);
		this.client.portT.setEnabled(false);
		this.client.userNameT.setEnabled(false);
		this.client.connect.setEnabled(false);
		this.client.disconnect.setEnabled(true);
		this.client.challenge.setEnabled(true);
		this.client.acceptChallenge.setEnabled(false);
		this.client.declineChallenge.setEnabled(false);
		this.client.surrender.setEnabled(false);
		JOptionPane.showMessageDialog(this.client,"Đối thủ đang bận!","Message", JOptionPane.INFORMATION_MESSAGE);
		this.challenger = null;
	}
	
	public void movePiece(String msg) throws IOException{
		int length = msg.length();
		int startI = Integer.parseInt(msg.substring(length-4,length-3));
		int startJ = Integer.parseInt(msg.substring(length-3,length-2));
		int endI = Integer.parseInt(msg.substring(length-2,length-1));
		int endJ = Integer.parseInt(msg.substring(length-1));
		this.client.board.move(startI,startJ,endI,endJ);
		this.client.myTurn = true;
	}
	
	public void alertwinner(){
		JOptionPane.showMessageDialog(this.client,"Bạn thắng! Đối thủ đã đầu hàng +1đ","Message", JOptionPane.INFORMATION_MESSAGE);
		this.challenger = null;
		this.client.myColor = 0;
		this.client.myTurn = false;
		this.client.next();
		this.client.hostT.setEnabled(false);
		this.client.portT.setEnabled(false);
		this.client.userNameT.setEnabled(false);
		this.client.connect.setEnabled(false);
		this.client.disconnect.setEnabled(true);
		this.client.challenge.setEnabled(true);
		this.client.acceptChallenge.setEnabled(false);
		this.client.declineChallenge.setEnabled(false);
		this.client.surrender.setEnabled(false);
	}
	public void alertloser(){
		JOptionPane.showMessageDialog(this.client,"Bạn thua! Không được cộng điểm ","Message", JOptionPane.INFORMATION_MESSAGE);
		this.challenger = null;
		this.client.myColor = 0;
		this.client.myTurn = false;
		this.client.next();
		this.client.hostT.setEnabled(false);
		this.client.portT.setEnabled(false);
		this.client.userNameT.setEnabled(false);
		this.client.connect.setEnabled(false);
		this.client.disconnect.setEnabled(true);
		this.client.challenge.setEnabled(true);
		this.client.acceptChallenge.setEnabled(false);
		this.client.declineChallenge.setEnabled(false);
		this.client.surrender.setEnabled(false);
	}
	
}
