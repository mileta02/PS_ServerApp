/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin;

import exception.CustomException;
import java.time.LocalDate;
import model.Termin;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class PromeniTermin extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Termin))
            throw new CustomException("error.termin.save.invalid");
        if (broker.doesExistForUpdate(obj))
            throw new CustomException("error.termin.save.exists");
        Termin t = (Termin) obj;
        if (t.getVremeOd() == null || t.getVremeDo() == null || t.getVremeDo().isBefore(t.getVremeOd()))
            throw new CustomException("error.termin.save.time.invalid");
        if (t.getMaxBrojSkijasa() <= 0)
            throw new CustomException("error.termin.save.skiers.invalid");
        if (t.getUkupanIznos() < 0)
            throw new CustomException("error.termin.save.amount.invalid");
        if (t.getDatum() == null || t.getDatum().isBefore(LocalDate.now()))
            throw new CustomException("error.termin.save.date.invalid");
        if (t.getTipTermina() == null)
            throw new CustomException("error.termin.save.type.missing");
        if (t.getInstruktor() == null)
            throw new CustomException("error.termin.save.instructor.missing");
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.update(obj);
    }
    
}
