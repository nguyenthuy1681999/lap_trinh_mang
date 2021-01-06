/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.tcp;

import control.SinhVienDAO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
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
public class ServerControlTCP {
    private ServerSocket myServer;
    private int serverPort = 8888;
    
    public ServerControlTCP() {
        try {
            openServer(serverPort);
            while (true) {                
               listening();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerControlTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void openServer(int portnumber) throws IOException{
        myServer = new ServerSocket(portnumber);
    }
    public void listening(){
        try {
            Socket clientSocket = myServer.accept();
            //nhan chuoi ten sinh vien tim
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            String tensinhvien = ois.readUTF();
            SinhVienDAO sinhVienDAO = null;
            List listsinhvientheoten = new ArrayList();
            listsinhvientheoten = sinhVienDAO.getSinhVienByName(tensinhvien);
            //gui danh sach sinh vien
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(listsinhvientheoten);
        } catch (IOException ex) {
            Logger.getLogger(ServerControlTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
