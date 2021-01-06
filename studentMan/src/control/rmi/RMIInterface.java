/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.rmi;

import java.rmi.RemoteException;
import java.util.List;
import model.SinhVien;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public interface RMIInterface {
    public List <SinhVien>timSinhVienTheoTen(String tencantim) throws RemoteException;
    public SinhVien timSinhVienTheoMa(String masinhvien) throws RemoteException;
    public boolean suaThongTinSinhVienTheoTen(SinhVien sv) throws RemoteException;
}
