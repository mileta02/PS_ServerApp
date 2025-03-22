package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Instruktor;
import model.InstruktorLicenca;
import model.Licenca;
import model.NivoSkijanja;
import model.OpstiDomenskiObjekat;
import model.Skijas;
import model.Termin;
import model.TerminSkijas;
import model.TipTermina;
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
        
        System.out.println("Vracanje liste." +list);
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
        System.out.println(query);
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
    
    public boolean update(OpstiDomenskiObjekat odo) throws Exception{
        
        try{
        String query = "UPDATE "+odo.vratiNazivTabele()+ " SET "+odo.vratiVrednostZaIzmenu()+ " WHERE "+odo.vratiPrimarniKljuc();
        System.out.println(query);
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        int affRows = st.executeUpdate(query);
        if(affRows>0)
            return true;
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
            System.out.println(query);
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
                + "ON il.instruktor=i.idInstruktor JOIN licenca l ON il.licenca=l.idLicenca WHERE instruktor=?";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, i.getIdInstruktor());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String naziv = rs.getString("nazivLicence");
                String zvanje = rs.getString("zvanjeInstruktora");
                LocalDate datum = rs.getDate("datumSticanja").toLocalDate();
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
    
    public List<InstruktorLicenca> readInstruktorWithLicencaWithCondition(InstruktorLicenca il) throws Exception {
        List<InstruktorLicenca> list = new ArrayList<>();
        String query = "SELECT zvanjeInstruktora,nazivLicence,idLicenca,datumSticanja FROM instruktorlicenca il JOIN instruktor i "
                + "ON il.instruktor=i.idInstruktor JOIN licenca l ON il.licenca=l.idLicenca WHERE instruktor=? AND nazivLicence LIKE ?;";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, il.getInstruktor().getIdInstruktor());
            ps.setString(2,il.getLicenca().getNazivLicence()+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String naziv = rs.getString("nazivLicence");
                String zvanje = rs.getString("zvanjeInstruktora");
                LocalDate datum = rs.getDate("datumSticanja").toLocalDate();
                int idLicenca = rs.getInt("idLicenca");
                Licenca l = new Licenca(idLicenca, zvanje, naziv);
                InstruktorLicenca i = new InstruktorLicenca(datum, il.getInstruktor() , l);
                list.add(i);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
           throw ex;
        }
        return list;
    }

    public List<Skijas> readSkijasWithNivoSkijanja(Skijas s) throws Exception {
        
        List<Skijas> list = new ArrayList<>();
        String query = "SELECT * FROM skijas skijas JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja;";
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            NivoSkijanja ns = new NivoSkijanja();
            ns = (NivoSkijanja) ns.vratiObjekatIzRs(rs);
            
            
            Skijas sk = new Skijas();
            sk = (Skijas) sk.vratiObjekatIzRs(rs);
            sk.setNivoSkijanja(ns);
            

            list.add(sk);
        }
        st.close();
        rs.close();
        
        return list;
    }
    
    public List<Skijas> readSkijasWithNivoSkijanjaWithCondition(Skijas s) throws Exception {
        
        List<Skijas> list = new ArrayList<>();
        String query = "SELECT * FROM skijas skijas JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja WHERE "
                + s.vratiUslovNadjiSlogove()+";";
        System.out.println(query);
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            NivoSkijanja ns = new NivoSkijanja();
            ns = (NivoSkijanja) ns.vratiObjekatIzRs(rs);
            
            
            Skijas sk = new Skijas();
            sk = (Skijas) sk.vratiObjekatIzRs(rs);
            sk.setNivoSkijanja(ns);
                            
            list.add(sk);
        }
        st.close();
        rs.close();
        
        return list;
    }

    public List<Termin> readTerminWithInstruktorTip(Termin t) throws Exception{
        List<Termin> list = new ArrayList<>();
        String query = "SELECT * FROM termin termin JOIN instruktor instruktor ON termin.instruktor=instruktor.idInstruktor "
                + "JOIN tiptermina tiptermina ON termin.tip=tiptermina.idTip;";
        Statement st =  DBConnection.getInstance().getConnection().createStatement();
                
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Termin ter = (Termin) new Termin().vratiObjekatIzRs(rs);
            Instruktor i = (Instruktor) new Instruktor().vratiObjekatIzRs(rs);
            TipTermina tip = (TipTermina) new TipTermina().vratiObjekatIzRs(rs);
            
            ter.setTipTermina(tip);
            ter.setInstruktor(i);
            
            list.add(ter);
        }
        rs.close();
        st.close();
        
        return list;
    }

    public List<Termin> readTerminWithInstruktorWithCondition(Termin t) throws Exception {
        List<Termin> list = new ArrayList<>();
        String query = "SELECT * FROM termin termin JOIN instruktor instruktor ON termin.instruktor=instruktor.idInstruktor "
                + "JOIN tiptermina tiptermina ON termin.tip=tiptermina.idTip WHERE "+t.vratiUslovNadjiSlogove();
        Statement st =  DBConnection.getInstance().getConnection().createStatement();
        System.out.println(query);        
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Termin ter = (Termin) new Termin().vratiObjekatIzRs(rs);
            Instruktor i = (Instruktor) new Instruktor().vratiObjekatIzRs(rs);
            TipTermina tip = (TipTermina) new TipTermina().vratiObjekatIzRs(rs);
            
            ter.setTipTermina(tip);
            ter.setInstruktor(i);
            
            list.add(ter);
        }
        rs.close();
        st.close();
        
        return list;
    }

    public Object readTerminWithSkijas(Termin t) throws Exception {
        List<TerminSkijas> list = new ArrayList<>();
        String query = "SELECT * FROM terminskijas terminskijas JOIN skijas skijas ON terminskijas.skijas=skijas.idSkijas "
                + "JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja WHERE termin="+t.getIdTermin()+";";
                Statement st =  DBConnection.getInstance().getConnection().createStatement();
        System.out.println(query);        
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Skijas s = (Skijas) new Skijas().vratiObjekatIzRs(rs);
            NivoSkijanja ns = (NivoSkijanja) new NivoSkijanja().vratiObjekatIzRs(rs);
            s.setNivoSkijanja(ns);
            LocalDate datum = rs.getDate("datumPrijave").toLocalDate();
            TerminSkijas ts = new TerminSkijas(s, t, datum);
            System.out.println(ts);
            list.add(ts);
        }
        rs.close();
        st.close();
        
        return list;
    }
    
    
   
}
