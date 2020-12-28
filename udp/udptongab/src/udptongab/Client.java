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
public class Client {

    int serverPort = 9900;
    int clientPort = 6666;
    String serverHost = "localhost";
    DatagramSocket myClient;

    public void openClient() {
        try {
            myClient = new DatagramSocket(clientPort);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(int a, int b) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(a);
            dos.writeInt(b);
            InetAddress IPAddress = InetAddress.getByName(serverHost);
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
            myClient.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int receiveData() {
        int kq = 0;
        try {  
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myClient.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            DataInputStream dis = new DataInputStream(bais);
            kq = dis.readInt();
            
        
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public void closeClient() {
        myClient.close();
    }
    public static void main(String[] args) {
        Client  client = new Client();
        client.openClient();
        client.sendData(7, 6);
        System.out.println("Tong la: "+client.receiveData());
        client.closeClient();
    }
}
