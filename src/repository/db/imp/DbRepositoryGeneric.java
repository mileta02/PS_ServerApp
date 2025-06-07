/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.imp;

import connection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OpstiDomenskiObjekat;
import repository.db.DbRepository;

/**
 *
 * @author milan
 */
public class DbRepositoryGeneric implements DbRepository<OpstiDomenskiObjekat>{

    @Override
    public List<OpstiDomenskiObjekat> read(OpstiDomenskiObjekat param) throws Exception{
        List<OpstiDomenskiObjekat> list = new ArrayList<>();
        String query = "SELECT * FROM "+param.vratiNazivTabele();
        //System.out.println(query);
        try (PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery())
        {
            list = param.vratiListu(rs);
        } catch (Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }      
        
        System.out.println("Operacija read: "+param.getClass()+"\n" +list);
        return list;
    }

    @Override
    public List<OpstiDomenskiObjekat> readWithCondition(OpstiDomenskiObjekat param, List<OpstiDomenskiObjekat> list) throws Exception {
        List<Object> parametri = new ArrayList<>();
        String query = "SELECT * FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiFilter(parametri);
        //System.out.println(query);
        try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query))
        {
            for(int i=0;i<parametri.size();i++){
                ps.setObject(i+1, parametri.get(i));
            }
            try(ResultSet rs = ps.executeQuery())
            {
                list = param.vratiListu(rs);   
            }
        } catch (Exception ex){
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        System.out.println("Operacija readWithCondition: "+param.getClass()+"\n" +list);
        return list;
    }

    @Override
    public boolean create(OpstiDomenskiObjekat param) throws Exception{
        String query = "INSERT INTO "+param.vratiNazivTabele()+param.vratiKoloneZaUbacivanje()+" VALUES"+param.vratiVrednostZaUbacivanje();
        
        try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query))
        {
            int affRows = ps.executeUpdate();
            System.out.println("Operacija create: "+param.getClass());
            return affRows>0;
        } catch (Exception ex){
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public boolean update(OpstiDomenskiObjekat param) throws Exception{
        String query = "UPDATE "+param.vratiNazivTabele()+ " SET "+param.vratiVrednostZaIzmenu()+ " WHERE "+param.vratiPrimarniKljuc();

        try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query))
        {
            int affRows = ps.executeUpdate();
            System.out.println("Operacija update: "+param.getClass());
            return affRows>0;
        }catch(Exception ex){
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public boolean delete(OpstiDomenskiObjekat param) throws Exception{
        String query = "DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
        
        try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query))
        {   
            int affRows = ps.executeUpdate();
            System.out.println("Operacija delete: "+param.getClass());
            return affRows>0;
        }catch(Exception ex){
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public boolean doesExistForCreate(OpstiDomenskiObjekat param) throws Exception {
        String query = "SELECT * FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiUslovDaPostoji();
        
        try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery())
        {
            System.out.println("Operacija doesExist: "+param.getClass());
            return rs.next();
        }catch(Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    @Override
    public boolean doesExistForUpdate(OpstiDomenskiObjekat param) throws Exception{
        String query = "SELECT * FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiUslovDaPostoji()+" AND "+param.vratiRazlicitPrimarniKljuc();
        
        try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery(query))
        {
            System.out.println("Operacija doesExistForUpdate: "+param.getClass());
            return rs.next();
        }catch(Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }
 
}
