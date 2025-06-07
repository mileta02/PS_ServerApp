/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin_skijas;

import java.util.List;
import model.TerminSkijas;
import operacija.ApstraktnaGenerickaOperacija;
import repository.RepositoryCustom;
import repository.db.imp.DbRepositoryCustom;

/**
 *
 * @author milan
 */
public class KreirajTerminSkijas extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    protected final RepositoryCustom brokerCustom = new DbRepositoryCustom();

    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        List<TerminSkijas> list = (List<TerminSkijas>) obj;
        if(obj == null)
            throw new Exception("Sistem ne može da doda skijaša u termin.");
        for(TerminSkijas ts : list){
//            if(broker.doesExistForCreate(ts))
//                throw new Exception("Sistem ne može da doda skijaša u termin.\nVeć je dodat.");
            if(ts.getSkijas()==null)
                throw new Exception("Sistem ne može da doda skijaša u termin.\nSkijaš nije izabran.");
            if(ts.getTermin()==null)
                throw new Exception("Sistem ne može da doda skijaša u termin.\nTermin nije izabran.");
            if(ts.getDatumPrijave()==null)
                throw new Exception("Sistem ne može da doda skijaša u termin.\nDatum nije izabran.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
       valid = brokerCustom.saveTerminWithSkijasi((List<TerminSkijas>) obj);
    }
}
