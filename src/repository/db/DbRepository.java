/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import connection.DBConnection;
import java.sql.SQLException;
import repository.Repository;

/**
 *
 * @author milan
 */
public interface DbRepository<T> extends Repository<T>{
    
    default public void connect(){
        DBConnection.getInstance().getConnection();
    }
    default public void disconnect() throws SQLException{
        DBConnection.getInstance().getConnection().close();
    }
    default public void commit() throws SQLException{
        DBConnection.getInstance().getConnection().commit();
    }
    default public void rollback() throws SQLException{
        DBConnection.getInstance().getConnection().rollback();
    }
    
}
