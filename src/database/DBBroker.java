/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
import model.Instruktor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InstruktorLicenca;
import model.Licenca;
import model.OpstiDomenskiObjekat;
/**
 *
 * @author milan
 */
public class DBBroker {

    
    public List<OpstiDomenskiObjekat> read(OpstiDomenskiObjekat odo) throws Exception{
        
        List<OpstiDomenskiObjekat> list = new ArrayList<>();
        
        String query = "SELECT * FROM "+odo.vratiNazivTabele();
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        list = odo.vratiListu(rs);
        
        ps.close();
        rs.close();
        
        System.out.println("Vracanje liste.");
        return list;
    }
    
    public List<OpstiDomenskiObjekat> readWithCondition(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> list = new ArrayList<>();
        
        String query = "SELECT * FROM "+odo.vratiNazivTabele()+" WHERE "+odo.vratiUslovNadjiSlogove();
        System.out.println(query);
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        list = odo.vratiListu(rs);
        
        st.close();
        rs.close();
        
        System.out.println("Vracanje liste sa filterom.");
        return list;
    }
    
    public boolean create(OpstiDomenskiObjekat odo) throws Exception{
        try{
            
            String query = "INSERT INTO "+odo.vratiNazivTabele()+odo.vratiKoloneZaUbacivanje()+" VALUES"+odo.vratiVrednostZaUbacivanje();
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            int affRows = ps.executeUpdate();
            
            return affRows==1;
        }catch(SQLException ex){
            System.out.println("Neuspesno izvrsavanja upita u brokeru.");
            throw ex;
        }
        
        
        
    }
    
    public OpstiDomenskiObjekat update(OpstiDomenskiObjekat odo) throws Exception{
        
        try{
        String query = "UPDATE "+odo.vratiNazivTabele()+ " SET "+odo.vratiVrednostZaIzmenu()+ " WHERE "+odo.vratiPrimarniKljuc();
            System.out.println(query);
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        int affRows = st.executeUpdate(query);
        if(affRows==1)
            return odo;
        else
            throw new Exception("Problem sa podacima, nije nista izmenjeno.");
        }catch(SQLException ex){
            System.out.println("Neuspesno izvrsavanje upita prilikom azuriranja podataka.");
            throw ex;
        }
    }
    
    public boolean delete(OpstiDomenskiObjekat odo) throws Exception{
        try{
        String query = "DELETE FROM "+odo.vratiNazivTabele()+" WHERE "+odo.vratiPrimarniKljuc();
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
        int affRows = ps.executeUpdate();
        if(affRows==1)
            return true;
        else
            throw new Exception("Problem sa podacima, instruktor nije obrisan.");
        }catch(SQLException ex){
            System.out.println("Neuspesno izvrsavanje upita prilikom brisanja iz baze.");
            throw ex;
            }
    }
    
    public boolean doesExist(OpstiDomenskiObjekat odo) throws Exception{
        try{
            String query = "SELECT * FROM "+odo.vratiPrimarniKljuc()+" WHERE "+odo.vratiPrimarniKljuc();
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
                return true;
            st.close();
            rs.close();
        }catch(SQLException ex) {
            System.out.println("Neuspesna provera postojanja");
        }
        return false;
    }

    public List<InstruktorLicenca> readInstruktorWithLicenca(Instruktor i) throws Exception {
        List<InstruktorLicenca> list = new ArrayList<>();
        String query = "SELECT zvanjeInstruktora,nazivLicence,idLicenca,datumSticanja FROM instruktorlicenca il JOIN instruktor i "
                + "ON il.instruktor JOIN licenca l ON il.licenca=l.idLicenca = i.idInstruktor WHERE instruktor=?;";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, i.getIdInstruktor());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String naziv = rs.getString("nazivLicence");
                String zvanje = rs.getString("zvanjeInstruktora");
                Date datum = rs.getDate("datumSticanja");
                int idLicenca = rs.getInt("idLicenca");
                Licenca l = new Licenca(idLicenca, zvanje, naziv);
                InstruktorLicenca il = new InstruktorLicenca(datum, i, l);
                list.add(il);
            }
        } catch (SQLException ex) {
           throw ex;
        }
        return list;
    }

   
}
