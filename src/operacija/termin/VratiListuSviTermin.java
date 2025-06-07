/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin;

import java.util.ArrayList;
import java.util.List;
import model.Termin;
import operacija.ApstraktnaGenerickaOperacija;
import repository.RepositoryCustom;
import repository.db.imp.DbRepositoryCustom;

/**
 *
 * @author milan
 */
public class VratiListuSviTermin extends ApstraktnaGenerickaOperacija{
    private List<Termin> list = new ArrayList<>();
    protected final RepositoryCustom brokerCustom = new DbRepositoryCustom();

    public List<Termin> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Termin))
            throw new Exception("Sistem ne može da nađe termine.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = brokerCustom.readTerminWithInstruktorWithTipTermina((Termin) obj, list);
    }
}
