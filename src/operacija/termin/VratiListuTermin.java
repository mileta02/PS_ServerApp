/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin;

import java.util.ArrayList;
import java.util.List;
import model.Termin;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuTermin extends ApstraktnaGenerickaOperacija{
    private List<Termin> list = new ArrayList<>();

    public List<Termin> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Termin))
            throw new Exception("Sistem ne može da pronađe termine po zadatim kriterijumima.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readTerminWithInstruktorWithTipTerminaWithCondition((Termin) obj, list);
    }
    
}
