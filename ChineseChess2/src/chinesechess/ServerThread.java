package chinesechess;

import java.net.*;

public class ServerThread extends Thread {
	
	Server mainServer;
	ServerSocket socket;
	boolean connected = true;
	
	public ServerThread(Server mainServer){
		this.mainServer = mainServer;
		socket = mainServer.socket;
	}
	
	public void run(){
		while(connected){
			try{
				// Chờ yêu cầu chấp nhận từ client
				Socket soc = socket.accept();
				AgentServerThread sat = new AgentServerThread(mainServer, soc);
				sat.start();// khởi tạo luồng AgentServerThread
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
