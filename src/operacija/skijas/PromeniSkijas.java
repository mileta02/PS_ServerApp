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
public class PromeniSkijas extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Skijas))
            throw new CustomException("error.skijas.save.invalid");
        if (broker.doesExistForUpdate(obj))
            throw new CustomException("error.skijas.save.exists");
        Skijas sk = (Skijas) obj;
        if (sk.getIme().isBlank() || sk.getIme().length() > 30 || !sk.getIme().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.skijas.save.ime.invalid");
        if (sk.getPrezime().isBlank() || sk.getPrezime().length() > 30 || !sk.getPrezime().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.skijas.save.prezime.invalid");
        if (sk.getBrojTelefona().isBlank() || !sk.getBrojTelefona().matches("\\+?[0-9]{9,15}"))
            throw new CustomException("error.skijas.save.brojtelefona.invalid");
        if (sk.getNivoSkijanja() == null)
            throw new CustomException("error.skijas.save.nivo.invalid");
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.update(obj);
    }
}
