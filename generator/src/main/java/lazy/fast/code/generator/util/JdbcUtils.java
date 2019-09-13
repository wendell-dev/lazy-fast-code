package lazy.fast.code.generator.util;

import lazy.fast.code.generator.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具操作类
 *
 * @author wendell
 */
public class JdbcUtils {

    private Connection con = null;

    public JdbcUtils() {
        try {
            Class.forName(Config.jdbcClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(Config.jdbcUrl, Config.jdbcUser, Config.jdbcPassword);
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