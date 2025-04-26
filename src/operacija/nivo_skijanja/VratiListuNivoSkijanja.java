/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nivo_skijanja;

import java.util.ArrayList;
import java.util.List;
import model.NivoSkijanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class VratiListuNivoSkijanja extends ApstraktnaGenerickaOperacija{
    private List<NivoSkijanja> list = new ArrayList<>();

    public List<NivoSkijanja> getList() {
        return list;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj==null || !(obj instanceof NivoSkijanja))
            throw new Exception("Sistem ne može da nadje nivoe skijanja po zadatim kriterijumima.");
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        list = broker.readWithCondition(obj, list);
    }
}
