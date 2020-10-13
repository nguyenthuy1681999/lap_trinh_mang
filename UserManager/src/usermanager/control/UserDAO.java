/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermanager.control;

/**
 *
 * @author Nguyen Thu Thuy 1608
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import usermanager.model.User;
import usermanager.view.LoginView;
import usermanager.view.RegisterView;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class UserDAO{
     private Connection con;
   

    public UserDAO() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/user";
        String dbClass = "com.mysql.cj.jdbc.Driver";   
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl,"root","thuy1608");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public boolean checkUser(User user) throws Exception {
        String query = "Select * FROM user.user WHERE username ='"
                + user.getUsername() + "' AND password ='"
                + user.getPassword() + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
            con.close();
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
    public void addUser(User user){
        String query_insert = "INSERT INTO user.user (id, username, password, yearofbirth) VALUES(?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query_insert);
            ps.setString(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword()); 
            ps.setInt(4, user.getYearofbirth());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    public ArrayList showUser(User user) throws SQLException{
        String query_select = "SELECT *FROM user.user";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query_select);
        ArrayList<User> userList = new ArrayList<>();
        while (rs.next()) {            
            String id = rs.getString(1);
            String name = rs.getString(2);
            int year = rs.getInt(3);
            String pass = rs.getString(4);
            User u = new User(id, name, pass, year);
            userList.add(u);
        }
       return userList;
    } 
}

  


