package site.rainbowx.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author yan32
 * createTime: 2024/04/30 10:28
 * description: Sql的句柄
 */
public class SqlHandler {
    private static final String user_name = "work";
    private static final String user_passwd = "test12345678";
    private static final String conn_url = "jdbc:mysql://localhost:3306/work_database";
    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(conn_url, user_name, user_passwd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
