/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor_licenca;

import java.util.ArrayList;
import java.util.List;
import model.Instruktor;
import model.InstruktorLicenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuSviInstruktorLicenca extends ApstraktnaGenerickaOperacija{
    private List<InstruktorLicenca> list= new ArrayList<>();

    public List<InstruktorLicenca> getList() {
        return list;
    }
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof InstruktorLicenca))
            throw new Exception("Sistem ne može da pronadje licece za zadatog instruktora.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.read(obj);
    }
}
