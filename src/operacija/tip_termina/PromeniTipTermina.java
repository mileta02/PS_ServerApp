/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tip_termina;

import model.TipTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class PromeniTipTermina extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof TipTermina))
            throw new Exception("Sistem ne može da zapamti tip termina.");
        if(broker.doesExistForUpdate(obj))
            throw new Exception("Tip termina već postoji u sistemu.");
        TipTermina tt = (TipTermina) obj;
        if(tt.getNazivTipa().isBlank() || tt.getNazivTipa().length()>30 || !tt.getNazivTipa().matches("^[a-zA-Z ]+$"))
            throw new Exception("Sistem ne može da zapamti tip termina.\nNaziv tipa mora sadržati do 30 slova.");
        if(tt.getCenaSata()<0)
            throw new Exception("Sistem ne može da zapamti tip termina.\nCena sata mora biti pozitivan broj");
        
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.update(obj);
    }
}
