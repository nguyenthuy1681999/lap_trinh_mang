package chinesechess;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Server extends JFrame implements ActionListener{
	//graphical user interface setup
	JLabel portID = new JLabel("Port ID");
	JTextField enterID = new JTextField("1111");//default port id number
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
		this.initialComponent();//add the components
		this.startButton.addActionListener(this);
		this.stopButton.addActionListener(this);
		this.initialFrame();
	}
	
	public void initialComponent(){
		//adding components to the panel
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
	    //basic setups
		this.setTitle("Chinese Chess -- Server");
		this.add(splitPane);
		splitPane.setDividerLocation(250);
		splitPane.setDividerSize(4);
		this.setBounds(20, 20, 420, 320);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
					//when window is closed
					public void windowClosing(WindowEvent e){
						if(serthread == null){//if no server, then just exit
							System.exit(0);
							return;
						}
						try{// otherwise tell each user that server window is closed
							Vector v = players;
							int size = v.size();
							for(int i = 0; i < size; ++i){
								AgentServerThread tempSat = (AgentServerThread)v.get(i);
								tempSat.output.writeUTF("<#SERVER_DOWN#>");
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
	
    //wrapper method
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource() == this.startButton){
			//when start button is pressed
			this.startActions();
		}
		
		if(event.getSource() == this.stopButton){
			//when stop button is pressed
			this.stopActions();
		}
	}
	
	public void startActions(){
		int port = 0;
		try{
			// get the value entered in the TextField
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
		//id is valid, server starts
		try{
			this.startButton.setEnabled(false);
			this.enterID.setEnabled(false);
			this.stopButton.setEnabled(true);
			socket = new ServerSocket(port);//identification socket
			serthread = new ServerThread(this);
			serthread.start();//start a thread for server
			JOptionPane.showMessageDialog(this, "Server mở thành công!","Message", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ee){
			JOptionPane.showMessageDialog(this, "Server sta36*+-069**9.030029*"
                                + "\rt bị lỗi!","Error!", JOptionPane.ERROR_MESSAGE);
			this.startButton.setEnabled(true);
			this.enterID.setEnabled(true);
			this.stopButton.setEnabled(false);
		}
	}
	
	public void stopActions(){
		try{
			Vector v = players;
			int size = v.size();
			//notify players of shut down
			for(int i = 0; i < size; ++i){
				AgentServerThread tempSat = (AgentServerThread)v.get(i);
				tempSat.output.writeUTF("<#SERVER_DOWN#>");
				tempSat.connected = false;
			}
			//adjust components and setup
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
	
	//refresh the graphical list of current players
	public void refreshPlayers(){
		//refresh the list of players
		Vector v = new Vector();
		int size = this.players.size();
		//goes through the actual players in the vector
		for(int i = 0 ; i < size; ++i){
			AgentServerThread tempSat = (AgentServerThread)this.players.get(i);
			String tempstr = tempSat.socket.getInetAddress().toString();//formatted string
			tempstr = tempstr+"|"+tempSat.getName();
			v.add(tempstr);
		}
		this.listOnline.setListData(v);
	}
	
	public static void main(String[] args){
		new Server();
	}
}
