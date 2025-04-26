/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin_skijas;

import model.TerminSkijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajTerminSkijas extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof TerminSkijas))
            throw new Exception("Sistem ne može da doda skijaša u termin.");
        if(broker.doesExist(obj))
            throw new Exception("Sistem ne može da doda skijaša u termin.\nVeć je dodat.");
        TerminSkijas ts = (TerminSkijas) obj;
        if(ts.getSkijas()==null)
            throw new Exception("Sistem ne može da doda skijaša u termin.\nSkijaš nije izabran.");
        if(ts.getTermin()==null)
            throw new Exception("Sistem ne može da doda skijaša u termin.\nTermin nije izabran.");
        if(ts.getDatumPrijave()==null)
            throw new Exception("Sistem ne može da doda skijaša u termin.\nDatum nije izabran.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
}
