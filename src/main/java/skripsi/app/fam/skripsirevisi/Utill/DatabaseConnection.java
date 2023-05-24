package skripsi.app.fam.skripsirevisi.Utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author qifli
 */
public class DatabaseConnection {

    
    //(ALTER TABLE tablename AUTO_INCREMENT = 1) guna untuk reset Auto_increment 
    public Connection databaseDB;

    public Connection getConnection() {
        String databaseNama = "aplikasiskripsifam";
        String databaseUser = "Qifli";
        String databasePassword = "181011400811";
        String url = "jdbc:mysql://localhost/" + databaseNama;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseDB = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("Berhasil Connect");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseDB;

    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public static void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

}
