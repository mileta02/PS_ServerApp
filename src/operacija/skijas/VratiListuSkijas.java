/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skijas;

import java.util.List;
import model.Skijas;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuSkijas extends ApstraktnaGenerickaOperacija{
    private List<Skijas> list;

    public List<Skijas> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readSkijasWithNivoSkijanjaWithCondition((Skijas) obj);
    }
}
