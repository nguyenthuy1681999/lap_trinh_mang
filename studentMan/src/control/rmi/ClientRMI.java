/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SinhVien;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ClientRMI {
   RMIInterface rmii;
   Registry registry;
   public ClientRMI(String address, int port) throws NotBoundException{
       try {
           registry = LocateRegistry.getRegistry(address,port);
           rmii = (RMIInterface) registry.lookup("rmiServer");
       } catch (RemoteException ex) {
           Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
       }     
   }
   public List<SinhVien> kqTimKiemTheoTen(String tencantim) throws RemoteException{
    List ds = rmii.timSinhVienTheoTen(tencantim);
    return ds;
   }
   public SinhVien kqTimKiemTheoMa(String masinhvien) throws RemoteException{
    SinhVien sv = rmii.timSinhVienTheoMa(masinhvien);
    return sv;
   }
   public boolean kqSuaSinhVien(SinhVien sv) throws RemoteException{
    boolean svsua;
    svsua = rmii.suaThongTinSinhVienTheoTen(sv);
    return svsua;
   }
}
