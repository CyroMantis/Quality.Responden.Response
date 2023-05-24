package skripsi.app.fam.skripsirevisi.Model.InputsDaoModel;

import java.sql.SQLException;
import java.util.List;
import skripsi.app.fam.skripsirevisi.Utill.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author qifli
 */
public class InputsDaoImpl implements InputsDao {
    DatabaseConnection connections = new DatabaseConnection();
    // Tarik data dengan kunci
    @Override
    public Inputs get(String nim, String nama) throws SQLException {
        Connection connectDB = connections.getConnection();
        Inputs inputs = null;

        String sql = "SELECT nim, nama, semester, reguler, p_pembelajaran, p_administrasi, p_sarana, p_perpustakaan, p_kemahasiswaan FROM tb_penialaian_mahasiswa WHERE nim= ? OR nama= ?";

        PreparedStatement ps = connectDB.prepareStatement(sql);

        ps.setString(1, nim);
        ps.setString(2, nama);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String nims = rs.getString("nim");
            String namas = rs.getString("nama");
            String semester = rs.getString("semester");
            String reguler = rs.getString("reguler");
            int p_pembelajaran = rs.getInt("p_pembelajaran");
            int p_administrasi = rs.getInt("p_administrasi");
            int p_sarana = rs.getInt("p_sarana");
            int p_perpustakaan = rs.getInt("p_perpustakaan");
            int p_kemahasiswaan = rs.getInt("p_kemahasiswaan");

            inputs = new Inputs(nim, nama, semester, reguler, p_pembelajaran, p_administrasi, p_sarana, p_perpustakaan, p_kemahasiswaan);
        }
        connections.closeResultSet(rs);
        connections.closePreparedStatement(ps);
        connections.closeConnection(connectDB);
        return inputs;

    }

    //Method inputs data
    @Override
    public int inputs(Inputs inputs) throws SQLException {
        Connection connectDB = connections.getConnection();
        String sql = "INSERT INTO tb_penialaian_mahasiswa (nim, nama, semester, reguler, p_pembelajaran, p_administrasi, p_sarana, p_perpustakaan, p_kemahasiswaan) values (?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = connectDB.prepareStatement(sql);

        ps.setString(1, inputs.getNim());
        ps.setString(2, inputs.getNama());
        ps.setString(3, inputs.getSemester());
        ps.setString(4, inputs.getReguler());
        ps.setInt(5, inputs.getP_pembelajaran());
        ps.setInt(6, inputs.getP_administrasi());
        ps.setInt(7, inputs.getP_sarana());
        ps.setInt(8, inputs.getP_perpustakaan());
        ps.setInt(9, inputs.getP_kemahasiswaan());

        int result = ps.executeUpdate();

        connections.closePreparedStatement(ps);
        connections.closeConnection(connectDB);

        return result;
    }

    @Override
    //method update
    public int update(Inputs inputs) throws SQLException {

        Connection connectDB = connections.getConnection();

        String sql = "UPDATE tb_penialaian_mahasiswa set nama = ?, semester = ?, reguler = ?, p_pembelajaran = ?, p_administrasi = ?, p_sarana = ?, p_perpustakaan =?, p_kemahasiswaan = ? where  nim=?";

        PreparedStatement ps = connectDB.prepareStatement(sql);

        ps.setString(1, inputs.getNama());
        ps.setString(2, inputs.getSemester());
        ps.setString(3, inputs.getReguler());
        ps.setInt(4, inputs.getP_pembelajaran());
        ps.setInt(5, inputs.getP_administrasi());
        ps.setInt(6, inputs.getP_sarana());
        ps.setInt(7, inputs.getP_perpustakaan());
        ps.setInt(8, inputs.getP_kemahasiswaan());
        ps.setString(9, inputs.getNim());

        int rs = ps.executeUpdate();

        connections.closePreparedStatement(ps);
        connections.closeConnection(connectDB);

        return rs;
    }

    @Override
    // method hapus
    public int delete(Inputs inputs) throws SQLException {

        Connection connectDB = connections.getConnection();

        String sql = "DELETE FROM tb_penialaian_mahasiswa where nim = ?";

        PreparedStatement ps = connectDB.prepareStatement(sql);

        ps.setString(1, inputs.getNim());
       // ps.setString(2, inputs.getNama());

        int rs = ps.executeUpdate();

        connections.closePreparedStatement(ps);
        connections.closeConnection(connectDB);

        return rs;

    }

    @Override
    public List<Inputs> getALL() throws SQLException {
        Connection connectDB = connections.getConnection();

        //Connection con = dbConnection.getConnection();
        String sql = " SELECT *FROM tb_penialaian_mahasiswa";

        List<Inputs> ContainList = new ArrayList<>();

        Statement stmt = connectDB.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String nim = rs.getString("nim");
            String nama = rs.getString("nama");
            String semester = rs.getString("semester");
            String reguler = rs.getString("reguler");
            int p_pembelajaran = rs.getInt("p_pembelajaran");
            int p_administrasi = rs.getInt("p_administrasi");
            int p_sarana = rs.getInt("p_sarana");
            int p_perpustakaan = rs.getInt("p_perpustakaan");
            int p_kemahasiswaan = rs.getInt("p_kemahasiswaan");

            Inputs inputs = new Inputs(nim, nama, semester, reguler, p_pembelajaran, p_administrasi, p_sarana, p_perpustakaan, p_kemahasiswaan);

            ContainList.add(inputs);

        }

        return ContainList;
    }
    public void clear() {
        try {
            Connection connectDB = connections.getConnection();

            Statement stmt = connectDB.createStatement();

            stmt.executeUpdate("DELETE FROM tb_penialaian_mahasiswa");

        } catch (SQLException ex) {

        }

    }
}
