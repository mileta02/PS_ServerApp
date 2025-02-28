/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import database.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.Instruktor;
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

    public Instruktor register(Instruktor in) throws Exception {
        if(broker.create(in)){
            return in;
        }
        throw new Exception("Korisnik nije dobro unetio podatke.");
       
    }

    public Object izmeniInstruktor(Instruktor ins) throws Exception {
        return  (Instruktor) broker.update(ins);
        
    }

    public Object obrisiInstruktor(Instruktor inst) throws Exception {
        return broker.delete(inst);
    }

    
}
