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
    /*
    public Instruktor login(Instruktor i) throws Exception {
    
        try{
        String query = "Select * from "+i.vratiNazivTabele()+" where korisnickoIme=? and sifra=?;";
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
        ps.setString(1, i.getKorisnickoIme());
        ps.setString(2, i.getSifra());
        
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            i.setIdInstruktor(rs.getInt("idInstruktor"));
            i.setIme(rs.getString("ime"));
            i.setPrezime(rs.getString("prezime"));
            i.setKontakt(rs.getString("kontakt"));
        }else 
        {
            throw new Exception("Korisnik ne postoji!");
        }
        rs.close();
        ps.close();
        System.out.println("Uspesno ucitavanje objekta User iz baze!");
        return i;
        }catch(SQLException e){
            System.out.println("Neuspesno citanje korisnika iz baze.");
            throw e;
        }

    }

    public Instruktor register(Instruktor in) throws Exception {
        try{
        String query = "INSERT INTO "+in.vratiNazivTabele()+in.vratiKoloneZaUbacivanje()+" VALUES "+in.vratiVrednostZaUbacivanje();
        PreparedStatement st = DBConnection.getInstance().getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        int ar = st.executeUpdate();
        if(ar==1){
            
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                int key = rs.getInt(1);
                in.setIdInstruktor(key);
            }
            System.out.println("Uspešno registrovan instruktor.");
            return in;
        }
        else{
            throw new Exception("Greska prilikom insertovanja u bazu.");
        }
        }catch(SQLException ex){
            System.out.println("Neuspesno registrovanje prilikom izvrsavanja upita.");
            throw ex;
        }
        
    }
    */
   
    
}
