/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SinhVien;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class StDAO {
    private Connection connection;

    public StDAO() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc://localhost:3306/db_student"
                +"?useUnicode=true&characterEncoding=utf-8","root","phong611");
    }
    //tim kiem theo ten
    public List <SinhVien> timKiemTheoTen(String tencantim){
    List danhsachtheoten = new ArrayList();
    SinhVien sinhVien=null;
    String sql = "Select *from tblSinhvien where hovaten like ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tencantim);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                String masv = resultSet.getString("masinhvien");
                String hovaten = resultSet.getString("hovaten");
                Date ngaysinh = resultSet.getDate("ngaysinh");
                String quequan = resultSet.getString("quequan");
                String khoa = resultSet.getString("khoa");
                sinhVien = new SinhVien(masv, hovaten, ngaysinh, quequan, khoa);
                danhsachtheoten.add(sinhVien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     return danhsachtheoten;
    }
    //tim kiem theo ma sinh vien
    public SinhVien timKiemTheoMaSinhVien(String masinhvien){
    SinhVien sinhVien=null;
    String sql = "Select *from tblSinhvien where masinhvien =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, masinhvien);
            ResultSet resultSet = preparedStatement.executeQuery(); {                
                String masv = resultSet.getString("masinhvien");
                String hovaten = resultSet.getString("hovaten");
                Date ngaysinh = resultSet.getDate("ngaysinh");
                String quequan = resultSet.getString("quequan");
                String khoa = resultSet.getString("khoa");
                sinhVien = new SinhVien(masv, hovaten, ngaysinh, quequan, khoa);
        }
        } catch (SQLException ex) {
            Logger.getLogger(StDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     return sinhVien;
    }
    //sua thong tin sinh vien
    public boolean suaThongTinSinhVien(SinhVien sv) throws SQLException{
    boolean svchinhsua;
    String sql = "Update tblSinhvien set masinhvien =?, hovaten =?, ngaysinh =?, quequan=?, khoa=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, sv.getMasinhvien());
    preparedStatement.setString(2, sv.getHovaten());
    preparedStatement.setDate(3, (java.sql.Date) sv.getNgaysinh());
    preparedStatement.setString(4, sv.getQuequan());
    preparedStatement.setString(5, sv.getKhoa());
    svchinhsua = preparedStatement.executeUpdate() > 0;
    return svchinhsua;
    }
     //them thong tin sinh vien
    public boolean themThongTinSinhVien(SinhVien sv) throws SQLException{
    boolean sinhvienthem;
    String sql = "Insert into tblSinhvien(masinhvien, hovaten, ngaysinh,quequan,khoa) values (?,?,?,?,?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, sv.getMasinhvien());
    preparedStatement.setString(2, sv.getHovaten());
    preparedStatement.setDate(3, (java.sql.Date) sv.getNgaysinh());
    preparedStatement.setString(4, sv.getQuequan());
    preparedStatement.setString(5, sv.getKhoa());
    sinhvienthem = preparedStatement.executeUpdate() > 0;
    return sinhvienthem;
    }
    //xoa thong tin sinh vien
    public boolean xoaThongTinSinhVien(String masinhvien) throws SQLException{
    boolean xoasinhvien;
    String sql = "Delete *from tblSinhvien where masinhvien =?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, masinhvien);
    xoasinhvien = preparedStatement.executeUpdate() > 0;
    return xoasinhvien;
    }
}
