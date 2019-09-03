package lazy.fast.code.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static String user;
    private static String password;
    private static String className;
    private static String url;

    static {
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        try {
            props.load(is);
            className = props.getProperty("jdbc.className");
            url = props.getProperty("jdbc.url");
            user = props.getProperty("jdbc.user");
            password = props.getProperty("jdbc.password");
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