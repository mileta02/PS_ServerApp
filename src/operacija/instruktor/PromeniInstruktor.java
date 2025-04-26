/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor;

import model.Instruktor;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class PromeniInstruktor extends ApstraktnaGenerickaOperacija {
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Instruktor))
            throw new Exception("Sistem ne može da zapamti instruktora.");
        if(broker.doesExistForUpdate(obj))
            throw new Exception("Instruktor sa unetim korisničkim imenom već postoji u sistemu.");
        Instruktor i = (Instruktor) obj;
        if (i.getIme().isBlank() || i.getIme().length()>30) {
            throw new Exception("Sistem ne može da zapamti instruktora.\nIme mora sadržati do 30 slova.");
        }
        if (i.getPrezime().isBlank() || i.getPrezime().length()>30) {
            throw new Exception("Sistem ne može da zapamti instruktora.\nPrezime mora sadržati do 30 slova.");
        }
        if (i.getKontakt().isBlank() || !i.getKontakt().matches("\\+?[0-9]{9,15}")) {
            throw new Exception("Sistem ne može da zapamti instruktora.\nKontakt mora imati 9-15 cifara!");
        }
        if (i.getKorisnickoIme().isBlank() || i.getKorisnickoIme().length() < 5 || i.getKorisnickoIme().length() > 30) {
            throw new Exception("Sistem ne može da zapamti instruktora.\nKorisničko ime mora sadržati od 5 do 30 karaktera!");
        }
        if (i.getSifra().isBlank() || i.getSifra().length() < 8 || i.getSifra().length() > 30 || !i.getSifra().matches(".*\\d.*")) {
            throw new Exception("Sistem ne može da zapamti instruktora.\nŠifra mora sadržati od 8 do 30 karaktera uključujući i broj!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.update(obj);
    }
    
}
