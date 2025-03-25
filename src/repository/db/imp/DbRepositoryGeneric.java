/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.imp;

import connection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Instruktor;
import model.InstruktorLicenca;
import model.Licenca;
import model.NivoSkijanja;
import model.OpstiDomenskiObjekat;
import model.Skijas;
import model.Termin;
import model.TerminSkijas;
import model.TipTermina;
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
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        list = param.vratiListu(rs);
        
        ps.close();
        rs.close();
        
        System.out.println("Vracanje liste." +list);
        return list;
    }

    @Override
    public List<OpstiDomenskiObjekat> readWithCondition(OpstiDomenskiObjekat param) throws Exception{
        List<OpstiDomenskiObjekat> list = new ArrayList<>();
        
        String query = "SELECT * FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiUslovNadjiSlogove();
        System.out.println(query);
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        list = param.vratiListu(rs);
        
        st.close();
        rs.close();
        System.out.println(query);
        System.out.println("Vracanje liste sa filterom.");
        return list;
    }

    @Override
    public boolean create(OpstiDomenskiObjekat param) throws Exception{
        try{
            
            String query = "INSERT INTO "+param.vratiNazivTabele()+param.vratiKoloneZaUbacivanje()+" VALUES"+param.vratiVrednostZaUbacivanje();
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            int affRows = ps.executeUpdate();
            ps.close();
            return affRows>0;
        }catch(SQLException ex){
            System.out.println("Neuspesno izvrsavanja upita create u brokeru.");
            throw ex;
        }
    }

    @Override
    public boolean update(OpstiDomenskiObjekat param) throws Exception{
        try{
        String query = "UPDATE "+param.vratiNazivTabele()+ " SET "+param.vratiVrednostZaIzmenu()+ " WHERE "+param.vratiPrimarniKljuc();
        System.out.println(query);
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        int affRows = st.executeUpdate(query);
        st.close();
        if(affRows>0)
            return true;
        else
            return false;
        }catch(SQLException ex){
            System.out.println("Neuspesno izvrsavanje upita azuriranja podataka.");
            throw ex;
        }
    }

    @Override
    public boolean delete(OpstiDomenskiObjekat param) throws Exception{
        try{
        String query = "DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(query);
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
        int affRows = ps.executeUpdate();
        ps.close();
        if(affRows>0)
           return true;
        else
            return false;
        }catch(SQLException ex){
            System.out.println("Neuspesno izvrsavanje upita  brisanja podataka.");
            throw ex;
            }
    }

    @Override
    public boolean doesExist(OpstiDomenskiObjekat param) {
        try{
            String query = "SELECT * FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiUslovDaPostoji();
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            //System.out.println(query);
            if(rs.next())
                return true;
            st.close();
            rs.close();
        }catch(SQLException ex) {
            System.out.println("Neuspesna provera postojanja objekta.");
        }
        return false;
    }

    @Override
    public List<InstruktorLicenca> readInstruktorWithLicenca(Instruktor i) {
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
           System.out.println("Greska prilikom citanja Instruktora sa licencom.");
        }
        return list;
    }

    @Override
    public List<InstruktorLicenca> readInstruktorWithLicencaWithCondition(InstruktorLicenca il) {
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
           System.out.println("Greska prilikom citanja Instruktora sa licencom i uslovom.");
        }
        return list;
    }

    @Override
    public List<Skijas> readSkijasWithNivoSkijanja(Skijas s) {
        List<Skijas> list = new ArrayList<>();
        String query = "SELECT * FROM skijas skijas JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja;";
       try{
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
       }catch(Exception ex){
           System.out.println("Greska prilikom citanja skijasa sa nivoom skijanja.");
       }
        return list;
    }

    @Override
    public List<Skijas> readSkijasWithNivoSkijanjaWithCondition(Skijas s) {
        List<Skijas> list = new ArrayList<>();
        String query = "SELECT * FROM skijas skijas JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja WHERE "
                + s.vratiUslovNadjiSlogove()+";";
        //System.out.println(query);
        try{
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
        }catch(Exception ex){
            System.out.println("Greska prilikom citanja skijasa sa nivoom skijanja sa uslovom.");
        }
        return list;
    }

    @Override
    public List<Termin> readTerminWithInstruktorWithTipTermina(Termin t) {
        List<Termin> list = new ArrayList<>();
        String query = "SELECT * FROM termin termin JOIN instruktor instruktor ON termin.instruktor=instruktor.idInstruktor "
                + "JOIN tiptermina tiptermina ON termin.tip=tiptermina.idTip;";
        try{
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
        }catch(Exception ex){
            System.out.println("Greska prilikom citanja termina sa instruktorom i tipom termina.");
        }
        return list;
    }

    @Override
    public List<Termin> readTerminWithInstruktorWithCondition(Termin t) {
        List<Termin> list = new ArrayList<>();
        String query = "SELECT * FROM termin termin JOIN instruktor instruktor ON termin.instruktor=instruktor.idInstruktor "
                + "JOIN tiptermina tiptermina ON termin.tip=tiptermina.idTip WHERE "+t.vratiUslovNadjiSlogove();
        try{
        Statement st =  DBConnection.getInstance().getConnection().createStatement();
        //System.out.println(query);        
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
        }catch(Exception ex){
            System.out.println("Greska prilikom citanja termina sa instruktorom i tipom termina sa uslovom.");
        }
        return list;
    }

    @Override
    public List<TerminSkijas> readTerminWithSkijas(Termin t) {
        List<TerminSkijas> list = new ArrayList<>();
        String query = "SELECT * FROM terminskijas terminskijas JOIN skijas skijas ON terminskijas.skijas=skijas.idSkijas "
                + "JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja WHERE termin="+t.getIdTermin()+";";
        //System.out.println(query);        
        try{
            Statement st =  DBConnection.getInstance().getConnection().createStatement();
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
        }catch(Exception ex){
            System.out.println("Greska prilikom citanja termina sa skijasem.");
        }
        return list;
    }
    
}
