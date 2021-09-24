package com.example.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static final Logger LOGGER = LogManager.getLogger(DBManager.class.getSimpleName());
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotelDB";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "rJycnfynby03";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static DBManager dbManager;

    /*static {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream("app.properties")) {
            prop.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        DATABASE_URL = prop.getProperty("connection.url");
        USER_NAME = prop.getProperty("DB.userName");
        PASSWORD = prop.getProperty("DB.password");
        DRIVER = prop.getProperty("DB.driver");
    }*/

    private DBManager() {  }

    public static DBManager getInstance() {
        if (dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }

    public static void setAutoCommitQuietly(Connection conn, boolean autoCommit) {
        try {
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }

    public static void rollBackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }

}
