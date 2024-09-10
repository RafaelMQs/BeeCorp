package br.com.beecorp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnection {
    private static final Logger log = Logger.getLogger(JdbcConnection.class.getName());

    private static final String datasourceName = "bee_manager_system";
    private static final String datasourceUrl = "jdbc:mysql://localhost:3306/";
    private static final String datasourceUsername = "root";
    private static final String datasourcePassword = "@Develop2904";

    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        log.setLevel(Level.FINE);
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(datasourceUrl + datasourceName, datasourceUsername, datasourcePassword);
                log.info("Database connection " + datasourceName + " established");
            } else if (conn.isClosed()) {
                conn = null;
                return getConnection();
            }
            return conn;
        } catch (SQLException e) {
            log.log(Level.FINE, e.getMessage(), e);
            throw new SQLException("Unable to connect to database");
        }
    }

    public static void closeConnection(Connection conn) throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                log.info("Connection to database " + datasourceName + " closed");
            }
        } catch (SQLException e) {
            log.log(Level.FINE, e.getMessage(), e);
            throw new SQLException("Unable to close database connection");
        }
    }
}
