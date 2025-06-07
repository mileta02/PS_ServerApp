/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin;

import java.time.LocalDate;
import model.Termin;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajTermin extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Termin))
            throw new Exception("Sistem ne može da kreira Termin.");
        if(broker.doesExistForCreate(obj))
            throw new Exception("Sistem ne može da kreira Termin.\n Vec postoji.");
        Termin t = (Termin) obj;
        if(t.getVremeOd()==null || t.getVremeDo()==null ||  t.getVremeDo().isBefore(t.getVremeOd()))
            throw new Exception("Sistem ne može da kreira Termin.\n Vreme kraja mora biti nakon vremena početka.");
        if(t.getMaxBrojSkijasa()<=0)
            throw new Exception("Sistem ne može da kreira Termin.\n Broj skijaša mora biti veći od 0.");
        if(t.getUkupanIznos()<0)
            throw new Exception("Sistem ne može da kreira Termin.\n Ukupan iznos mora biti veći od 0.");
        if(t.getDatum()==null || t.getDatum().isBefore(LocalDate.now()))
            throw new Exception("Sistem ne može da kreira Termin.\n Datum se mora odnositi na budućnost.");
        if(t.getTipTermina()==null)
            throw new Exception("Sistem ne može da kreira Termin.\n Tip termina nije odabran.");
        if(t.getInstruktor()==null)
            throw new Exception("Sistem ne može da kreira Termin.\n Instruktor nije odabran.");

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }
    
}
