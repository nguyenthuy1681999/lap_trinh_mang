/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermanager.view;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import usermanager.control.UserDAO;
import usermanager.model.User;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class RegisterView extends JFrame implements ActionListener{
    private JTextField txtID;
    private JTextField txtUsername;
    private JTextField txtYearOfBirth;
    private JPasswordField txtPassword;
    private JPasswordField txtPasswordConfirm;
    private JButton btnRegister;
    private JButton btnReset;
    private User model;

    public RegisterView() {
        super("Register MVC");
        txtID = new JTextField(15);
        txtUsername = new JTextField(15);
        txtYearOfBirth = new JTextField(4);
        txtPassword = new JPasswordField(15);
        txtPasswordConfirm = new JPasswordField(15);
        btnRegister = new JButton("Register");
        btnReset = new JButton("Reset");
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("ID:"));
        content.add(txtID);
        content.add(new JLabel("User Name:"));
        content.add(txtUsername);
        content.add(new JLabel("Year of Birth:"));
        content.add(txtYearOfBirth);
        content.add(new JLabel("Password:"));
        content.add(txtPassword);
        content.add(new JLabel("Confirm password:"));
        content.add(txtPasswordConfirm);
        content.add(btnRegister);
        content.add(btnReset);
        btnRegister.addActionListener(this);
        btnReset.addActionListener(this);
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){ 
                System.exit(0);
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       JButton btn = (JButton) e.getSource();
        if (btn.equals(btnRegister)) {
      
           try {
               btnRegister_actionperformed();
           } catch (Exception ex) {
               Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
           }
        }  
        else if (btn.equals(btnReset)) {
            btnReset_actionperformed();
        }
    }
    
    

    private void btnRegister_actionperformed() throws Exception {
        User user = new User();
        if(txtPasswordConfirm.getText().equalsIgnoreCase(txtPassword.getText())){
        user.setId(txtID.getText());
        user.setUsername(txtUsername.getText());
        user.setYearofbirth(Integer.parseInt(txtYearOfBirth.getText()));
        user.setPassword(txtPassword.getText());
        UserDAO rdao = new UserDAO();
        rdao.addUser(user);
        if(rdao.checkUser(user)) JOptionPane.showMessageDialog(this, "Add user successfullly!");
        else JOptionPane.showMessageDialog(this, "Add user fail!");
        }
        else JOptionPane.showMessageDialog(this, "Password Cofirm false!!!");
    }

    private void btnReset_actionperformed() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtPasswordConfirm.setText("");
    }
}
