package skripsi.app.fam.skripsirevisi.Model.HasilProsesModel;

import skripsi.app.fam.skripsirevisi.Utill.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qifli
 */
public class HasilProsesDaoImpl implements HasilProsesDao {
    DatabaseConnection connections = new DatabaseConnection();

    @Override
    public int inputhasil(HasilProses hasilProses) throws SQLException {

        Connection connectDB = connections.getConnection();

        String sql = "INSERT INTO tb_hasilpenilaian_fam (hasil_penilaianfam,keputusan,penilai_nim) values (?,?,?)";

        PreparedStatement ps = connectDB.prepareStatement(sql);

        ps.setDouble(1, hasilProses.getprosesHasil());
        ps.setString(2, hasilProses.getproseskeputusan());
        ps.setString(3, hasilProses.getprosesnim());

        int result = ps.executeUpdate();


        connections.closePreparedStatement(ps);
        connections.closeConnection(connectDB);

        return result;

    }

    @Override
    public List<HasilProses> getALL() throws SQLException {

        Connection connectDB = connections.getConnection();

        //Connection con = dbConnection.getConnection();
        String sql = " SELECT *FROM tb_viewproses";

        List<HasilProses> ContainerList = new ArrayList<>();

        Statement stmt = connectDB.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String nim = rs.getString("nim");
            String nama = rs.getString("nama");
            double hasil = rs.getDouble("hasil_penilaianfam");
            String keputusan = rs.getString("keputusan");

            HasilProses hasilProses = new HasilProses(nim, nama, hasil, keputusan);

            ContainerList.add(hasilProses);
        }

        return ContainerList;

    }

    @Override
    public void clear() {
        try {

            Connection connectDB = connections.getConnection();

            Statement stmt = connectDB.createStatement();
            Statement stmt2 = connectDB.createStatement();

            stmt.executeUpdate("DELETE FROM tb_hasilpenilaian_fam");
            stmt2.executeUpdate("ALTER TABLE tb_hasilpenilaian_fam AUTO_INCREMENT = 1");
        } catch (SQLException ex) {

        }

    }

   
}
