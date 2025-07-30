/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor;

import java.util.List;
import model.Instruktor;
import operacija.ApstraktnaGenerickaOperacija;
import repository.RepositoryCustom;
import repository.db.imp.DbRepositoryCustom;

/**
 *
 * @author milan
 */
public class PrijaviInstruktor extends ApstraktnaGenerickaOperacija{
    private Instruktor logged;
    protected final RepositoryCustom brokerCustom = new DbRepositoryCustom();
    
    public Instruktor getLogged() {
        return logged;
    }
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof Instruktor))
            throw new Exception("Ne može da se otvori glavna forma i meni");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        Instruktor ins = (Instruktor) obj;
        logged = brokerCustom.login(ins);
    }
    
}
