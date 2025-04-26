/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.licenca;

import java.util.ArrayList;
import java.util.List;
import model.Licenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuLicenca extends ApstraktnaGenerickaOperacija{
    private List<Licenca> list = new ArrayList<>();

    public List<Licenca> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Licenca))
            throw new Exception("Sistem ne može da nađe licence po zadatim kriterijumima.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readWithCondition(obj, list);
    }
    
}
