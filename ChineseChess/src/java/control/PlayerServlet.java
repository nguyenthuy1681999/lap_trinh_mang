package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Player;
import model.RankMoveLoss;
import model.RankMoveWin;
import model.Rankplayerscore;


@WebServlet("/")
public class PlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PlayerDAO playerDAO;

    public void init() {
        playerDAO = new PlayerDAO();
        
    }
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            
            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/register":
                    register(request, response);
                    break;
                case "/rankscore":
                    getRankScore(request, response);
                    break;
                case "/rankmovewin":
                    getRankMoveWin(request, response);
                    break;
                case "/rankmoveloss":
                    getRankMoveLoss(request, response);
                    break;
                case "/inforplayer":
                    getInfor(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void login(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException, SQLException {
        String ten = request.getParameter("tentaikhoan");
        String matkhau = request.getParameter("matkhau");
        Player player = new Player();
        player.setTen(ten);
        player.setMatkhau(matkhau);   
        try{
            if (playerDAO.validate(player)) {   
                request.setAttribute("player", player);
                RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                dispatcher.forward(request, response);
            } else {
               RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
         } catch (ClassNotFoundException ex) {
    }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Player playerout = (Player) request.getAttribute("player");
            request.removeAttribute("player");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        
    }

    private void getRankScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rankplayerscore> listrankcore = playerDAO.getRankScore();      
        request.setAttribute("listrankscore",listrankcore);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rankscore.jsp");
        dispatcher.forward(request, response);
    }

    private void getInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       Player player = playerDAO.getInforPlayer(id);
       RequestDispatcher dispatcher = request.getRequestDispatcher("inforplayer.jsp");
       request.setAttribute("player",player);
       dispatcher.forward(request, response);
    }

    private void getRankMoveWin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RankMoveWin> listmovewin = playerDAO.getRankMoveWin();
        request.setAttribute("listrankmovewin",listmovewin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rankmovewin.jsp");
        dispatcher.forward(request, response);
    }

    private void getRankMoveLoss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RankMoveLoss> listmoveloss = playerDAO.getRankMoveLoss();
        request.setAttribute("listrankmoveloss",listmoveloss);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rankmoveloss.jsp");
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ten = request.getParameter("ten");
        String matkhau = request.getParameter("matkhau");
        String matkhaunhaplai = request.getParameter("matkhaunhaplai");
        if(!matkhau.equals(matkhaunhaplai)){
            boolean thongtinsai = true;
            request.setAttribute("thongtinsai", thongtinsai);
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
        else{
        boolean kqdangky = true;
        request.setAttribute("kqdangky",kqdangky);
        playerDAO.register(ten, matkhau);
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);   
        }  
    }
}
    
