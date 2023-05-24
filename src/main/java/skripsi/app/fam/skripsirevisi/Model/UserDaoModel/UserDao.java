package skripsi.app.fam.skripsirevisi.Model.UserDaoModel;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author qifli
 */
public interface UserDao {

    int insertDetail(User user) throws SQLException;

    int insertAccount(User user) throws SQLException;

    boolean loginpass(String username, String password) throws SQLException;

}
