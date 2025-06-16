/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nivo_skijanja;

import exception.CustomException;
import model.NivoSkijanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class PromeniNivoSkijanja extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof NivoSkijanja))
            throw new CustomException("error.nivoSkijanja.update.invalid");
        if (broker.doesExistForUpdate(obj))
            throw new CustomException("error.nivoSkijanja.update.exists");

        NivoSkijanja ns = (NivoSkijanja) obj;
        if (ns.getNazivNivoa().isBlank() || ns.getNazivNivoa().length() > 30 || !ns.getNazivNivoa().matches("^[a-zA-Z ]+$"))
            throw new CustomException("error.nivoSkijanja.update.naziv.invalid");
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.update(obj);
    }
}
