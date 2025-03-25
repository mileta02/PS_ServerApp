/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor_licenca;

import java.util.List;
import model.Instruktor;
import model.InstruktorLicenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuInstruktorLicencaFilter extends ApstraktnaGenerickaOperacija{
    private List<InstruktorLicenca> list;

    public List<InstruktorLicenca> getList() {
        return list;
    }
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readInstruktorWithLicencaWithCondition((InstruktorLicenca) obj);
    }
}
