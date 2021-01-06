/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.udp;

import control.SinhVienDAO;
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

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ServerControlUDP {
    //dung de giao tiep

    DatagramSocket socket = null;
    //gui va nhan du lieu dc dong goi duoi dang byte
    DatagramPacket packet = null;
    //cong
    int port = 9900;

    public ServerControlUDP() throws ClassNotFoundException {
        openServer();
        while (true) {            
            listening();
        }
    }

    public void openServer() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ServerControlUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listening() throws ClassNotFoundException {
        //xác định dung luong byte nhan

        try {
            byte[] nhanData = new byte[1024];
            packet = new DatagramPacket(nhanData, nhanData.length);
            //luong nhan goi du lieu
            socket.receive(packet);
            // nhan du lieu
            ByteArrayInputStream bais = new ByteArrayInputStream(nhanData);
            //chuyen tu byte thanh object
            ObjectInputStream ois = new ObjectInputStream(bais);
            SinhVien sv = (SinhVien) ois.readObject();
            SinhVienDAO sinhVienDAO = null;
            List danhsach = new ArrayList();
            danhsach = sinhVienDAO.getSinhVienByName(sv.getHovaten());
            //gui du lieu
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(danhsach);
            oos.flush();
            //lay dia chi ip, port gui
            InetAddress IPAddress = packet.getAddress();
            int clientPort = packet.getPort();
            //chuyen ve kieu byte
            byte[] guidata = baos.toByteArray();
            DatagramPacket dp = new DatagramPacket(guidata, guidata.length, IPAddress, clientPort);
            //luong nhan       
            socket.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(ServerControlUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
