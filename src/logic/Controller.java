/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import database.DBBroker;
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

/**
 *
 * @author milan
 */
public class Controller {
    private static Controller instance;
    private DBBroker broker;
    
    private Controller(){
        broker = new DBBroker();
    }

    public static Controller getInstance() {
        if(instance==null)
            instance = new Controller();
        return instance;
    }
    //INSTRUKTOR SO
    public Instruktor login(Instruktor i) throws Exception {
        
      List<OpstiDomenskiObjekat> odoList = broker.read(i);
      List<Instruktor> instructorList = new ArrayList<>();
      for(OpstiDomenskiObjekat odo: odoList){
          instructorList.add((Instruktor) odo);
      }
      
      for(Instruktor ins : instructorList){
          if(ins.getKorisnickoIme().equals(i.getKorisnickoIme()) && ins.getSifra().equals(i.getSifra())){
              return ins;
          }
      }
      throw new Exception("Instruktor sa unetim kredencijalima ne postoji.");
      
    }

    public Instruktor kreirajInstruktor(Instruktor in) throws Exception {
        if(broker.create(in)){
            return in;
        }
        throw new Exception("Korisnik nije dobro uneo podatke.");
       
    }

    public boolean promeniInstruktor(Instruktor ins) throws Exception {
        return broker.update(ins);
        
        
    }

    public boolean obrisiInstruktor(Instruktor inst) throws Exception {
        return broker.delete(inst);
    }

    public Object vratiListuSviInstruktor(Instruktor instr) throws Exception {
        return broker.read(instr);
    }

    public Object vratiListuInstruktor(Instruktor instru) throws Exception {
        return broker.readWithCondition(instru);
    }
    //LICENCA
    
    public boolean kreirajLicenca(Licenca l) throws Exception {
        return broker.create(l);
    }

    public boolean promeniLicenca(Licenca l) throws Exception {
        return  broker.update(l);
    }

    public boolean obrisiLicenca(Licenca l) throws Exception {
        return broker.delete(l);
    }

    public Object vratiListuSviLicenca(Licenca l) throws Exception {
        return broker.read(l);
    }
    
    public Object vratiListuLicenca(Licenca l) throws Exception {
        return broker.readWithCondition(l);
    }
    
    //INSTRUKTORLICENCA SO
    public List<InstruktorLicenca> vratiListuInstruktorLicenca(Instruktor i) throws Exception {
        return broker.readInstruktorWithLicenca(i);
    }
    
    public Object vratiListuInstruktorLicencaFilter(InstruktorLicenca il) throws Exception {
        return broker.readInstruktorWithLicencaWithCondition(il);
    }

    public boolean obrisiInstruktorLicenca(InstruktorLicenca il) throws Exception {
        return broker.delete(il);
    }

    public boolean kreirajInstruktorLicenca(InstruktorLicenca il) throws Exception {
        return broker.create(il);
    }
    
    //NIVO_SKIJANJA
    public boolean kreirajNivoSkijanja(NivoSkijanja ns) throws Exception {
        return broker.create(ns);
    }

    public Object vratiListuSviNivoSkijanja(NivoSkijanja ns) throws Exception {
        return broker.read(ns);
    }

    public Object vratiListuNivoSkijanja(NivoSkijanja ns) throws Exception {
        return broker.readWithCondition(ns);
    }

    public boolean obrisiNivoSkijanja(NivoSkijanja ns) throws Exception {
        return broker.delete(ns);
    }

    public boolean promeniNivoSkijanja(NivoSkijanja ns) throws Exception {
        return broker.update(ns);
    }
    
    //TIP_TERMINA
    public Object kreirajTipTermina(TipTermina tt) throws Exception {
        return broker.create(tt);
    }

    public Object vratiListuSviTipTermina(TipTermina tt) throws Exception {
        return broker.read(tt);
    }

    public Object vratiListuTipTermina(TipTermina tt) throws Exception {
        return broker.readWithCondition(tt);
    }
    public Object promeniTipTermina(TipTermina tt) throws Exception {
        return broker.update(tt);
    }

    public Object obrisiTipTermina(TipTermina tt) throws Exception {
        return broker.delete(tt);
    }
    
    
    //SKIJAS
    public Object vratiListuSviSkijas(Skijas s) throws Exception {
        return broker.readSkijasWithNivoSkijanja(s);
    }

    public Object vratiListuSkijas(Skijas s) throws Exception {
        return broker.readSkijasWithNivoSkijanjaWithCondition(s);
    }

    public Object kreirajSkijas(Skijas s) throws Exception {
        return broker.create(s);
    }

    public Object obrisiSkijas(Skijas s) throws Exception {
        return broker.delete(s);
    }

    public Object promeniSkijas(Skijas s) throws Exception {
        return broker.update(s);
    }
    
    
    //TERMIN
    public Object vratiListuSviTermin(Termin t) throws Exception{
        return broker.readTerminWithInstruktorTip(t);
    }

    public Object kreirajTermin(Termin t) throws Exception{
        return broker.create(t);
    }

    public Object vratiListuTermin(Termin t) throws Exception{
        return broker.readTerminWithInstruktorWithCondition(t);
    }

    public Object promeniTermin(Termin t) throws Exception{
        return broker.update(t);
    }

    public Object obrisiTermin(Termin t) throws Exception{
        return broker.delete(t);
    }

    
    //TERMIN_SKIJAS
    public Object vratiListuTerminSkijas(Termin t) throws Exception{
        return broker.readTerminWithSkijas(t);
    }

    public Object kreirajTerminSkijas(TerminSkijas t) throws Exception {
        return broker.create(t);
    }

    public Object promeniTerminSkijas(TerminSkijas t) throws Exception {
        return broker.update(t);
    }

    public Object obrisiTerminSkijas(TerminSkijas t) throws Exception {
        return broker.delete(t);
    }

    

    
}
