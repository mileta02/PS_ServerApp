/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.licenca;

import model.Licenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajLicenca extends ApstraktnaGenerickaOperacija {
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Licenca))
            throw new Exception("Sistem ne može da kreira licencu.");
        if(broker.doesExistForCreate(obj))
            throw new Exception("Sistem ne može da kreira licencu.\nLicenca već postoji u sistemu.");
        Licenca l = (Licenca) obj;
        if(l.getNazivLicence().isBlank() || l.getNazivLicence().length()>40 || !l.getNazivLicence().matches("^[a-zA-Z ]+$"))
             throw new Exception("Sistem ne može da kreira licencu.\nNaziv mora sadržati  do 40 slova.");
        
        if(l.getZvanjeInstruktora().isBlank() || l.getZvanjeInstruktora().length()>40 || !l.getZvanjeInstruktora().matches("^[a-zA-Z ]+$"))
            throw new Exception("Sistem ne može da kreira licencu.\nZvanje mora sadržati do 40 slova.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
    
}
