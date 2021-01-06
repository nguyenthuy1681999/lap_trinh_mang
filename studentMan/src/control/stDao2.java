/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.protocol.Resultset;
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
public class stDao2 {
    private Connection connection;

    public stDao2() throws SQLException, ClassNotFoundException {
       Class.forName("com.mysql.cj.jdbc.Driver");
       connection = DriverManager.getConnection("jdbc:mysql://localost:3306/db_student"
               +"?useUnicode=true&CharacterEncoding=utf-8","root","phong611");
    }
 
    public List<SinhVien>timKiemSinhVienTheoTen(String tencantim) throws SQLException{
        List listtimkiemtheoten = new ArrayList();
        SinhVien sv = null;
        String sql = "Select *from tblSinhVien where hovaten like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setString(1,tencantim);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {            
            String masv = resultSet.getString("masinhvien");
            String hovaten = resultSet.getString("hovaten");
            Date ngaysinh = resultSet.getDate("ngaysinh");
            String quequan = resultSet.getString("quequan");
            String khoa = resultSet.getString("khoa");
            sv = new SinhVien(masv, hovaten, ngaysinh, quequan, khoa);
            listtimkiemtheoten.add(sv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(stDao2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listtimkiemtheoten;
    }
    public SinhVien timKiemSinhVienTheoMa(String masinhvien) throws SQLException{   
        SinhVien sv = null;
        String sql = "Select *from tblSinhVien where masinhvien = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setString(1,masinhvien);
            ResultSet resultSet = preparedStatement.executeQuery();           
            String masv = resultSet.getString("masinhvien");
            String hovaten = resultSet.getString("hovaten");
            Date ngaysinh = resultSet.getDate("ngaysinh");
            String quequan = resultSet.getString("quequan");
            String khoa = resultSet.getString("khoa");
            sv = new SinhVien(masv, hovaten, ngaysinh, quequan, khoa);
        } catch (SQLException ex) {
            Logger.getLogger(stDao2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sv;
    }
     public boolean suaThongTinSinhVien(SinhVien sinhVien) throws SQLException{
        boolean sinhviensua;
        String sql = "Update from tblSinhVien set masinhvien =?, hovaten =?, ngaysinh =?, quequan =?, khoa =? where masinhvien = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,sinhVien.getMasinhvien());
            preparedStatement.setString(2,sinhVien.getHovaten());
            preparedStatement.setDate(3, (java.sql.Date) sinhVien.getNgaysinh());
            preparedStatement.setString(4,sinhVien.getQuequan());
            preparedStatement.setString(5,sinhVien.getKhoa());
            preparedStatement.setString(6,sinhVien.getMasinhvien());
            sinhviensua = preparedStatement.executeUpdate()>0;
         return sinhviensua;   
    }
     
}
