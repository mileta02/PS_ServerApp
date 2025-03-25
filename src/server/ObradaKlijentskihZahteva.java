/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controller;
import model.Instruktor;
import model.InstruktorLicenca;
import model.Licenca;
import model.NivoSkijanja;
import model.Skijas;
import model.Termin;
import model.TerminSkijas;
import model.TipTermina;

/**
 *
 * @author milan
 */
public class ObradaKlijentskihZahteva extends Thread {
    private Socket clientSocket;
    private Sender sender;
    private Receiver receiver;
    
    public ObradaKlijentskihZahteva(Socket clientSocket) {
        this.clientSocket=clientSocket;
        sender = new Sender(clientSocket);
        receiver = new Receiver(clientSocket);
    }

    @Override
    public void run() {
        while(true){
        try {
            Request request = (Request) receiver.receive();
            Response response = new Response();
            try{
            switch(request.getOperation()){
                case Operation.LOGIN: 
                    login(request,response);
                    break;
                case Operation.KREIRAJ_INSTRUKTOR:
                    kreirajInstruktor(request,response);
                    break;
                case Operation.PROMENI_INSTRUKTOR:
                    promeniInstruktor(request,response);
                    break;
                case Operation.OBRISI_INSTRUKTOR:
                    obrisiInstruktor(request,response);
                    break;
                case Operation.UCITAJ_INSTRUKTOR:
                    vratiListuSviInstruktor(request,response);
                    break;
                case Operation.UCITAJ_INSTRUKTOR_FILTER:
                    vratiListuInstruktor(request,response);
                    break;
                case Operation.KREIRAJ_LICENCA:
                    kreirajLicenca(request,response);
                    break;
                case Operation.PROMENI_LICENCA:
                    promeniLicenca(request,response);
                    break;
                case Operation.OBRISI_LICENCA:
                    obrisiLicenca(request,response);
                    break;
                case Operation.UCITAJ_LICENCA:
                    vratiListuSviLicenca(request,response);
                    break;
                case Operation.UCITAJ_LICENCA_FILTER:
                    vratiListuLicenca(request,response);
                    break;
                case Operation.UCITAJ_INSTRUKTOR_LICENCA_FILTER:
                    vratiListuInstruktorLicencaFilter(request,response);
                    break;
                case Operation.UCITAJ_INSTRUKTOR_LICENCA:
                    vratiListuInstruktorLicenca(request,response);
                    break;
                case Operation.OBRISI_INSTRUKTOR_LICENCA:
                    obrisiInstruktorLicenca(request, response);
                    break;
                case Operation.KREIRAJ_INSTRUKTOR_LICENCA:
                    kreirajInstruktorLicenca(request, response);
                    break;
                case Operation.KREIRAJ_NIVO_SKIJANJA:
                    kreirajNivoSkijanja(request,response);
                    break;
                case Operation.UCITAJ_NIVO_SKIJANJA:
                    vratiListuSviNivoSkijanja(request,response);
                    break;
                case Operation.UCITAJ_NIVO_SKIJANJA_FILTER:
                    vratiListuNivoSkijanja(request,response);
                    break;
                case Operation.OBRISI_NIVO_SKIJANJA:
                    obrisiNivoSkijanja(request,response);
                    break;
                case Operation.PROMENI_NIVO_SKIJANJA:
                    promeniNivoSkijanja(request,response);
                    break;
                case Operation.KREIRAJ_TIP_TERMINA:
                    kreirajTipTermina(request,response);
                    break;
                case Operation.UCITAJ_TIP_TERMINA:
                    vratiListuSviTipTermina(request,response);
                    break;
                case Operation.UCITAJ_TIP_TERMINA_FILTER:
                    vratiListuTipTermina(request,response);
                    break;
                case Operation.PROMENI_TIP_TERMINA:
                    promeniTipTermina(request,response);
                    break;
                case Operation.OBRISI_TIP_TERMINA:
                    obrisiTipTermina(request,response);
                    break;
                case Operation.UCITAJ_SKIJAS:
                    vratiListuSviSkijas(request,response);
                    break;
                case Operation.UCITAJ_SKIJAS_FILTER:
                    vratiListuSkijas(request, response);
                    break;
                case Operation.KREIRAJ_SKIJAS:
                    kreirajSkijas(request,response);
                    break;
                case Operation.OBRISI_SKIJAS:
                    obrisiSkijas(request,response);
                    break;
                case Operation.PROMENI_SKIJAS:
                    promeniSkijas(request,response);
                    break;
                case Operation.UCITAJ_TERMIN:
                    vratiListuSviTermin(request,response);
                    break;
                case Operation.UCITAJ_TERMIN_FILTER:
                    vratiListuTermin(request,response);
                    break;
                case Operation.KREIRAJ_TERMIN:
                    kreirajTermin(request,response);
                    break;
                case Operation.PROMENI_TERMIN:
                    promeniTermin(request,response);
                    break;
                case Operation.OBRISI_TERMIN:
                    obrisiTermin(request,response);
                    break;
                case Operation.UCITAJ_TERMIN_SKIJAS:
                    vratiListuTerminSkijas(request,response);
                    break;
                case Operation.KREIRAJ_TERMIN_SKIJAS:
                    kreirajTerminSkijas(request,response);
                    break;
                case Operation.PROMENI_TERMIN_SKIJAS:
                    promeniTerminSkijas(request,response);
                    break;
                case Operation.OBRISI_TERMIN_SKIJAS:
                    obrisiTerminSkijas(request,response);
                    break;
                default:System.out.println("Operacija ne postoji.");;
                        
            }
            }catch(Exception ex){
                response.setException(ex);
            }
            sender.send(response);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    
    //INSTRUKTOR
    private void login(Request request, Response response) throws Exception {
        System.out.println("Operacija login");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().login(i));
    }

    private void kreirajInstruktor(Request request, Response response) throws Exception {
        System.out.println("Operacija kreiraj instruktor");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().kreirajInstruktor(i));
    }

    private void promeniInstruktor(Request request, Response response) throws Exception {
        System.out.println("Operacija promeni instruktor");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().promeniInstruktor(i));
    }

    private void obrisiInstruktor(Request request, Response response) throws Exception {

        System.out.println("Operacija obrisi instruktor");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().obrisiInstruktor(i));    
    }

    private void vratiListuSviInstruktor(Request request, Response response) throws Exception {
        System.out.println("Operacija vrati listu instruktora.");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuSviInstruktor(i));
    }

    private void vratiListuInstruktor(Request request, Response response) throws Exception {
        System.out.println("Operacija vrati listu instruktora sa filterom.");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuInstruktor(i));
    }

    //LICENCA
    private void kreirajLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija kreiraj licencu");
        Licenca l = (Licenca) request.getArgument();
        response.setResult(Controller.getInstance().kreirajLicenca(l));
    }

    private void promeniLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija promeni licencu");
        Licenca l = (Licenca) request.getArgument();
        response.setResult(Controller.getInstance().promeniLicenca(l));    }

    private void obrisiLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija obrisi licencu");
        Licenca l = (Licenca) request.getArgument();
        response.setResult(Controller.getInstance().obrisiLicenca(l));
    }

    private void vratiListuSviLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija vrati listu licenci");
        Licenca l = (Licenca) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuSviLicenca(l));    }
    
    private void vratiListuLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija vrati listu licenci");
        Licenca l = (Licenca) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuLicenca(l));    
    }
    //INSTRUKTOR_LICENCA
    private void vratiListuInstruktorLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija vracanje licenci instruktora");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuInstruktorLicenca(i));
    }
    
    private void vratiListuInstruktorLicencaFilter(Request request, Response response) throws Exception {
        System.out.println("Operacija vracanje licenci instruktora sa filterom");
        InstruktorLicenca il = (InstruktorLicenca) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuInstruktorLicencaFilter(il));
    }
    
    private void obrisiInstruktorLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija brisanja licenci instruktora");
        InstruktorLicenca il = (InstruktorLicenca) request.getArgument();
        response.setResult(Controller.getInstance().obrisiInstruktorLicenca(il));
    }

    private void kreirajInstruktorLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija dodavanja licenci instruktoru");
        InstruktorLicenca il = (InstruktorLicenca) request.getArgument();
        response.setResult(Controller.getInstance().kreirajInstruktorLicenca(il));
        }

    //NIVO_SKIJANJA
    private void kreirajNivoSkijanja(Request request, Response response) throws Exception{
        System.out.println("Operacija kreiraj nivo skijanja");
        NivoSkijanja ns = (NivoSkijanja) request.getArgument();
        response.setResult(Controller.getInstance().kreirajNivoSkijanja(ns));
    }
    private void vratiListuSviNivoSkijanja(Request request, Response response) throws Exception{
        System.out.println("Operacija vrati listu nivo skijanja");
        NivoSkijanja ns = (NivoSkijanja) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuSviNivoSkijanja(ns));
    }

    private void vratiListuNivoSkijanja(Request request, Response response) throws Exception{
        System.out.println("Operacija vrati listu nivo skijanja sa filterom");
        NivoSkijanja ns = (NivoSkijanja) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuNivoSkijanja(ns));
    }

    private void obrisiNivoSkijanja(Request request, Response response) throws Exception{
        System.out.println("Operacija obrisi nivo skijanja");
        NivoSkijanja ns = (NivoSkijanja) request.getArgument();
        response.setResult(Controller.getInstance().obrisiNivoSkijanja(ns));
    }

    private void promeniNivoSkijanja(Request request, Response response) throws Exception{
        System.out.println("Operacija promeni nivo skijanja");
        NivoSkijanja ns = (NivoSkijanja) request.getArgument();
        response.setResult(Controller.getInstance().promeniNivoSkijanja(ns));
    }
    
    //TIP_TERMINA
    private void kreirajTipTermina(Request request, Response response) throws Exception{
        System.out.println("Operacija kreiraj tip termina");
        TipTermina tt = (TipTermina) request.getArgument();
        response.setResult(Controller.getInstance().kreirajTipTermina(tt));
    }

    private void vratiListuTipTermina(Request request, Response response) throws Exception {
        System.out.println("Operacija vrati listu tipova termina sa filterom");
        TipTermina tt = (TipTermina) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuTipTermina(tt));
    }

    private void vratiListuSviTipTermina(Request request, Response response) throws Exception {
        System.out.println("Operacija vrati listu svih tipova termina");
        TipTermina tt = (TipTermina) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuSviTipTermina(tt));
    }

    private void promeniTipTermina(Request request, Response response) throws Exception{
        System.out.println("Operacija promeni tip termina");
        TipTermina tt = (TipTermina) request.getArgument();
        response.setResult(Controller.getInstance().promeniTipTermina(tt));
    }

    private void obrisiTipTermina(Request request, Response response) throws Exception{
        System.out.println("Operacija obrisi tip termina");
        TipTermina tt = (TipTermina) request.getArgument();
        response.setResult(Controller.getInstance().obrisiTipTermina(tt));
    }
    
    //SKIJAS

    private void vratiListuSviSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija vrati listu svih skijasa.");
        Skijas s = (Skijas) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuSviSkijas(s));
    }

    private void kreirajSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija kreiraj skijasa.");
        Skijas s = (Skijas) request.getArgument();
        response.setResult(Controller.getInstance().kreirajSkijas(s));
    }

    private void vratiListuSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija vrati listu skijasa sa filterom.");
        Skijas s = (Skijas) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuSkijas(s));
    }

    private void obrisiSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija obrisi skijasa.");
        Skijas s = (Skijas) request.getArgument();
        response.setResult(Controller.getInstance().obrisiSkijas(s));
    }

    private void promeniSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija promeni skijasa.");
        Skijas s = (Skijas) request.getArgument();
        response.setResult(Controller.getInstance().promeniSkijas(s));
    }
    
    //TERMIN
    private void vratiListuSviTermin(Request request, Response response) throws Exception{
        System.out.println("Operacija vrati listu svih termina.");
        Termin t = (Termin) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuSviTermin(t));
    }

    private void kreirajTermin(Request request, Response response) throws Exception{
        System.out.println("Operacija kreiraj termin.");
        Termin t = (Termin) request.getArgument();
        response.setResult(Controller.getInstance().kreirajTermin(t));
    }

    private void vratiListuTermin(Request request, Response response) throws Exception{
        System.out.println("Operacija vrati listu termina sa filterom.");
        Termin t = (Termin) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuTermin(t));
    }

    private void promeniTermin(Request request, Response response) throws Exception{
        System.out.println("Operacija promeni termin.");
        Termin t = (Termin) request.getArgument();
        response.setResult(Controller.getInstance().promeniTermin(t));
    }

    private void obrisiTermin(Request request, Response response) throws Exception{
        System.out.println("Operacija obrisi termin.");
        Termin t = (Termin) request.getArgument();
        response.setResult(Controller.getInstance().obrisiTermin(t));
    }

    
    //TERMIN_SKIJAS
    private void vratiListuTerminSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija vrati listu termin skijas.");
        Termin t = (Termin) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuTerminSkijas(t));
    }

    private void kreirajTerminSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija kreiraj termin skijas.");
        TerminSkijas t = (TerminSkijas) request.getArgument();
        response.setResult(Controller.getInstance().kreirajTerminSkijas(t));
    }

    private void promeniTerminSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija promeni termin skijas.");
        TerminSkijas t = (TerminSkijas) request.getArgument();
        response.setResult(Controller.getInstance().promeniTerminSkijas(t));
    }

    private void obrisiTerminSkijas(Request request, Response response) throws Exception{
        System.out.println("Operacija obrisi termin skijas.");
        TerminSkijas t = (TerminSkijas) request.getArgument();
        response.setResult(Controller.getInstance().obrisiTerminSkijas(t));
    }

    
}
