/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor_licenca;

import exception.CustomException;
import model.InstruktorLicenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class ObrisiInstruktorLicenca extends ApstraktnaGenerickaOperacija {
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof InstruktorLicenca))
            throw new CustomException("error.licenca.delete");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.delete(obj);    
    }
    
}
