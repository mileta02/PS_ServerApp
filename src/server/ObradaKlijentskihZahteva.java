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
                    System.out.println("Operacija login");
                    Instruktor i = (Instruktor) request.getArgument();
                    response.setResult(Controller.getInstance().login(i));
                    break;
                case Operation.REGISTER:
                    System.out.println("Operacija register");
                    Instruktor in = (Instruktor) request.getArgument();
                    response.setResult(Controller.getInstance().register(in));
                    break;
                case Operation.EDIT_INSTRUKTOR:
                    System.out.println("Operacija izmeni instruktor");
                    Instruktor ins = (Instruktor) request.getArgument();
                    response.setResult(Controller.getInstance().editInstructor(ins));
                    break;
                case Operation.DELETE_INSTRUKTOR:
                    System.out.println("Operacija obrisi instruktor");
                    Instruktor inst = (Instruktor) request.getArgument();
                    response.setResult(Controller.getInstance().deleteInstructor(inst));
                    break;
                case Operation.VRATI_LISTU_INSTRUKTOR:
                    System.out.println("Operacija vrati listu instruktora.");
                    Instruktor instr = (Instruktor) request.getArgument();
                    response.setResult(Controller.getInstance().getInstructorList(instr));
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
    
    
    
}
