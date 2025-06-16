/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor_licenca;

import exception.CustomException;
import java.util.ArrayList;
import java.util.List;
import model.Instruktor;
import model.InstruktorLicenca;
import operacija.ApstraktnaGenerickaOperacija;
import repository.RepositoryCustom;
import repository.db.imp.DbRepositoryCustom;

/**
 *
 * @author milan
 */
public class VratiListuInstruktorLicenca extends ApstraktnaGenerickaOperacija{
    private List<InstruktorLicenca> list= new ArrayList<>();
    protected final RepositoryCustom brokerCustom = new DbRepositoryCustom();

    public List<InstruktorLicenca> getList() {
        return list;
    }
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof Instruktor))
            throw new CustomException("error.licenca.notfound.for.instruktor");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = brokerCustom.readInstruktorWithLicenca((Instruktor) obj, list);
    }
    
}
