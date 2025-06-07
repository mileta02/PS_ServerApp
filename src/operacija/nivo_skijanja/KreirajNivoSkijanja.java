/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nivo_skijanja;

import model.NivoSkijanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajNivoSkijanja extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof NivoSkijanja))
            throw new Exception("Sistem ne može da kreira nivo skijanja.");
        if(broker.doesExistForCreate(obj))
            throw new Exception("Sistem ne može da kreira nivo skijanja.\nNivo skijanja već postoji u sistemu.");
        NivoSkijanja ns = (NivoSkijanja) obj;
        if(ns.getNazivNivoa().isBlank() || ns.getNazivNivoa().length()>30 || !ns.getNazivNivoa().matches("^[a-zA-Z ]+$"))
            throw new Exception("Sistem ne može da kreira nivo skijanja.\nNaziv nivoa skijanja treba da sadrži do 30 slova.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
}
