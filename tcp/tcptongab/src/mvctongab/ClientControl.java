/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvctongab;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ClientControl {
   private Socket mysSocket;
   private String serverHost = "localhost";
   private int serverPort = 8888;

    public ClientControl() {
    }
   public Socket openConnection(){
       try {
           mysSocket = new Socket(serverHost, serverPort);
       } catch (IOException ex) {
           Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
       }
      return mysSocket;
   }
   public void sendData(Tongab tongab){
       ObjectOutputStream oos;
       try {
           oos = new ObjectOutputStream(mysSocket.getOutputStream());
           oos.writeObject(tongab);
       } catch (IOException ex) {   
           Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);        
       }
   }
   public int receiveData() throws ClassNotFoundException{
       int tong = 0;
       DataInputStream dis;
       try {
           dis = new DataInputStream(mysSocket.getInputStream());
           tong = dis.readInt();
       } catch (IOException ex) {
           Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return tong;
   }
   public boolean closeConnection(){
       try {
           mysSocket.close();
       } catch (IOException ex) {
           Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
       return true;
   }
}
