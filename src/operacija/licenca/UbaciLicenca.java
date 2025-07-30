/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.licenca;

import exception.CustomException;
import model.Licenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class UbaciLicenca extends ApstraktnaGenerickaOperacija {
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Licenca))
            throw new CustomException("error.licenca.create.invalid");
        if (broker.doesExistForCreate(obj))
            throw new CustomException("error.licenca.create.exists");
        Licenca l = (Licenca) obj;
        if (l.getNazivLicence().isBlank() || l.getNazivLicence().length() > 40 || !l.getNazivLicence().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.licenca.create.naziv.invalid");
        if (l.getZvanjeInstruktora().isBlank() || l.getZvanjeInstruktora().length() > 40 || !l.getZvanjeInstruktora().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.licenca.create.zvanje.invalid");
}


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
    
}
