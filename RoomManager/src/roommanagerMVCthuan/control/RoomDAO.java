/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roommanagerMVCthuan.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import roommanagerMVCthuan.model.Room;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class RoomDAO {
    private Connection con;

    public RoomDAO() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/hotel";
        String dbClass = "com.mysql.cj.jdbc.Driver ";   
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl,"root","thuy1608");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void addRoom(Room room){
        String sql = "INSERT INTO tblRoom(id, name, type, displayPrice, description) VALUES(?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, room.getId());
            ps.setString(2, room.getName());
            ps.setString(3, room.getType());
            ps.setFloat(4, room.getDisplayPrice());
            ps.setString(5, room.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        } 
    }
}
