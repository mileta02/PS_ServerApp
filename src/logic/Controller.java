/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.util.ArrayList;
import java.util.List;
import model.*;
import operacija.instruktor.*;
import operacija.instruktor_licenca.*;
import operacija.licenca.*;
import operacija.nivo_skijanja.*;
import operacija.skijas.KreirajSkijas;
import operacija.skijas.ObrisiSkijas;
import operacija.skijas.PromeniSkijas;
import operacija.skijas.VratiListuSkijas;
import operacija.skijas.VratiListuSviSkijas;
import operacija.termin.KreirajTermin;
import operacija.termin.ObrisiTermin;
import operacija.termin.PromeniTermin;
import operacija.termin.VratiListuSviTermin;
import operacija.termin.VratiListuTermin;
import operacija.termin_skijas.KreirajTerminSkijas;
import operacija.termin_skijas.ObrisiTerminSkijas;
import operacija.termin_skijas.VratiListuSviTerminSkijas;
import operacija.termin_skijas.VratiListuTerminSkijas;
import operacija.tip_termina.*;
/**
 *
 * @author milan
 */
public class Controller {
    private static Controller instance;
    
    private Controller(){
    }

    public static Controller getInstance() {
        if(instance==null)
            instance = new Controller();
        return instance;
    }
    //INSTRUKTOR SO
    public Instruktor login(Instruktor i) throws Exception {
        Login operacija = new Login();
        operacija.izvrsi(i, "login");
        return operacija.getLogged();
    }

    public boolean kreirajInstruktor(Instruktor i) throws Exception {
        KreirajInstruktor operacija = new KreirajInstruktor();
        operacija.izvrsi(i, "create");
        return operacija.getValid();
       
    }

    public boolean promeniInstruktor(Instruktor i) throws Exception {
        PromeniInstruktor operacija = new PromeniInstruktor();
        operacija.izvrsi(i, "update");
        return operacija.getValid();  
    }

    public boolean obrisiInstruktor(Instruktor i) throws Exception {
        ObrisiInstruktor operacija = new ObrisiInstruktor();
        operacija.izvrsi(i, "delete");
        return operacija.getValid();
    }

    public List<Instruktor> vratiListuSviInstruktor(Instruktor i) throws Exception {
        VratiListuSviInstruktor operacija = new VratiListuSviInstruktor();
        operacija.izvrsi(new Instruktor(), "read");
        return operacija.getLista();
    }

    public List<Instruktor> vratiListuInstruktor(Instruktor i) throws Exception {
        VratiListuInstruktor operacija = new VratiListuInstruktor();
        operacija.izvrsi(i, "read");
        return operacija.getList();
    }
    
    
    //LICENCA
    public boolean kreirajLicenca(Licenca l) throws Exception {
        KreirajLicenca operacija = new KreirajLicenca();
        operacija.izvrsi(l, "create");
        return operacija.getValid();
    }

    public boolean promeniLicenca(Licenca l) throws Exception {
        PromeniLicenca operacija = new PromeniLicenca();
        operacija.izvrsi(l, "update");
        return operacija.getValid();
    }

    public boolean obrisiLicenca(Licenca l) throws Exception {
        ObrisiLicenca operacija = new ObrisiLicenca();
        operacija.izvrsi(l, "delete");
        return operacija.getValid();
    }

    public List<Licenca> vratiListuSviLicenca(Licenca l) throws Exception {
        VratiListuSviLicenca operacija = new VratiListuSviLicenca();
        operacija.izvrsi(l, "read");
        return operacija.getList();
    }
    
    public List<Licenca> vratiListuLicenca(Licenca l) throws Exception {
        VratiListuLicenca operacija = new VratiListuLicenca();
        operacija.izvrsi(l, "read");
        return operacija.getList();
    }
    
    //INSTRUKTORLICENCA SO
    public List<InstruktorLicenca> vratiListuInstruktorLicenca(Instruktor i) throws Exception {
        VratiListuInstruktorLicenca operacija = new VratiListuInstruktorLicenca();
        operacija.izvrsi(i, "read");
        return operacija.getList();
    }
    
    public List<InstruktorLicenca> vratiListuSviInstruktorLicenca(InstruktorLicenca il) throws Exception {
        VratiListuSviInstruktorLicenca operacija = new VratiListuSviInstruktorLicenca();
        operacija.izvrsi(il, "read");
        return operacija.getList();
    }

    public boolean obrisiInstruktorLicenca(InstruktorLicenca il) throws Exception {
        ObrisiInstruktorLicenca operacija = new ObrisiInstruktorLicenca();
        operacija.izvrsi(il, "delete");
        return operacija.getValid();
    }

    public boolean kreirajInstruktorLicenca(InstruktorLicenca il) throws Exception {
        KreirajInstruktorLicenca operacija = new KreirajInstruktorLicenca();
        operacija.izvrsi(il, "create");
        return operacija.getValid();
    }
    
    //NIVO_SKIJANJA
    public boolean kreirajNivoSkijanja(NivoSkijanja ns) throws Exception {
        KreirajNivoSkijanja operacija = new KreirajNivoSkijanja();
        operacija.izvrsi(ns, "create");
        return operacija.getValid();
    }

    public List<NivoSkijanja> vratiListuSviNivoSkijanja(NivoSkijanja ns) throws Exception {
        VratiListuSviNivoSkijanja operacija = new VratiListuSviNivoSkijanja();
        operacija.izvrsi(ns, "read");
        return operacija.getList();
    }

    public List<NivoSkijanja> vratiListuNivoSkijanja(NivoSkijanja ns) throws Exception {
        VratiListuNivoSkijanja operacija = new VratiListuNivoSkijanja();
        operacija.izvrsi(ns, "read");
        return operacija.getList();    
    }

    public boolean obrisiNivoSkijanja(NivoSkijanja ns) throws Exception {
        ObrisiNivoSkijanja operacija = new ObrisiNivoSkijanja();
        operacija.izvrsi(ns, "delete");
        return operacija.getValid();
    }

    public boolean promeniNivoSkijanja(NivoSkijanja ns) throws Exception {
        PromeniNivoSkijanja operacija = new PromeniNivoSkijanja();
        operacija.izvrsi(ns, "update");
        return operacija.getValid();
    }
    
    //TIP_TERMINA
    public boolean kreirajTipTermina(TipTermina tt) throws Exception {
        KreirajTipTermina operacija = new KreirajTipTermina();
        operacija.izvrsi(tt,"create");
        return operacija.getValid();
    }

    public List<TipTermina> vratiListuSviTipTermina(TipTermina tt) throws Exception {
        VratiListuSviTipTermina operacija = new VratiListuSviTipTermina();
        operacija.izvrsi(tt, "read");
        return operacija.getList(); 
    }

    public List<TipTermina> vratiListuTipTermina(TipTermina tt) throws Exception {
        VratiListuTipTermina operacija = new VratiListuTipTermina();
        operacija.izvrsi(tt, "read");
        return operacija.getList(); 
    }
    public boolean promeniTipTermina(TipTermina tt) throws Exception {
        PromeniTipTermina operacija = new PromeniTipTermina();
        operacija.izvrsi(tt, "update");
        return operacija.getValid();    
    }

    public boolean obrisiTipTermina(TipTermina tt) throws Exception {
        ObrisiTipTermina operacija = new ObrisiTipTermina();
        operacija.izvrsi(tt, "delete");
        return operacija.getValid();  
    }
    
    
    //SKIJAS
    public List<Skijas> vratiListuSviSkijas(Skijas s) throws Exception {
        VratiListuSviSkijas operacija = new VratiListuSviSkijas();
        operacija.izvrsi(s, "read");
        return operacija.getList();
    }

    public List<Skijas> vratiListuSkijas(Skijas s) throws Exception {
        VratiListuSkijas operacija = new VratiListuSkijas();
        operacija.izvrsi(s, "read");
        return operacija.getList();
    }

    public boolean kreirajSkijas(Skijas s) throws Exception {
        KreirajSkijas operacija = new KreirajSkijas();
        operacija.izvrsi(s, "create");
        return operacija.getValid();
    }

    public boolean obrisiSkijas(Skijas s) throws Exception {
        ObrisiSkijas operacija = new ObrisiSkijas();
        operacija.izvrsi(s, "delete");
        return operacija.getValid();    }

    public boolean promeniSkijas(Skijas s) throws Exception {
        PromeniSkijas operacija = new PromeniSkijas();
        operacija.izvrsi(s, "update");
        return operacija.getValid();    }
    
    
    //TERMIN
    public List<Termin> vratiListuSviTermin(Termin t) throws Exception{
        VratiListuSviTermin operacija = new VratiListuSviTermin();
        operacija.izvrsi(t, "read");
        return operacija.getList();
    }

    public boolean kreirajTermin(Termin t) throws Exception{
       KreirajTermin operacija = new KreirajTermin();
       operacija.izvrsi(t, "create");
       return operacija.getValid();
    }

    public List<Termin> vratiListuTermin(Termin t) throws Exception{
        VratiListuTermin operacija = new VratiListuTermin();
        operacija.izvrsi(t, "read");
        return operacija.getList();
    }

    public boolean promeniTermin(Termin t) throws Exception{
        PromeniTermin operacija = new PromeniTermin();
       operacija.izvrsi(t, "update");
       return operacija.getValid();
    }

    public boolean obrisiTermin(Termin t) throws Exception{
       ObrisiTermin operacija = new ObrisiTermin();
       operacija.izvrsi(t, "delete");
       return operacija.getValid();
    }

    
    //TERMIN_SKIJAS
    public List<TerminSkijas> vratiListuTerminSkijas(Termin t) throws Exception{
        VratiListuTerminSkijas operacija = new VratiListuTerminSkijas();
        operacija.izvrsi(t, "read");
        return operacija.getList();
    }
    
    public List<TerminSkijas> vratiListuSviTerminSkijas(TerminSkijas ts) throws Exception{
        VratiListuSviTerminSkijas operacija = new VratiListuSviTerminSkijas();
        operacija.izvrsi(ts, "read");
        return operacija.getList();
    }
//    public boolean kreirajTerminSkijas(TerminSkijas t) throws Exception {
//       KreirajTerminSkijas operacija = new KreirajTerminSkijas();
//       operacija.izvrsi(t, "create");
//       return operacija.getValid();
//    }
    public boolean kreirajTerminSkijas(List<TerminSkijas> t) throws Exception {
       KreirajTerminSkijas operacija = new KreirajTerminSkijas();
       operacija.izvrsi(t, "create");
       return operacija.getValid();
    }

    public boolean obrisiTerminSkijas(TerminSkijas t) throws Exception {
       ObrisiTerminSkijas operacija = new ObrisiTerminSkijas();
       operacija.izvrsi(t, "delete");
       return operacija.getValid();
    }

    

    
}
