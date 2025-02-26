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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        try {
            Request request = (Request) receiver.receive();
            Response response = new Response();
            
            switch(request.getOperation()){
                case Operation.LOGIN: System.out.println("Operacija login");
                    break;
               
            }
            
            sender.send(response);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
