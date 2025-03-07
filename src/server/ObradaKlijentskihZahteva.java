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
                case Operation.UCITAJ_INSTRUKTOR_LICENCA_FILTER:
                    vratiListuInstruktorLicenca(request,response);
                    break;
                case Operation.OBRISI_INSTRUKTOR_LICENCA:
                    obrisiInstruktorLicenca(request, response);
                    break;
                case Operation.KREIRAJ_INSTRUKTOR_LICENCA:
                    kreirajInstruktorLicenca(request, response);
                    break;
                        
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
    
    //INSTRUKTORLICENCA
    private void vratiListuInstruktorLicenca(Request request, Response response) throws Exception {
        System.out.println("Operacija vracanje licenci instruktora");
        Instruktor i = (Instruktor) request.getArgument();
        response.setResult(Controller.getInstance().vratiListuInstruktorLicenca(i));
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
}
