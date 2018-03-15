package project.java.dao.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final String URL_DB = "jdbc:mysql://127.0.0.1:2016/?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER_DB = "root";
    private static final String PASSWORD_DB = "";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

//        try {
//            InitialContext initialContext = new InitialContext();
//            DataSource dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/my_sql_kozlov");
//            return dataSource.getConnection();
//        } catch (NamingException | SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
