/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roommanagerMVCthuan.view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import roommanagerMVCthuan.control.RoomDAO;
import roommanagerMVCthuan.model.Room;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class AddRoomFrm extends JFrame implements ActionListener {

    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtType;
    private JTextField txtDisplayPrice;
    private JTextField txtDescription;
    private JButton btnSubmit;
    private JButton btnReset;

    public AddRoomFrm() {
        super("Room management pure-MVC");
        txtID = new JTextField(15);
        txtName = new JTextField(15);
        txtType = new JTextField(15);
        txtDisplayPrice = new JTextField(15);
        txtDescription = new JTextField(15);
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(6, 2));
        content.add(new JLabel("ID: "));
        content.add(txtID);
        content.add(new JLabel("Name:"));
        content.add(txtName);
        content.add(new JLabel("Type:"));
        content.add(txtType);
        content.add(new JLabel("Display price:"));
        content.add(txtDisplayPrice);
        content.add(new JLabel("Description:"));
        content.add(txtDescription);
        content.add(btnReset);
        content.add(btnSubmit);
        btnSubmit.addActionListener(this);
        btnReset.addActionListener(this);
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.equals(btnSubmit)) {
      
            try {
                btnSubmit_actionperformed();
            } catch (SQLException ex) {
                Logger.getLogger(AddRoomFrm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        else if (btn.equals(btnReset)) {
            btnReset_actionperformed();
        }
    }

    private void btnSubmit_actionperformed() throws SQLException {
        Room room = new Room();
        room.setId(txtID.getText());
        room.setName(txtName.getText());
        room.setType(txtType.getText());
        room.setDisplayPrice(Float.parseFloat(txtDisplayPrice.getText()));
        room.setDescription(txtDescription.getText());

        RoomDAO rd = new RoomDAO();
        rd.addRoom(room);
        JOptionPane.showMessageDialog(this, "Add room successfullly!");

    }

    public void btnReset_actionperformed() {
        txtID.setText("");
        txtName.setText("");
        txtType.setText("");
        txtDisplayPrice.setText("");
        txtDescription.setText("");
    }
}

