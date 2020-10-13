package usermanager.control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import usermanager.model.User;
import usermanager.view.LoginView;

public class LoginControl {

    private User model;
    private LoginView view;

    public LoginControl(LoginView view) {
        this.view = view;
        view.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                model = view.getUser();
                if (checkUser(model)) {
                    view.showMessage("Login succesfully!");
                } else {
                    view.showMessage("Invalid username and/or password!");
                }
            } catch (Exception ex) {
                view.showMessage(ex.getStackTrace().toString());
            }
        }
    }

    public boolean checkUser(User user) throws Exception {

        String dbUrl = "jdbc:mysql://localhost:3306/user";
        String dbClass = "com.mysql.cj.jdbc.Driver";
        String user_name = "root";
        String pass= "thuy1608";
        String query = "Select * FROM user.user WHERE username ='"
                + user.getUsername() + "' AND password ='"
                + user.getPassword() + "'";
        try {
            Class.forName(dbClass);
            Connection con = DriverManager.getConnection(dbUrl,user_name,pass);
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
}
