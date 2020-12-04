package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.RankMoveLoss;
import model.RankMoveWin;
import model.Rankplayerscore;

public class PlayerDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/co_tuong?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "thuy1608";
    private static final String CHECK = "select * from tbl_nguoichoi where ten = ? and mat_khau = ? ";
    private static final String GET_RANK_CORE = "SElECT id, ten, diem, RANK() OVER(ORDER BY diem DESC) rank_core FROM tbl_nguoichoi;";
    private static final String GET_INFOR_PLAYER = "SElECT *FROM tbl_nguoichoi WHERE id=?;";
    private static final String INSERT_PLAYER = "INSERT INTO tbl_nguoichoi(ten, mat_khau) VALUES(?,?);";
    private static final String GET_RANK_MOVE_WIN = " SELECT id, ten,so_tran_thang,\n"
            + "nuoc_di_thang,\n"
            + "(nuoc_di_thang/so_tran_thang) AS trung_binh_nuoc_di_thang,\n"
            + "RANK() OVER(ORDER BY (nuoc_di_thang/so_tran_thang) ASC) rank_move_win\n"
            + "FROM tbl_nguoichoi;";
     private static final String GET_RANK_MOVE_LOSS="SELECT id,ten,so_tran_thua,\n" +
"	   nuoc_di_thua,\n" +
"       (nuoc_di_thua/so_tran_thua) AS trung_binh_nuoc_di_thua,\n" +
"       RANK() OVER(ORDER BY (nuoc_di_thua/so_tran_thua) DESC) rank_move_loss\n" +
"       FROM tbl_nguoichoi;  ";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean validate(Player player) throws ClassNotFoundException, SQLException {
        boolean status = false;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK);) {
            preparedStatement.setString(1, player.getTen());
            preparedStatement.setString(2, player.getMatkhau());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }
    public void register(String ten, String matkhau){
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYER);) {
            preparedStatement.setString(1, ten);
            preparedStatement.setString(2, matkhau);
            System.out.println(preparedStatement);
             int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public List<Rankplayerscore> getRankScore() {
        List<Rankplayerscore> listplayercore = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_RANK_CORE);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                float diem = rs.getFloat("diem");
                int rank = rs.getInt("rank_core");
                listplayercore.add(new Rankplayerscore(id, ten, diem, rank));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listplayercore;
    }

    public List<RankMoveWin> getRankMoveWin() {
        List<RankMoveWin> listmovewin = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_RANK_MOVE_WIN);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int sotranthang = rs.getInt("so_tran_thang");
                int sonuocdithang = rs.getInt("nuoc_di_thang");
                float trungbinhnuocdithang = rs.getInt("trung_binh_nuoc_di_thang");
                int rank = rs.getInt("rank_move_win");
                listmovewin.add(new RankMoveWin(id, ten, sotranthang, sonuocdithang, trungbinhnuocdithang, rank));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listmovewin;
    }
    public List<RankMoveLoss> getRankMoveLoss() {
        List<RankMoveLoss> listmoveloss = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_RANK_MOVE_LOSS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int sotranthua = rs.getInt("so_tran_thua");
                int sonuocdithua = rs.getInt("nuoc_di_thua");
                float trungbinhnuocdithua = rs.getInt("trung_binh_nuoc_di_thua");
                int rank = rs.getInt("rank_move_loss");
                listmoveloss.add(new RankMoveLoss(id, ten, sotranthua, sonuocdithua, trungbinhnuocdithua, rank));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listmoveloss;
    }
    public Player getInforPlayer(int id) {
        Player player = null;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_INFOR_PLAYER);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String ten = rs.getString("ten");
                String matkhau = rs.getString("mat_khau");
                int sotranthang = rs.getInt("so_tran_thang");
                int sotranthua = rs.getInt("so_tran_thua");
                int sotranhoa = rs.getInt("so_tran_hoa");
                int nuocdithang = rs.getInt("nuoc_di_thang");
                int nuocdithua = rs.getInt("nuoc_di_thua");
                float diem = rs.getFloat("diem");
                player = new Player(id, ten, matkhau, sotranthang, sotranthua, sotranhoa, nuocdithang, nuocdithua, diem);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return player;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
