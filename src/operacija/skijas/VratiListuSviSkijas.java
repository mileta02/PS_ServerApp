/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skijas;

import java.util.ArrayList;
import java.util.List;
import model.Skijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuSviSkijas extends ApstraktnaGenerickaOperacija{
    private List<Skijas> list= new ArrayList<>();

    public List<Skijas> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Skijas)) 
            throw new Exception("Sistem ne može da pronadje skijaše.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readSkijasWithNivoSkijanja((Skijas) obj, list);
    }
}
