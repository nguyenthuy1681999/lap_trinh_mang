package chinesechess;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Server extends JFrame implements ActionListener{
	
	JLabel portID = new JLabel("Port ID");
	JTextField enterID = new JTextField("1111");//cổng mặc định
	JButton startButton = new JButton("Mở");
	JButton stopButton = new JButton("Đóng");
	JPanel panel = new JPanel();//reserved as the main frame for the components
	JList listOnline = new JList();
	JScrollPane spane = new JScrollPane(listOnline);//displays the list of current players
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spane, panel);
	ServerSocket socket;
	ServerThread serthread;
	Vector players = new Vector();
	
	public Server(){
		this.initialComponent();//thêm các nút
		this.startButton.addActionListener(this);
		this.stopButton.addActionListener(this);
		this.initialFrame();
	}
	
	public void initialComponent(){
		//set vị trí
		panel.setLayout(null);
		portID.setBounds(20, 20, 50, 20);
		panel.add(portID);
		this.enterID.setBounds(85, 20, 60, 20);
		panel.add(this.enterID);
		this.startButton.setBounds(18, 50, 65, 20);
		panel.add(this.startButton);
		this.stopButton.setBounds(85, 50, 55, 20);
		panel.add(this.stopButton);
		this.stopButton.setEnabled(false);
	}
	
	public void initialFrame(){
	    //frame
		this.setTitle("Game Cờ Tướng-Server");
		this.add(splitPane);
		splitPane.setDividerLocation(250);
		splitPane.setDividerSize(4);
		this.setBounds(20, 20, 420, 320);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
					//đóng cửa sổ
					public void windowClosing(WindowEvent e){
						if(serthread == null){//không mở nếu không mở luồng
							System.exit(0);
							return;
						}
						try{// Gửi thông điệp tắt server đến các client khi thoát cửa sổ
							Vector v = players;
							int size = v.size();
							for(int i = 0; i < size; ++i){
								AgentServerThread tempSat = (AgentServerThread)v.get(i);
								tempSat.output.writeUTF("SERVER_DOWN");
							    tempSat.connected = false;
							}
							serthread.connected = false;
							serthread = null;
							socket.close();
							v.clear();
							refreshPlayers();
						}
						catch(Exception ee){
							ee.printStackTrace();
						}
						System.exit(0);
					}
				});
	}
	
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource() == this.startButton){
			//click mở
			this.startActions();
		}
		
		if(event.getSource() == this.stopButton){
			//click đóng
			this.stopActions();
		}
	}
	
	public void startActions(){
		int port = 0;
		try{
			// lấy giá trị cổng
			port = Integer.parseInt(this.enterID.getText().trim());
		}
		catch(Exception ee){
			JOptionPane.showMessageDialog(this, "Cổng phải là số nguyên","Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if((port > 65535) || (port < 0)){
			JOptionPane.showMessageDialog(this, "Giá trị cổng nhập sai","Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		 
		try{
			this.startButton.setEnabled(false);
			this.enterID.setEnabled(false);
			this.stopButton.setEnabled(true);
			socket = new ServerSocket(port);//tạo socket
			serthread = new ServerThread(this);
			serthread.start();//chạy luồng
			JOptionPane.showMessageDialog(this, "Server mở thành công!","Message", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ee){
			JOptionPane.showMessageDialog(this, "Server bị lỗi!","Error!", JOptionPane.ERROR_MESSAGE);
			this.startButton.setEnabled(true);
			this.enterID.setEnabled(true);
			this.stopButton.setEnabled(false);
		}
	}
	
	public void stopActions(){
		try{
			Vector v = players;
			int size = v.size();
			//thông báo khi tắt sever
			for(int i = 0; i < size; ++i){
				AgentServerThread tempSat = (AgentServerThread)v.get(i);
				tempSat.output.writeUTF("SERVER_DOWN");
				tempSat.connected = false;
			}
			//
			serthread.connected = false;
			serthread = null;
			socket.close();
			v.clear();
			refreshPlayers();
			this.startButton.setEnabled(true);
			this.enterID.setEnabled(true);
			this.stopButton.setEnabled(false);
		}catch(Exception ee){
			ee.printStackTrace();
		}
	}
	
	//hiển thị người online
	public void refreshPlayers(){
		//tạo mới danh sách
		Vector v = new Vector();
		int size = this.players.size();
		//ghi danh sách
		for(int i = 0 ; i < size; ++i){
			AgentServerThread tempSat = (AgentServerThread)this.players.get(i);
			String tempstr = tempSat.socket.getInetAddress().toString();
			tempstr = tempstr+"|"+tempSat.getName();
			v.add(tempstr);
		}
		this.listOnline.setListData(v);
	}
	
	public static void main(String[] args){
		new Server();
	}
}
