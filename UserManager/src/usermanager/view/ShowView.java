/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermanager.view;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class ShowView extends JFrame implements ActionListener{
    private JTable tableUser;
    private Button btnShowUser;
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ShowView() {
        super("Show User MVC");
        String column []={"id","name","year of birth"};
        
        //tableUser =  new JTable(tm, column);
        
    }
    
    
}
