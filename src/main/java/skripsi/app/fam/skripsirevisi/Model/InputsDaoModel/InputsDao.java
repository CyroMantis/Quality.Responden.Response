package skripsi.app.fam.skripsirevisi.Model.InputsDaoModel;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author qifli
 */
public interface InputsDao {

    Inputs get(String nim, String nama) throws SQLException;

    int inputs(Inputs inputs) throws SQLException;

    int update(Inputs inputs) throws SQLException;

    int delete(Inputs inputs) throws SQLException;

    List<Inputs> getALL() throws SQLException;

    void clear() throws SQLException;


}
