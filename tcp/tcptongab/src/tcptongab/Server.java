/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcptongab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    ServerSocket myServer = null;
    int a,b;
    int tong;
    DataInputStream dis;
    DataOutputStream dos;
    PrintStream os;
    Socket clientSocket = null;
    //mo mot socket
    public void openServer(){
        try {
            myServer = new ServerSocket(9999);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //chap nhan ket noi va xu li du lieu
    public void listening(){
        try {
            clientSocket = myServer.accept();
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
            //doc du lieu dau vao
            while (true) {   
                a = dis.readInt();
                b = dis.readInt();
                //xu ly
                tong = a + b;
                //gui du lieu
                dos.writeInt(tong);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Server  server = new Server();
        System.out.println("tcp running...");
        server.openServer();
        server.listening();
    }
    
}
