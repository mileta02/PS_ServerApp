/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tip_termina;

import java.util.ArrayList;
import java.util.List;
import model.TipTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuTipTermina extends ApstraktnaGenerickaOperacija{
    private List<TipTermina> list = new ArrayList<>();

    public List<TipTermina> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof TipTermina))
            throw new Exception("Sistem ne može da pronadje tipove termina po zadatim kriterijumima.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readWithCondition(obj, list);
    }
}
