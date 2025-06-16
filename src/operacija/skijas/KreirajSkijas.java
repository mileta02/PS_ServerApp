/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skijas;

import exception.CustomException;
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
            throw new CustomException("error.skijas.create.invalid");
        if (broker.doesExistForCreate(obj))
            throw new CustomException("error.skijas.create.exists");
        Skijas sk = (Skijas) obj;
        if (sk.getIme().isBlank() || sk.getIme().length() > 30 || !sk.getIme().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.skijas.create.ime.invalid");
        if (sk.getPrezime().isBlank() || sk.getPrezime().length() > 30 || !sk.getPrezime().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.skijas.create.prezime.invalid");
        if (sk.getBrojTelefona().isBlank() || !sk.getBrojTelefona().matches("\\+?[0-9]{9,15}"))
            throw new CustomException("error.skijas.create.brojTelefon.invalid");
        if (sk.getNivoSkijanja() == null)
            throw new CustomException("error.skijas.create.nivo.invalid");
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
    
}
