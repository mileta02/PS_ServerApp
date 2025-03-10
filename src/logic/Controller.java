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

    public Instruktor promeniInstruktor(Instruktor ins) throws Exception {
        return  (Instruktor) broker.update(ins);
        
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

    public boolean kreirajLicenca(Licenca l) throws Exception {
        return broker.create(l);
    }

    public Licenca promeniLicenca(Licenca l) throws Exception {
        return (Licenca) broker.update(l);
    }

    public boolean obrisiLicenca(Licenca l) throws Exception {
        return broker.delete(l);
    }

    public Object vratiListuSviLicenca(Licenca l) throws Exception {
        return broker.read(l);
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

    public Object promeniNivoSkijanja(NivoSkijanja ns) throws Exception {
        if(broker.update(ns)!=null)
            return true;
        return false;
    }

    
}
