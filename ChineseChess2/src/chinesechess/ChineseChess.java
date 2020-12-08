package chinesechess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChineseChess extends JFrame implements ActionListener{
        
	public static final Color backGround = new Color(245,250,160);
	public static final Color selectedBackground = new Color(242,242,242);
	public static final Color selectedTextBackground = new Color(96,95,91);
	public static final Color redColor = new Color(249,183,172);
	public static final Color whiteColor= Color.white;
	//graphical user interface setup
	JLabel hostLabel = new JLabel("HostIP");
	JLabel portLabel = new JLabel("Port");
	JLabel nickName = new JLabel("Tên:");
        JLabel passWord = new JLabel("Mật khẩu:");
        JLabel listOnlineLabel = new JLabel("Danh sách online: ");
	JTextField hostT = new JTextField("127.0.0.1");//default
	JTextField portT = new JTextField("1111");//default
        
        
	JTextField userNameT = new JTextField("Player1");
        JTextField passwordT = new JTextField("***");//default
	JButton connect = new JButton("Đăng nhập");
	JButton disconnect = new JButton("Đăng xuất");
	JButton surrender = new JButton("Đầu hàng");
	JButton challenge = new JButton("Mời chơi");
	JComboBox otherUsersList = new JComboBox();
	JButton acceptChallenge = new JButton("Chấp nhận");
	JButton declineChallenge = new JButton("Từ chối");
	int width = 60;//distance between lines
	ChessPiece[][] chessPieces = new ChessPiece[9][10];
	Board board = new Board(chessPieces, width, this);
	JPanel jpRight = new JPanel();
	JSplitPane spane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board, jpRight);
	 public static Connection con;
	boolean myTurn = false;
	int myColor = 0;//0 is red, 1 is white
	Socket socket;
	AgentclientThread act;
	public ChineseChess(){
		this.initialComponent();//add components
		this.addComponentListener();//add listeners
		this.intialState();
		this.initialPieces();
		this.initialFrame();
	}
	
	public void initialComponent(){
		jpRight.setLayout(null);
		this.hostLabel.setBounds(10,10,50,20);
		jpRight.add(this.hostLabel);
		this.hostT.setBounds(70,10,80,20);
		jpRight.add(this.hostT);
		this.portLabel.setBounds(10,30,50,20);
		jpRight.add(this.portLabel);
		this.portT.setBounds(70,30,80,20);
		jpRight.add(this.portT);
		this.nickName.setBounds(10,60,80,20);
		jpRight.add(this.nickName);    
		this.userNameT.setBounds(10,80,150,20);
		jpRight.add(this.userNameT);
                this.passWord.setBounds(10,100,80,20);
		jpRight.add(this.passWord);
                this.passwordT.setBounds(10,120,150,20);
		jpRight.add(this.passwordT);
		this.connect.setBounds(10,150,100,20);
		jpRight.add(this.connect);
		this.disconnect.setBounds(120,150,100,20);
		jpRight.add(this.disconnect);
                this.listOnlineLabel.setBounds(10,180,150,20);
		jpRight.add(this.listOnlineLabel);
		this.otherUsersList.setBounds(10,210,210,20);
		jpRight.add(this.otherUsersList);
		this.challenge.setBounds(10,240,100,20);
		jpRight.add(this.challenge);
		this.surrender.setBounds(120,240,100,20);
		jpRight.add(this.surrender);
		this.acceptChallenge.setBounds(10,280,100,20);
		jpRight.add(this.acceptChallenge);
		this.declineChallenge.setBounds(120,280,100,20);
		jpRight.add(this.declineChallenge);
		board.setLayout(null);
		board.setBounds(0,0,700,700);
	}
	
	public void addComponentListener(){
		this.connect.addActionListener(this);
		this.disconnect.addActionListener(this);
		this.challenge.addActionListener(this);
		this.surrender.addActionListener(this);
		this.acceptChallenge.addActionListener(this);
		this.declineChallenge.addActionListener(this);
	}
	
	public void intialState(){
		this.disconnect.setEnabled(false);
		this.challenge.setEnabled(false);
		this.acceptChallenge.setEnabled(false);
		this.declineChallenge.setEnabled(false);
		this.surrender.setEnabled(false);
	}
	
	public void initialPieces(){
		chessPieces[0][0] = new ChessPiece(redColor,"xeR",0,0);
		chessPieces[1][0] = new ChessPiece(redColor,"maR",1,0);
		chessPieces[2][0] = new ChessPiece(redColor,"tR",2,0);
		chessPieces[3][0] = new ChessPiece(redColor,"syR",3,0);
		chessPieces[4][0] = new ChessPiece(redColor,"T.R",4,0);
		chessPieces[5][0] = new ChessPiece(redColor,"syR",5,0);
		chessPieces[6][0] = new ChessPiece(redColor,"tR",6,0);
		chessPieces[7][0] = new ChessPiece(redColor,"maR",7,0);
		chessPieces[8][0] = new ChessPiece(redColor,"xeR",8,0);
		chessPieces[1][2] = new ChessPiece(redColor,"phaoR",1,2);
		chessPieces[7][2] = new ChessPiece(redColor,"phaoR",7,2);
		chessPieces[0][3] = new ChessPiece(redColor,"totR",0,3);
		chessPieces[2][3] = new ChessPiece(redColor,"totR",2,3);
		chessPieces[4][3] = new ChessPiece(redColor,"totR",4,3);
		chessPieces[6][3] = new ChessPiece(redColor,"totR",6,3);
		chessPieces[8][3] = new ChessPiece(redColor,"totR",8,3);
		chessPieces[0][9] = new ChessPiece(whiteColor,"xeW",0,9);
		chessPieces[1][9] = new ChessPiece(whiteColor,"maW",1,9);
		chessPieces[2][9] = new ChessPiece(whiteColor,"tW",2,9);
		chessPieces[3][9] = new ChessPiece(whiteColor,"syW",3,9);
		chessPieces[4][9] = new ChessPiece(whiteColor,"T.W",4,9);
		chessPieces[5][9] = new ChessPiece(whiteColor,"syW",5,9);
		chessPieces[6][9] = new ChessPiece(whiteColor,"tW",6,9);
		chessPieces[7][9] = new ChessPiece(whiteColor,"maW",7,9);
		chessPieces[8][9] = new ChessPiece(whiteColor,"xeW",8,9);
		chessPieces[1][7] = new ChessPiece(whiteColor,"phaoW",1,7);
		chessPieces[7][7] = new ChessPiece(whiteColor,"phaoW",7,7);
		chessPieces[0][6] = new ChessPiece(whiteColor,"totW",0,6);
		chessPieces[2][6] = new ChessPiece(whiteColor,"totW",2,6);
		chessPieces[4][6] = new ChessPiece(whiteColor,"totW",4,6);
		chessPieces[6][6] = new ChessPiece(whiteColor,"totW",6,6);
		chessPieces[8][6] = new ChessPiece(whiteColor,"totW",8,6);
	}
	
	public void initialFrame(){
		this.setTitle("Chinese chess!");
		this.add(this.spane);
		spane.setDividerLocation(730);
		spane.setDividerSize(4);
		this.setBounds(30,30,1000,730);
		this.setVisible(true);
		
		this.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					if(act == null){
						System.exit(0);
						return;
					}
					try{
						if(act.challenger != null){//playing with someone
							try{
								//surrender message
								act.output.writeUTF("<#GIVEUP#>"+act.challenger);
							}
							catch(Exception ee){
								ee.printStackTrace();
							}
						}
						act.output.writeUTF("<#CLIENT_LEAVE#>");//leave message
						act.connected = false;//end client
						act = null;	
					}
					catch(Exception ee){
						ee.printStackTrace();
					}
					System.exit(0);
				}
				
			});
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.connect){
                    try {
                        this.connectEvent();
                    } catch (Exception ex) {
                        Logger.getLogger(ChineseChess.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}else if(e.getSource() == this.disconnect){
			this.disconnectEvent();
		}else if(e.getSource() == this.challenge){
			this.challengeEvent();
		}else if(e.getSource() == this.acceptChallenge){
			this.acceptChallengeEvent();
		}else if(e.getSource() == this.declineChallenge){
			this.declineChallengeEvent();
		}else if(e.getSource() == this.surrender){
			this.surrenderEvent();
		}
	}

	public void connectEvent() throws Exception{
		int port = 0;
		try{//get port id
			port=Integer.parseInt(this.portT.getText().trim());
		}catch(Exception ee){//not whole number
			JOptionPane.showMessageDialog(this,"Cổng phải là số nguyên","Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(port > 65535 || port < 0){
			JOptionPane.showMessageDialog(this,"Cổng nhập sai","Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String name = this.userNameT.getText().trim();
                String password = this.passwordT.getText().trim();

		try {
			//adjusting setup
			socket=new Socket(this.hostT.getText().trim(),port);
			act=new AgentclientThread(this);
			act.start();	
			this.hostT.setEnabled(false);
			this.portT.setEnabled(false);
			this.userNameT.setEnabled(false);
                        this.passwordT.setEnabled(false);
			this.connect.setEnabled(false);
			this.disconnect.setEnabled(true);
			this.challenge.setEnabled(true);
			this.acceptChallenge.setEnabled(false);
			this.declineChallenge.setEnabled(false);
			this.surrender.setEnabled(false);
			
		}
		catch(Exception ee){
			JOptionPane.showMessageDialog(this,"Lỗi kết nối!","Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	public void disconnectEvent(){
		try{
			//changing setup
			this.act.output.writeUTF("<#CLIENT_LEAVE#>");//thông điệp đăng xuất
			this.act.connected = false;//kết thúc kết nối
			this.act = null;
			this.hostT.setEnabled(!false);
			this.portT.setEnabled(!false);
			this.userNameT.setEnabled(!false);
                        this.passwordT.setEnabled(false);
			this.connect.setEnabled(!false);
			this.disconnect.setEnabled(!true);
			this.challenge.setEnabled(!true);
			this.acceptChallenge.setEnabled(false);
			this.declineChallenge.setEnabled(false);
			this.surrender.setEnabled(false);
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
	}
	
	public void challengeEvent(){
		//gửi lựa chọn đối thủ
		Object o = this.otherUsersList.getSelectedItem();
		if(o == null || ((String)o).equals("")) {
			JOptionPane.showMessageDialog(this,"Hãy chọn một đối thủ trước","Error!", JOptionPane.ERROR_MESSAGE);
		}
		else{
			String opponent=(String)this.otherUsersList.getSelectedItem();
			try{
				this.hostT.setEnabled(false);
				this.portT.setEnabled(false);
				this.userNameT.setEnabled(false);
                                this.passwordT.setEnabled(false);
				this.connect.setEnabled(false);
				this.disconnect.setEnabled(!true);
				this.challenge.setEnabled(!true);
				this.acceptChallenge.setEnabled(false);
				this.declineChallenge.setEnabled(false);
				this.surrender.setEnabled(false);
				this.act.challenger = opponent;
				this.myTurn = true;
				this.myColor = 0;
				this.act.output.writeUTF("<#CHALLENGE#>"+opponent);
				JOptionPane.showMessageDialog(this,"Đã gửi lời mời","message", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}
	
	public void acceptChallengeEvent(){
		try{	//thông điệp chấp nhận
			this.act.output.writeUTF("<#CHALACC#>"+this.act.challenger);
			this.myTurn = false;
			this.myColor=1;
			this.hostT.setEnabled(false);
			this.portT.setEnabled(false);
			this.userNameT.setEnabled(false);
                        this.passwordT.setEnabled(false);
			this.connect.setEnabled(false);
			this.disconnect.setEnabled(!true);
			this.challenge.setEnabled(!true);
			this.acceptChallenge.setEnabled(false);
			this.declineChallenge.setEnabled(false);
			this.surrender.setEnabled(!false);
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
	}
	
	public void declineChallengeEvent(){
		try{// thông điệp từ chối
			this.act.output.writeUTF("<#CHAREJECT#>"+this.act.challenger);
			this.act.challenger = null;
			this.hostT.setEnabled(false);
			this.portT.setEnabled(false);
			this.userNameT.setEnabled(false);
                        this.passwordT.setEnabled(false);
			this.connect.setEnabled(false);
			this.disconnect.setEnabled(true);
			this.challenge.setEnabled(true);
			this.acceptChallenge.setEnabled(false);
			this.declineChallenge.setEnabled(false);
			this.surrender.setEnabled(false);
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
	}
	
	public void surrenderEvent(){
		try{   //deliver surrender message
			this.act.output.writeUTF("<#GIVEUP#>"+this.act.challenger);
			this.act.challenger = null;
			this.myColor = 0;
			this.myTurn = false;
			this.next();//prepare for next round
			this.hostT.setEnabled(false);
			this.portT.setEnabled(false);
			this.userNameT.setEnabled(false);
                        this.passwordT.setEnabled(false);
			this.connect.setEnabled(false);
			this.disconnect.setEnabled(true);
			this.challenge.setEnabled(true);
			this.acceptChallenge.setEnabled(false);
			this.declineChallenge.setEnabled(false);
			this.surrender.setEnabled(false);
		}
		catch(Exception ee){
			ee.printStackTrace();
		}	
	}
	
	public void next(){
		for(int i = 0;i < 9; ++i){//empty the chess pieces
			for(int j = 0;j < 10; ++j){
				this.chessPieces[i][j] = null;
			}
		}
		this.myTurn = false;
		this.initialPieces();
		this.repaint();
	}
	
	public static void main(String args[]){
		new ChineseChess();
	}

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
