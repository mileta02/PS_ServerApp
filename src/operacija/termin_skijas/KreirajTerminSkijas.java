/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin_skijas;

import exception.CustomException;
import java.util.List;
import model.TerminSkijas;
import operacija.ApstraktnaGenerickaOperacija;
import repository.RepositoryCustom;
import repository.db.imp.DbRepositoryCustom;

/**
 *
 * @author milan
 */
public class KreirajTerminSkijas extends ApstraktnaGenerickaOperacija{
    private boolean valid;
    protected final RepositoryCustom brokerCustom = new DbRepositoryCustom();

    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null)
            throw new CustomException("error.terminski.addskier.null"); 

        List<TerminSkijas> list = (List<TerminSkijas>) obj;

        for (TerminSkijas ts : list) {
            if (ts.getSkijas() == null)
                throw new CustomException("error.terminski.addskier.noskier"); 
            if (ts.getTermin() == null)
                throw new CustomException("error.terminski.addskier.notermin"); 
            if (ts.getDatumPrijave() == null)
                throw new CustomException("error.terminski.addskier.nodate"); 
        }
    }


    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
       valid = brokerCustom.saveTerminWithSkijasi((List<TerminSkijas>) obj);
    }
}
