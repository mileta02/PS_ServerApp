/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin;

import java.time.LocalDate;
import java.util.List;
import logic.Controller;
import model.Termin;
import model.TerminSkijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class ObrisiTermin extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Termin))
            throw new Exception("Sistem ne može da obriše termin.");
        Termin t = (Termin) obj;
        if(t.getDatum().isBefore(LocalDate.now()))
                throw new Exception("Sistem ne može da obriše termin.");
        
        List<TerminSkijas> list = Controller.getInstance().vratiListuSviTerminSkijas(new TerminSkijas());
        for(TerminSkijas ts : list){
            if(ts.getTermin().equals(t))
                throw new Exception("Sistem ne može da obriše termin. Postoje skijaši za izabrani termin.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.delete(obj);
    }
    
}
