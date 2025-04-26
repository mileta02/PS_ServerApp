/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor;

import java.util.ArrayList;
import java.util.List;
import logic.Controller;
import model.Instruktor;
import model.InstruktorLicenca;
import model.Termin;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class ObrisiInstruktor extends ApstraktnaGenerickaOperacija {
    private boolean valid;
    
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Instruktor))
            throw new Exception("Sistem ne može da obriše instruktora.");
        //Strukturno ogranicenje za termin
        List<Termin> listT = Controller.getInstance().vratiListuSviTermin(new Termin());
        for(Termin t : listT){
            if(t.getInstruktor().equals(obj))
                throw new Exception("Sistem ne može da obriše instruktora.");
        }
        
        //Strukturno ogranicenje za licencu
        List<InstruktorLicenca> listIL = Controller.getInstance().vratiListuInstruktorLicenca((Instruktor) obj);
        if(!listIL.isEmpty())
            throw new Exception("Sistem ne može da obriše instruktora.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.delete(obj);
    }
    
}
