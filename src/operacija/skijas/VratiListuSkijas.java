/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skijas;

import java.util.ArrayList;
import java.util.List;
import model.Skijas;
import operacija.ApstraktnaGenerickaOperacija;
import repository.RepositoryCustom;
import repository.db.imp.DbRepositoryCustom;

/**
 *
 * @author milan
 */
public class VratiListuSkijas extends ApstraktnaGenerickaOperacija{
    private List<Skijas> list = new ArrayList<>();
    protected final RepositoryCustom brokerCustom = new DbRepositoryCustom();

    public List<Skijas> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Skijas)) 
            throw new Exception("Sistem ne može da pronadje skijaše po zadatim kriterijumima.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = brokerCustom.readSkijasWithNivoSkijanjaWithCondition((Skijas) obj, list);
    }
}
