/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcptongab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Client {
    Socket mySocket = null;
    DataOutputStream dos;
    DataInputStream dis;
    public  void connection(){
        try {
            mySocket = new Socket("localhost",9999);
            dos = new DataOutputStream(mySocket.getOutputStream());
            dis = new DataInputStream(mySocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //gui du lieu den server
    public void send(int a, int b){
        if(mySocket!=null && dos!=null){
            try {
                dos.writeInt(a);
                dos.writeInt(b);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //nhan du lieu tra ve tu server
    public int receive(){
        int tong =  0;
        if(mySocket!=null && dis !=null){
     
                try {
                    tong = dis.readInt();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return tong;
    }   
    public  void close() throws IOException{
        if(mySocket != null && dos != null && dis != null){
            dos.close();
            dis.close();
    }
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connection();
        client.send(1, 5);
        System.out.println("tong la: "+client.receive());
        client.close();
    }
}
                               
    

