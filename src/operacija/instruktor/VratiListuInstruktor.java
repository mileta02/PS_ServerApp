/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor;

import java.util.ArrayList;
import java.util.List;
import model.Instruktor;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuInstruktor extends ApstraktnaGenerickaOperacija{
    private List<Instruktor> list = new ArrayList<>();

    public List<Instruktor> getList() {
        return list;
    }
    
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Instruktor))
            throw new Exception("Sistem ne može da nadje instruktore po zadatim kriterijumima.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readWithCondition(obj, list);
    }
    
}
