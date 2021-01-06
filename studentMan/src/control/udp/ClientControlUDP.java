/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SinhVien;
import sun.security.x509.IPAddressName;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ClientControlUDP {
    int serverPort = 9900;
    int clientPort = 6666;
    String serverHost = "localhost";
    DatagramSocket socket = null;

    public ClientControlUDP() {
    }
    public void openConnection(){
        try {
            socket = new DatagramSocket(clientPort);
        } catch (SocketException ex) {
            Logger.getLogger(ClientControlUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void guiTenSinhVien(String tencantim){    
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(tencantim);
            oos.flush();
            InetAddress IPAddress = InetAddress.getByName(serverHost);
            byte[] guidata = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(guidata, guidata.length, IPAddress, clientPort);
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(ClientControlUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<SinhVien> nhanDSSinhVien() throws ClassNotFoundException{    
        List ds = new ArrayList();
        try {
            byte[] nhandata = new byte[1024];
            ByteArrayInputStream bais = new ByteArrayInputStream(nhandata);
            ObjectInputStream ois = new ObjectInputStream(bais);
            ds = (List) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientControlUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
}
