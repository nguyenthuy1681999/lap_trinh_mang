/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermanager.model;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class User {
    private String id;
    private String username;
    private String password;
    private int yearofbirth;
    public User() {
    }

    public User(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYearofbirth() {
        return yearofbirth;
    }

    public void setYearofbirth(int yearofbirth) {
        this.yearofbirth = yearofbirth;
    }

    public String getUsername() {
        return username;
    }

    public User(String id, String username, String password, int yearofbirth) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.yearofbirth = yearofbirth;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
