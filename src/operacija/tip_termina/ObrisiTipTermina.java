/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tip_termina;

import exception.CustomException;
import java.util.List;
import logic.Controller;
import model.Termin;
import model.TipTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class ObrisiTipTermina extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof TipTermina))
            throw new CustomException("error.tiptermina.delete.invalid");
        List<Termin> list = Controller.getInstance().vratiListuSviTermin(new Termin());
        for (Termin t : list) {
            if (t.getTipTermina().equals(obj))
                throw new CustomException("error.tiptermina.delete.inuse");
        }
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.delete(obj);
    }
}
