package skripsi.app.fam.skripsirevisi.Model.UserDaoModel;

import skripsi.app.fam.skripsirevisi.Utill.DatabaseConnection;
import java.sql.*;

/**
 *
 * @author qifli
 */
public class UserDaoImpl implements UserDao {
    DatabaseConnection connections = new DatabaseConnection();
    @Override
    public int insertDetail(User user) throws SQLException {
        //  Connection con = dbConnection.getConnection();


        Connection connectDB = connections.getConnection();

        String sql = "INSERT INTO tb_detail_account (nidn,nama_depan,nama_belakang,nama_lengkap,email,no_telp) values (?,?,?,?,?,?)";

        PreparedStatement ps = connectDB.prepareStatement(sql);

        ps.setString(1, user.getNidn());
        ps.setString(2, user.getNama_depan());
        ps.setString(3, user.getNama_belakang());
        ps.setString(4, user.getNama_lengkap());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getNo_telp());

        int result = ps.executeUpdate();

        connections.closeConnection(connectDB);
        return result;

    }

    @Override
    public boolean loginpass(String username, String password) throws SQLException {

        Connection connectDB = connections.getConnection();
        String sql = "SELECT * FROM tb_useraccount WHERE username = ? and password = ?";

        PreparedStatement ps = connectDB.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

   @Override
       public int insertAccount(User user) throws SQLException {

        Connection connectDB = connections.getConnection();
                
        String sql2 = "INSERT INTO tb_useraccount (Username,Password,user_nidn) VALUES (?,?,?)";
        PreparedStatement ps2 = connectDB.prepareStatement(sql2);
        ps2.setString(1, user.getDtl_username());
        ps2.setString(2, user.getDtl_password());
        ps2.setString(3, user.getNidn());

        int result2 = ps2.executeUpdate();

        connections.closePreparedStatement(ps2);

        return result2;

    }
    
}
