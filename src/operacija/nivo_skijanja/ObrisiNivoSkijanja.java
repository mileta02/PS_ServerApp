/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nivo_skijanja;

import java.util.List;
import logic.Controller;
import model.NivoSkijanja;
import model.Skijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class ObrisiNivoSkijanja extends ApstraktnaGenerickaOperacija {
    private boolean valid;
    public boolean getValid(){
        return valid;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof NivoSkijanja))
            throw new Exception("Sistem ne može da obriše nivo skijanja.");
        
        List<Skijas> list = Controller.getInstance().vratiListuSviSkijas(new Skijas());
        for(Skijas s: list){
            if(s.getNivoSkijanja().equals(obj))
                throw new Exception("Sistem ne može da obriše nivo skijanja.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.delete(obj);
    }
}
