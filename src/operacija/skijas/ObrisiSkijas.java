/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skijas;

import exception.CustomException;
import java.util.List;
import logic.Controller;
import model.Skijas;
import model.TerminSkijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class ObrisiSkijas extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Skijas))
            throw new CustomException("error.skijas.delete.invalid");

        List<TerminSkijas> list = Controller.getInstance().vratiListuSviTerminSkijas(new TerminSkijas());
        for (TerminSkijas ts : list) {
            if (ts.getSkijas().equals(obj))
                throw new CustomException("error.skijas.delete.inUse");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.delete(obj);
    }
}
