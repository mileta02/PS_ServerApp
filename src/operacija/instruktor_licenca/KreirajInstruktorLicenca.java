/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.instruktor_licenca;

import exception.CustomException;
import java.time.LocalDate;
import java.util.List;
import logic.Controller;
import model.Instruktor;
import model.InstruktorLicenca;
import model.Licenca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milan
 */
public class KreirajInstruktorLicenca extends ApstraktnaGenerickaOperacija {
private boolean valid;
    public boolean getValid(){
        return valid;
    }
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof InstruktorLicenca))
            throw new CustomException("error.license_save_fail");
        if(broker.doesExistForCreate(obj))
            throw new CustomException("error.license_already_exists");
        InstruktorLicenca novaLicenca = (InstruktorLicenca) obj;
        if(novaLicenca.getGodinaSticanja() > LocalDate.now().getYear())
            throw new CustomException("error.license_must_be_past");
        
        Licenca l = novaLicenca.getLicenca();
        Instruktor i = novaLicenca.getInstruktor();
        List<InstruktorLicenca> list = Controller.getInstance().vratiListuInstruktorLicenca(i);
        int stepenLicence = stepen(l);
        
        if(stepenLicence>1){
            boolean prethodniStepen = false;
            for(InstruktorLicenca il : list){
                if(il.getGodinaSticanja()<=novaLicenca.getGodinaSticanja() && stepen(il.getLicenca()) == (stepenLicence-1)){
                    prethodniStepen = true;
                    int godina = godinaPrethodnogStepena(list, stepenLicence-1); 
                    if((novaLicenca.getGodinaSticanja() - godina) <2)
                       throw new CustomException("error.license_min_2_years_between_levels");
                }
            }
            if (!prethodniStepen) {
                throw new CustomException("error.license_previous_level_required");
            }
        }
        
        if(stepenLicence < maksimalanStepen(list, novaLicenca.getGodinaSticanja()))
            throw new CustomException("error.license_only_highest_or_next_level_allowed");

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        valid = broker.create(obj);
    }

    private int stepen(Licenca l) {
        if(l.getZvanjeInstruktora().contains("IV"))
            return 4;
        if(l.getZvanjeInstruktora().contains("III"))
            return 3;
        if(l.getZvanjeInstruktora().contains("II"))
            return 2;
        return 1;
    }
    
    private int maksimalanStepen(List<InstruktorLicenca> list, int godinaSticanja) {
        int max = 0;
        for(InstruktorLicenca il : list){
            if(il.getGodinaSticanja()<=godinaSticanja && stepen(il.getLicenca()) > max){
                max = stepen(il.getLicenca());
            }
        }
        return max;
    }

    private int godinaPrethodnogStepena(List<InstruktorLicenca> list, int stepen) {
        int minGod = LocalDate.now().getYear();
        System.out.println(minGod);
        for(InstruktorLicenca il : list){
            if(stepen(il.getLicenca()) == stepen){
                if(il.getGodinaSticanja()<minGod){
                    minGod=il.getGodinaSticanja();
                    System.out.println(minGod);
                }
            }
        }
        return minGod;
    }
    
}
