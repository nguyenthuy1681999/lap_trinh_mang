/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SinhVien;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ClientControlTCP {
    private Socket mysSocket;
    private String serverHost = "localhost";
    private int serverPort = 8888;

    public ClientControlTCP() {
    }
    public Socket openConnection(){
        try {
            mysSocket = new Socket(serverHost, serverPort);
        } catch (IOException ex) {
            Logger.getLogger(ClientControlTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mysSocket;
    }
    public void guiTenSinhVien(String tencantim){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mysSocket.getOutputStream());
            oos.writeObject(tencantim);
        } catch (IOException ex) {
            Logger.getLogger(ClientControlTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<SinhVien> nhanSinhVien() throws ClassNotFoundException{
        List<SinhVien> listsinhvien = new ArrayList();
        try {
            ObjectInputStream ois = new ObjectInputStream(mysSocket.getInputStream());
            listsinhvien = (List) ois.readObject();
           
        } catch (IOException ex) {
            Logger.getLogger(ClientControlTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
         return listsinhvien;
    }
    public boolean closeConnection(){
        try {
            mysSocket.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ClientControlTCP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
