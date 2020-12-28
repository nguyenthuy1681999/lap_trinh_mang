/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udptongab;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Server {
    DatagramSocket myServer = null;
    DatagramPacket receivePacket = null;
    int a,b;
    int tong =0;
    int port = 9900;
    //mở một server socket
    public void openServer(){
        try {
            myServer = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //chấp nhận kết nối xử lý dữ liệu
//    public void listening(){
//        byte[] receiveData = new byte[1024];
//        byte[] sendData = new byte[1024];
//        while (true) {            
//            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
//            try {
//                myServer.receive(receivePacket);   
//     //xử lý dữ liệu
//                a = Integer.parseInt(new String(receivePacket.getData()));
//                b = Integer.parseInt(new String(receivePacket.getData()));
//                int tong = a+b;
//    //đóng gói dữ liệu 
//                InetAddress IpAddress = receivePacket.getAddress();
//                int port = receivePacket.getPort();
//                sendData = Byte.parseByte(string, tong);
//            } catch (IOException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    public void listenning(){
        //nhan du lieu
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData,receiveData.length);
        try {
            myServer.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            DataInputStream dis = new DataInputStream(bais);
            int a = dis.readInt();
            int b = dis.readInt();
            //xu ly
            tong =  a+b;
            //gui du lieu
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(tong);
            dos.flush();
            InetAddress IPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
            myServer.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
     public static void main(String[] args) {
        Server server = new Server();
        server.openServer();
         System.out.println("UDP server running...");
        server.listenning();
    }
     
}
