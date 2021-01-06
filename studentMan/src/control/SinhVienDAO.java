/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SinhVien;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class SinhVienDAO {
    private Connection connection;
    
    public SinhVienDAO() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentman"
                    + "?useUnicode=true&characterEncoding=utf-8","root","thuy1608");
    }
    public List<SinhVien> getSinhVienByName(String tensinhvien){
        SinhVien sinhVien = null;
        List listsinhvien = new ArrayList();
        String sql = "SELECT * FROM studentman.tblsinhvien WHERE masinhvien =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tensinhvien);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
            String masv = resultSet.getString("masinhvien");
            String hovaten = resultSet.getString("hovaten");
            Date ngaysinh = resultSet.getDate("ngaysinh");
            String quequan = resultSet.getString("quequan");
            String khoa = resultSet.getString("khoa");
            sinhVien = new SinhVien(masv, hovaten, ngaysinh, quequan, khoa);
            listsinhvien.add(sinhVien);
            }       
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listsinhvien;
    }
    public boolean editSinhVien(SinhVien sinhVien){
        boolean sinhvienupdate = false;
        String sql2 = "UPDATE tblsinhvien set hovaten =?, ngaysinh =?, quequan =?, khoa =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, sinhVien.getHovaten());
            preparedStatement.setDate(2, (Date) sinhVien.getNgaysinh());
            preparedStatement.setString(3, sinhVien.getQuequan());
            preparedStatement.setString(3, sinhVien.getKhoa());
            sinhvienupdate = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sinhvienupdate;
    }
    public SinhVien getSinhVienByID(String masinhvien){
        SinhVien sinhVien = null;
        String sql = "SELECT * FROM studentman.tblsinhvien WHERE masinhvien =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, masinhvien);
            ResultSet resultSet = preparedStatement.executeQuery();              
            String masv = resultSet.getString("masinhvien");
            String hovaten = resultSet.getString("hovaten");
            Date ngaysinh = resultSet.getDate("ngaysinh");
            String quequan = resultSet.getString("quequan");
            String khoa = resultSet.getString("khoa");
            sinhVien = new SinhVien(masv, hovaten, ngaysinh, quequan, khoa);
                
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sinhVien;
    }
}
