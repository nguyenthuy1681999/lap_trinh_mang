/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.rmi;

import control.SinhVienDAO;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.SinhVien;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ServerRMI extends UnicastRemoteObject implements RMIInterface{
    int thisPort = 8888;
    String thisAddress;
    Registry registry;
    @Override
    public List<SinhVien> timSinhVienTheoTen(String tencantim) throws RemoteException {
        SinhVienDAO svdao = null;
        List ds = svdao.getSinhVienByName(tencantim);
        return ds;
    }

    @Override
    public SinhVien timSinhVienTheoMa(String masinhvien) throws RemoteException {
       SinhVienDAO svdao = null;
       SinhVien sv = svdao.getSinhVienByID(masinhvien);
       return sv;
    }

    @Override
    public boolean suaThongTinSinhVienTheoTen(SinhVien sv) throws RemoteException {
        boolean svsua;
        SinhVienDAO svdao = null;
        svsua = svdao.editSinhVien(sv);
        return svsua;
    }

    public ServerRMI() throws RemoteException{
        registry = LocateRegistry.createRegistry(thisPort);
        registry.rebind("rmiServer", this);
    }
    
}
