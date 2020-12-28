/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvctongab;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ServerControl {
    private ServerSocket myServer;
    private int serverPort = 8888;
    public ServerControl() throws ClassNotFoundException{
        openServer(serverPort);
        while (true) {            
            listenning();
        }
    }
    private void openServer(int portNumber){
        try {
            myServer = new ServerSocket(portNumber);
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listenning() throws ClassNotFoundException{
        try {
            Socket clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            Object o = ois.readObject();
            if(o instanceof Tongab){
                Tongab t = (Tongab) o;
                int tong  = t.tinhTong();
                dos.writeInt(tong);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
}
