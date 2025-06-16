/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.licenca;

import exception.CustomException;
import java.util.List;
import logic.Controller;
import model.InstruktorLicenca;
import model.Licenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class ObrisiLicenca extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    public boolean getValid(){
        return valid;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Licenca))
            throw new CustomException("error.licenca.delete.invalid");

        List<InstruktorLicenca> list = Controller.getInstance().vratiListuSviInstruktorLicenca(new InstruktorLicenca());
        Licenca l = (Licenca) obj;
        for (InstruktorLicenca il : list) {
            if (il.getLicenca().getIdLicenca() == l.getIdLicenca())
                throw new CustomException("error.licenca.delete.inuse");
        }
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        broker.delete(obj);
    }
}
