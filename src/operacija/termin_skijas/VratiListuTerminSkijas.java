/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin_skijas;

import java.util.ArrayList;
import java.util.List;
import model.Termin;
import model.TerminSkijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuTerminSkijas extends ApstraktnaGenerickaOperacija{
    private List<TerminSkijas> list = new ArrayList<>();

    public List<TerminSkijas> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof Termin))
            throw new Exception("Sistem ne može da nadje skijaše za termin.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readTerminWithSkijas((Termin) obj, list);
    }
}
