/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author milan
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private DBConnection(){
        String url = "jdbc:mysql://localhost:3306/ps_projekat";
        try {
            connection = DriverManager.getConnection(url,"root","");
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
