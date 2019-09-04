package lazy.fast.code.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库工具操作类
 *
 * @author wendell
 */
public class JdbcUtils {

    public static String className;
    public static String url;
    public static String user;
    public static String password;

    static {
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        try {
            props.load(is);
            className = props.getProperty("className");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private Connection con = null;

    public JdbcUtils() {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return con;
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

}