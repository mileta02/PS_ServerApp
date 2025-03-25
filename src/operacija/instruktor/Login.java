/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor;

import java.util.List;
import model.Instruktor;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class Login extends ApstraktnaGenerickaOperacija{
    private Instruktor logged;

    public Instruktor getLogged() {
        return logged;
    }
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        List<Instruktor> list = broker.read((Instruktor) obj);
        Instruktor ins = (Instruktor) obj;
        for(Instruktor i : list){
            if(i.getKorisnickoIme().equals(ins.getKorisnickoIme()) && i.getSifra().equals(ins.getSifra())){
              logged = i;
              return;
          }
        }
        throw new Exception("Instruktor sa unetim kredencijalima ne postoji.");
    }
    
}
