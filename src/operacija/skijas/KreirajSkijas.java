/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skijas;

import model.Skijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajSkijas extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Skijas))
            throw new Exception("Sistem ne može da kreira skijaša.");
        if(broker.doesExistForCreate(obj))
            throw new Exception("Sistem ne može da kreira skijaša.\nSkijaš već postoji u sistemu.");
        Skijas sk = (Skijas) obj;
        if(sk.getIme().isBlank() || sk.getIme().length()>30 || !sk.getIme().matches("^[a-zA-Z ]+$"))
            throw new Exception("Sistem ne može da kreira skijaša.\nIme skijaša mora sadrzati do 30 slova.");
        if(sk.getPrezime().isBlank() || sk.getPrezime().length()>30 ||  !sk.getPrezime().matches("^[a-zA-Z ]+$"))
            throw new Exception("Sistem ne može da kreira skijaša.\nPrezime skijaša mora sadrzati do 30 slova.");
        if(sk.getBrojTelefona().isBlank() || !sk.getBrojTelefona().matches("\\+?[0-9]{9,15}"))
            throw new Exception("Sistem ne može da kreira skijaša.\nBroj skijasa mora sadrzati 9-15 cifara.");
        if(sk.getNivoSkijanja()==null)
            throw new Exception("Sistem ne može da kreira skijaša.\nNivo skijanja mora imati dozvoljenu vrednost.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
    
}
