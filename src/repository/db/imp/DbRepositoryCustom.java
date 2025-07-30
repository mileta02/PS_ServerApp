/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.imp;

import connection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class DbRepositoryCustom implements repository.RepositoryCustom{
    @Override
    public List<InstruktorLicenca> readInstruktorWithLicenca(Instruktor i, List<InstruktorLicenca> list) throws Exception {
        String query = "SELECT zvanjeInstruktora,nazivLicence,idLicenca,godinaSticanja FROM instruktorlicenca instruktorlicenca JOIN instruktor instruktor "
                + "ON instruktorlicenca.instruktor=instruktor.idInstruktor "
                + "JOIN licenca licenca ON instruktorlicenca.licenca=licenca.idLicenca WHERE instruktor=?"; 
        try (PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query))
        {
            ps.setInt(1, i.getIdInstruktor());
            try(ResultSet rs = ps.executeQuery())
            {
                while(rs.next()){
                    int godina = rs.getInt("godinaSticanja");
                    Licenca l = (Licenca) new Licenca().vratiObjekatIzRs(rs);
                    InstruktorLicenca il = new InstruktorLicenca(godina, i, l);
                    
                    list.add(il);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        System.out.println("Operacija readInstruktorWithLicenca.");
        return list;
    }

    @Override
    public List<Skijas> readSkijasWithNivoSkijanja(Skijas s, List<Skijas> list) throws Exception {
       String query = "SELECT * FROM skijas skijas JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja;";
       try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery())
       {
           while(rs.next()){
                NivoSkijanja ns = (NivoSkijanja) new NivoSkijanja().vratiObjekatIzRs(rs);
                Skijas sk = (Skijas) new Skijas().vratiObjekatIzRs(rs);
                sk.setNivoSkijanja(ns);
                
                list.add(sk);
        }
       }catch(Exception ex){
           Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
           throw ex;
       }
        System.out.println("Operacija readSkijasWithNivoSkijanja.");
        return list;
    }

    @Override
    public List<Skijas> readSkijasWithNivoSkijanjaWithCondition(Skijas s, List<Skijas> list) throws Exception {
        List<Object> parametri = new ArrayList<>();
        String query = "SELECT * FROM skijas skijas JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja WHERE "
                + s.vratiFilter(parametri)+";";
        try(PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query))
        {   
            for(int i=0;i<parametri.size();i++){
                ps.setObject(i+1, parametri.get(i));
            }
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    NivoSkijanja ns = (NivoSkijanja) new NivoSkijanja().vratiObjekatIzRs(rs);
                    Skijas sk = (Skijas) new Skijas().vratiObjekatIzRs(rs);
                    sk.setNivoSkijanja(ns);

                    list.add(sk);
                }
            }
        }catch(Exception ex){
           Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
           throw ex;
        }
        System.out.println("Operacija readSkijasWithNivoSkijanjaWithCondition.");
        return list;
    }

    @Override
    public List<Termin> readTerminWithInstruktorWithTipTermina(Termin t, List<Termin> list) throws Exception {
        String query = "SELECT * FROM termin termin JOIN instruktor instruktor ON termin.instruktor=instruktor.idInstruktor "
                + "JOIN tiptermina tiptermina ON termin.tip=tiptermina.idTip;";
        try(PreparedStatement ps =  DBConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery())
        {
            while(rs.next()){
                Termin ter = (Termin) new Termin().vratiObjekatIzRs(rs);
                Instruktor i = (Instruktor) new Instruktor().vratiObjekatIzRs(rs);
                TipTermina tip = (TipTermina) new TipTermina().vratiObjekatIzRs(rs);
                ter.setTipTermina(tip);
                ter.setInstruktor(i);
                
                list.add(ter);
            }
        }catch(Exception ex){
           Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
           throw ex;
        }
        System.out.println("Operacija readTerminWithInstruktorWithTipTermina.");
        return list;
    }

    @Override
    public List<Termin> readTerminWithInstruktorWithTipTerminaWithCondition(Termin t, List<Termin> list) throws Exception {
        List<Object> parametri = new ArrayList<>();
        String query = "SELECT * FROM termin termin JOIN instruktor instruktor ON termin.instruktor=instruktor.idInstruktor "
                + "JOIN tiptermina tiptermina ON termin.tip=tiptermina.idTip WHERE "+t.vratiFilter(parametri);
        try(PreparedStatement ps =  DBConnection.getInstance().getConnection().prepareStatement(query))
        {
            for(int i=0;i<parametri.size();i++){
                ps.setObject(i+1, parametri.get(i));
            }
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Termin ter = (Termin) new Termin().vratiObjekatIzRs(rs);
                    Instruktor i = (Instruktor) new Instruktor().vratiObjekatIzRs(rs);
                    TipTermina tip = (TipTermina) new TipTermina().vratiObjekatIzRs(rs);
                    ter.setTipTermina(tip);
                    ter.setInstruktor(i);

                    list.add(ter);
                }
            }
        }catch(Exception ex){
           Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
           throw ex;
        }
        System.out.println("Operacija readTerminWithInstruktorWithTipTerminaWithCondition.");
        return list;
    }

    @Override
    public List<TerminSkijas> readTerminWithSkijas(Termin t, List<TerminSkijas> list) throws Exception {
        String query = "SELECT * FROM terminskijas terminskijas JOIN skijas skijas ON terminskijas.skijas=skijas.idSkijas "
                + "JOIN nivoskijanja nivoskijanja ON skijas.nivoSkijanja=nivoskijanja.idNivoSkijanja WHERE termin=?";
        try(PreparedStatement ps =  DBConnection.getInstance().getConnection().prepareStatement(query))
        {
            ps.setInt(1, t.getIdTermin());
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Skijas s = (Skijas) new Skijas().vratiObjekatIzRs(rs);
                    NivoSkijanja ns = (NivoSkijanja) new NivoSkijanja().vratiObjekatIzRs(rs);
                    s.setNivoSkijanja(ns);
                    LocalDate datum = rs.getDate("datumPrijave").toLocalDate();
                    TerminSkijas ts = new TerminSkijas(s, t, datum);

                    list.add(ts);
                }
            }
        }catch(Exception ex){
           Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
           throw ex;
        }
        System.out.println("Operacija readTerminWithSkijas.");
        return list;
    }

    @Override
    public boolean saveTerminWithSkijasi(List<TerminSkijas> list) throws Exception {
        TerminSkijas param = list.get(0);
        Termin t = param.getTermin();
        
        boolean cont = true;
        if(param.getSkijas().getIdSkijas()==0)
            cont = false;
        
        String deleteQuery = "DELETE FROM terminskijas WHERE termin = ?";
        System.out.println(deleteQuery);
        try (PreparedStatement deletePs = DBConnection.getInstance().getConnection().prepareStatement(deleteQuery)) {
            deletePs.setInt(1, t.getIdTermin());
            deletePs.executeUpdate();
        }
        if(!cont)
            return true;
        String addQuery = "INSERT INTO terminskijas VALUES (?, ?, ?)"; 
        System.out.println(addQuery);

        try (PreparedStatement addPs = DBConnection.getInstance().getConnection().prepareStatement(addQuery)) {
            for (TerminSkijas ts : list) {
                addPs.setInt(1, ts.getTermin().getIdTermin());
                addPs.setInt(2, ts.getSkijas().getIdSkijas());
                addPs.setDate(3, java.sql.Date.valueOf(ts.getDatumPrijave()));
                addPs.addBatch();
            }
            addPs.executeBatch();
        } catch (Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        System.out.println("Operacija saveTerminWithSkijasi.");
        return true;
    }

    @Override
    public Instruktor login(Instruktor instruktor) throws Exception {
            String query = "SELECT * FROM instruktor WHERE korisnickoIme=? AND sifra=?;";
            try(PreparedStatement ps =  DBConnection.getInstance().getConnection().prepareStatement(query))
            {
                ps.setString(1, instruktor.getKorisnickoIme());
                ps.setString(2, instruktor.getSifra());
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        Instruktor ulogovaniInstruktor = new Instruktor();
                        ulogovaniInstruktor.setIdInstruktor(rs.getInt("idInstruktor"));
                        ulogovaniInstruktor.setIme(rs.getString("ime"));
                        ulogovaniInstruktor.setPrezime(rs.getString("prezime"));
                        ulogovaniInstruktor.setKontakt(rs.getString("kontakt"));
                        ulogovaniInstruktor.setKorisnickoIme(rs.getString("korisnickoIme"));
                        ulogovaniInstruktor.setSifra(rs.getString("sifra"));

                        return ulogovaniInstruktor;
                    }
                }
            }catch(Exception ex){
               Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
               throw ex;
            }
        System.out.println("Operacija login.");     
        return null;
    }
}
