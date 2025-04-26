/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author milan
 */
import configuration.Configuration;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private DBConnection(){
        try {
            String url = Configuration.getInstance().getProperty("db_url");
            String password = Configuration.getInstance().getProperty("db_password");
            String username = Configuration.getInstance().getProperty("db_user");
            connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() {
        if(instance==null)
            instance = new DBConnection();
        return instance;
    }
    
    
}
