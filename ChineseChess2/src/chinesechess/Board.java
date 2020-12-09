package chinesechess;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class Board extends JPanel implements MouseListener{
	
	private int width;//distance between lines
	boolean selected = false;
	int king1x = 4;
	int king1y = 0;
	int king2x = 4;
	int king2y = 9;
	int startX = -1;//start location
	int startY = -1;
	int endX = -1;//end location 
	int endY = -1;
	public ChessPiece pieces[][];
	Client client = null;
	GameRules rules;
	
	//Constructor
	public Board(ChessPiece pieces[][], int width, Client chess){
		this.client = chess;
		this.pieces = pieces;
		this.width = width;
		rules = new GameRules(pieces);
		this.addMouseListener(this);
		this.setBounds(0, 0, 700, 700);
		this.setLayout(null);
	}
	
	public void paint(Graphics g1){
		Graphics2D g = (Graphics2D)g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color c = g.getColor();//save current color
		g.setColor(Client.backGround);
		g.fill3DRect(60,30,580,630,false);
		g.setColor(Color.black);
		//drawing the board 
		for(int i = 80; i <= 620; i = i+60){
			g.drawLine(110,i,590,i);
		}
		g.drawLine(110,80,110,620);
		g.drawLine(590,80,590,620);
		for(int i = 170;i <= 530; i = i+60){
			g.drawLine(i,80,i,320);
			g.drawLine(i,380,i,620);
		}
		g.drawLine(290,80,410,200);
		g.drawLine(290,200,410,80);
		g.drawLine(290,500,410,620);
		g.drawLine(290,620,410,500);
		//place for cannons
		this.smallLine(g,1,2);
		this.smallLine(g,7,2);
		this.smallLine(g,1,7);
		this.smallLine(g,7,7);
		//place for pawn
		this.smallLine(g,0,3);
		this.smallLine(g,2,3);
		this.smallLine(g,4,3);
		this.smallLine(g,6,3);
		this.smallLine(g,8,3);
		this.smallLine(g,0,6);
		this.smallLine(g,2,6);
		this.smallLine(g,4,6);
		this.smallLine(g,6,6);
		this.smallLine(g,8,6);
		g.setColor(Color.black);
//		Font font1 = new Font("����",Font.BOLD,50);//font
//		g.setFont(font1);
		g.drawString("Thi đấu cờ tướng",170,365);
		g.drawString("Đối kháng online",400,365);
		Font font = new Font("����",Font.BOLD,18);
		g.setFont(font);//font
		for(int i = 0;i < 9; ++i){
			for(int j = 0;j < 10; ++j){//pieces
				if(pieces[i][j] != null){
					if(this.pieces[i][j].getSelected() != false){
						g.setColor(Client.selectedBackground);
						g.fillOval(110+i*60-25,80+j*60-25,50,50);
						g.setColor(Client.selectedTextBackground);
					}
					else{
						g.fillOval(110+i*60-25,80+j*60-25,50,50);
						g.setColor(pieces[i][j].getColor());
					}
				    g.drawString(pieces[i][j].getName(),110+i*60-15,80+j*60+10);
				    g.setColor(Color.black);
				}
			}
		}
		g.setColor(c);//revert back to the original color
	}
	
	public void mouseClicked(MouseEvent e){
		if(this.client.myTurn == true){//is it actually the player's turn
			int i = -1;
			int j = -1;
			int[] pos = getPos(e);
			i = pos[0];
			j = pos[1];
			if(i >= 0 && i <= 8 && j >= 0 && j <= 9){//if inside the board
				if(selected == false){//if no piece selected
					this.noFocus(i,j);
				}
				else{//if a piece was selected before
					if(pieces[i][j] != null){//if a piece is present
						if(pieces[i][j].getColor() == pieces[startX][startY].getColor()){//my piece
							//change selection
							pieces[startX][startY].setSelected(false);
							pieces[i][j].setSelected(true);
							startX = i;
							startY = j;
						}
						else{//opponent's piece
							endX = i;
							endY = j;
							String name = pieces[startX][startY].getName();
							//check if this move is valid
							boolean canMove = rules.canMove(startX,startY,endX,endY,name);
							if(canMove){
								try{
									this.client.act.output.writeUTF("MOVE"+ this.client.act.challenger+startX+startY+endX+endY);
									this.client.myTurn = false;
								    if(pieces[endX][endY].getName().equals("T.R") || pieces[endX][endY].getName().equals("T.W")){
								    	this.win();
								    }
								    else{
								    	this.gameNotEnd();
								    }
								}
								catch(Exception ee){
									ee.printStackTrace();
								}
							}
						}
					}
					else{//if no piece is at this location
						endX = i;
						endY = j;
						String name = pieces[startX][startY].getName();
						boolean canMove = rules.canMove(startX,startY,endX,endY,name);
						if(canMove){
							this.noPieces();
						}
					}
				}
			}
			this.client.repaint();
		}
	}
	
	public int[] getPos(MouseEvent e){
		//round to get the nearest board position
		int[] pos = new int[2];
		pos[0] = -1;
		pos[1] = -1;
		Point p = e.getPoint();
		double x = p.getX();
		double y = p.getY();
		if(Math.abs((x-110)/1%60) <= 25){
			pos[0] = Math.round((float)(x-110))/60;
		}
		else if(Math.abs((x-110)/1%60) >= 35){
			pos[0] = Math.round((float)(x-110))/60+1;
		}
		if(Math.abs((y-80)/1%60) <= 25){
			pos[1] = Math.round((float)(y-80))/60;
		}
		else if(Math.abs((y-80)/1%60) >= 35){
			pos[1] = Math.round((float)(y-80))/60+1;
		}
		return pos;
	}
	
	
	public void noFocus(int i,int j){
		if(this.pieces[i][j] != null){
			if(this.client.myColor == 0){
				if(this.pieces[i][j].getColor().equals(Client.redColor)){
					this.pieces[i][j].setSelected(true);
					selected = true;
					startX = i;
					startY = j;
				}
			}
			else{
				if(this.pieces[i][j].getColor().equals(Client.whiteColor)){
					this.pieces[i][j].setSelected(true);
					selected = true;
					startX = i;
		            startY = j;
				}
			}
		}
	}
	
	public void win(){
		pieces[endX][endY] = pieces[startX][startY];//king is dead
		pieces[startX][startY] = null;
		this.client.repaint();//paint the final move
		JOptionPane.showMessageDialog(this.client,"Đã ăn tướng, đối thủ sắp đầu hàng!","Message", JOptionPane.INFORMATION_MESSAGE);           
//                prepare for next games
//		this.client.act.challenger = null;
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
		startX = -1;
		startY = -1;
		endX = -1;
		endY = -1;
		king1x = 4;
		king1y = 0;
		king2x = 4;
		king2y = 9;
		selected=false;
	}
	
	public void gameNotEnd() throws IOException{
		pieces[endX][endY] = pieces[startX][startY];
		pieces[startX][startY] = null;
		pieces[endX][endY].setSelected(false);
		this.client.repaint();//paint the move
		//update king's locations if king moved
		if(pieces[endX][endY].getName().equals("T.R")){
			king1x = endX;
			king1y = endY;
		}
		else if(pieces[endX][endY].getName().equals("T.W")){
			king2x = endX;
			king2y = endY;
		}
		if(king1x == king2x){//game rule
			int count = 0;
			for(int n = king1y+1; n < king2y; ++n){
				if(pieces[king1x][n] != null){
					count++;
					break;
				}
			}
			if(count == 0){//game rule
//		    	JOptionPane.showMessageDialog(this.client,"Bạn thua rồi, đầu hàng đi","Message", JOptionPane.INFORMATION_MESSAGE);
//                        this.client.act.output.writeUTF("LOSER");
		    	//ready for future games
		    	this.client.act.challenger = null;
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
				this.client.surrender.setEnabled(true);
				king1x = 4;
				king1y = 0;
				king2x = 4;
				king2y = 9;
			}
		}
		startX = -1;
		startY = -1;
		endX = -1;
		endY = -1;
		selected=false;
	}
	
	public void noPieces(){
		try{
			this.client.act.output.writeUTF("MOVE"+this.client.act.challenger+startX+startY+endX+endY);
			this.client.myTurn = false;
			pieces[endX][endY] = pieces[startX][startY];
			pieces[startX][startY] = null;
			pieces[endX][endY].setSelected(false);
			this.client.repaint();//paint the move
			
			//update king's information if applicable
			if(pieces[endX][endY].getName().equals("T.R")){
				king1x = endX;
				king1y = endY;
			}
			else if(pieces[endX][endY].getName().equals("T.W")){
				king2x = endX;
				king2y = endY;
			}
			if(king1x == king2x)//losing condition
			{
				int count = 0;
				for(int n = king1y+1; n < king2y; ++n){
					if(pieces[king1x][n] != null){
						count++;
						break;
					}
				}
				if(count == 0){//loose the game
			    	JOptionPane.showMessageDialog(this.client,"Bạn thua rồi, đầu hàng đi!","Message", JOptionPane.INFORMATION_MESSAGE);

//                                this.client.act.output.writeUTF("LOSER");
			    	this.client.act.challenger = null;
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
					this.client.surrender.setEnabled(true);
					king1x = 4;
					king1y = 0;
					king2x = 4;
					king2y = 9;
				}
			}
			startX = -1;
			startY = -1;
			endX = -1;
			endY = -1;
			selected = false;
		}
		catch(Exception ee){
			ee.printStackTrace();
	    }
	}
	
	public void move(int startI,int startJ,int endX,int endY) throws IOException{
		if(pieces[endX][endY] != null && (pieces[endX][endY].getName().equals(".") || pieces[endX][endY].getName().equals("."))){//if king is dead
	    	pieces[endX][endY] = pieces[startI][startJ];
		    pieces[startI][startJ] = null;
		    this.client.repaint();//paint the move
	    	JOptionPane.showMessageDialog(this.client,"Bạn thua rồi, đầu hàng đi ","Message", JOptionPane.INFORMATION_MESSAGE);
	    	this.client.act.challenger = null;
			this.client.myColor = 0;
			this.client.myTurn = false;
			this.client.next();//ready for the next game
			//reset the default properties
			this.client.hostT.setEnabled(false);
			this.client.portT.setEnabled(false);
			this.client.userNameT.setEnabled(false);
			this.client.connect.setEnabled(false);
			this.client.disconnect.setEnabled(true);
			this.client.challenge.setEnabled(false);
			this.client.acceptChallenge.setEnabled(false);
			this.client.declineChallenge.setEnabled(false);
			this.client.surrender.setEnabled(true);
			king1x = 4;
			king1y = 0;
			king2x = 4;
			king2y = 9;
	    }
	    else{//king is not dead
	    	pieces[endX][endY] = pieces[startI][startJ];
		    pieces[startI][startJ] = null;//piece moved
		    this.client.repaint();
		    //if king moved
		    if(pieces[endX][endY].getName().equals("T.R")){
				king1x = endX;
				king1y = endY;
			}
			else if(pieces[endX][endY].getName().equals("T.W")){
				king2x = endX;
				king2y = endY;
			}
		 
			if(king1x == king2x){//if two kings are in the same line
				int count = 0;
				for(int n = king1y+1; n < king2y; ++n){
					if(pieces[king1x][n] != null){
						count++;
						break;
					}
				}
				if(count == 0){
//			    	JOptionPane.showMessageDialog(this.client,"Bạn thắng rồi", "Message",JOptionPane.INFORMATION_MESSAGE);
//                                this.client.act.output.writeUTF("WINNER");
			    	this.client.act.challenger = null;
					this.client.myColor = 0;
					this.client.myTurn = false;
					this.client.next();//ready for the next game
					//reset the default properties
					this.client.hostT.setEnabled(false);
					this.client.portT.setEnabled(false);
					this.client.userNameT.setEnabled(false);
					this.client.connect.setEnabled(false);
					this.client.disconnect.setEnabled(true);
					this.client.challenge.setEnabled(true);
					this.client.acceptChallenge.setEnabled(false);
					this.client.declineChallenge.setEnabled(false);
					this.client.surrender.setEnabled(false);
					king1x = 4;
					king1y = 0;
					king2x = 4;
					king2y = 9;
				}
			}
	    }
		this.client.repaint();
	}
	
	public void mousePressed(MouseEvent e){
	}
	public void mouseReleased(MouseEvent e){
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e){
	}
	
	//used to draw the patterns on the board
	public void smallLine(Graphics2D g,int i,int j){
		int x = 110+60*i;
		int y = 80+60*j;
		if(i > 0){//top left
			g.drawLine(x-3,y-3,x-20,y-3);
			g.drawLine(x-3,y-3,x-3,y-20);
		}
		if(i < 8){//top right
			g.drawLine(x+3,y-3,x+20,y-3);
			g.drawLine(x+3,y-3,x+3,y-20);
		}
		if(i > 0){//bottom left
			g.drawLine(x-3,y+3,x-20,y+3);
			g.drawLine(x-3,y+3,x-3,y+20);
		}
		if(i < 8){//bottom right
			g.drawLine(x+3,y+3,x+20,y+3);
			g.drawLine(x+3,y+3,x+3,y+20);
		}
	}
}
