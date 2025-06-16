/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tip_termina;

import exception.CustomException;
import model.TipTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajTipTermina extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof TipTermina))
            throw new CustomException("error.tiptermina.create.invalid");
        if (broker.doesExistForCreate(obj))
            throw new CustomException("error.tiptermina.create.exists");
        TipTermina tt = (TipTermina) obj;
        if (tt.getNazivTipa().isBlank() || tt.getNazivTipa().length() > 30 || !tt.getNazivTipa().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.tiptermina.create.naziv.invalid");
        if (tt.getCenaSata() < 0)
            throw new CustomException("error.tiptermina.create.cena.invalid");
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
}
