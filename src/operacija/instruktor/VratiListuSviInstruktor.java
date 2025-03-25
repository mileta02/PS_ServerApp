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
public class VratiListuSviInstruktor extends ApstraktnaGenerickaOperacija{

    private List<Instruktor> list=new ArrayList<>();

    public List<Instruktor> getLista() {
        return list;
    }
    
    
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.read(obj);
    }
    
}
