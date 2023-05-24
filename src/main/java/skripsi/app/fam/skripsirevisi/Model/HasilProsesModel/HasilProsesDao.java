package skripsi.app.fam.skripsirevisi.Model.HasilProsesModel;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author qifli
 */
public interface HasilProsesDao {

    int inputhasil(HasilProses hasilProses) throws SQLException;

    List<HasilProses> getALL() throws SQLException;

    void clear() throws SQLException;

}
