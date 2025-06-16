/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor;

import exception.CustomException;
import model.Instruktor;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajInstruktor extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
        @Override
        protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Instruktor))
            throw new CustomException("error.instruktor.create.invalid");
        if (broker.doesExistForCreate(obj))
            throw new CustomException("error.instruktor.create.username.exists");
        Instruktor i = (Instruktor) obj;
        if (i.getIme().isBlank() || i.getIme().length() > 30)
            throw new CustomException("error.instruktor.create.name.invalid");
        if (i.getPrezime().isBlank() || i.getPrezime().length() > 30)
            throw new CustomException("error.instruktor.create.surname.invalid");
        if (i.getKontakt().isBlank() || !i.getKontakt().matches("\\+?[0-9]{9,15}"))
            throw new CustomException("error.instruktor.create.contact.invalid");
        if (i.getKorisnickoIme().isBlank() || i.getKorisnickoIme().length() < 5 || i.getKorisnickoIme().length() > 30)
            throw new CustomException("error.instruktor.create.username.length");
        if (i.getSifra().isBlank() || i.getSifra().length() < 8 || i.getSifra().length() > 30 || !i.getSifra().matches(".*\\d.*"))
            throw new CustomException("error.instruktor.create.password.invalid");
    }
        
        @Override
        protected void izvrsiOperaciju(Object obj) throws Exception {
            valid = broker.create(obj);
        }
}
    

    
    

