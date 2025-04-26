/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import configuration.Configuration;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ObradaKlijentskihZahteva;

/**
 *
 * @author milan
 */
public class Server extends Thread{
    private boolean end = false;
    private List<ObradaKlijentskihZahteva> list;
    private ServerSocket serverSocket;
    public Server(){
        list = new ArrayList<>();
    }
    @Override
    public void run() {
        try {
            int port = Integer.parseInt(Configuration.getInstance().getProperty("port"));
            serverSocket = new ServerSocket(port);
            System.out.println("Server ceka klijente na portu: "+port);

            while(!end){
                try{
                Socket clientSocket = serverSocket.accept();
                System.out.println("Klijent: "+clientSocket + " se konektovao.");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(clientSocket);
                list.add(okz);
                okz.start();
                } catch (SocketException e) {
                    if (end) {
                        System.out.println("Server je zatvoren.");
                        break;
                    }
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                };
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void zaustavi() throws IOException{
        for(ObradaKlijentskihZahteva okz : list){
            okz.zaustavi();
        }
        end=true;
        serverSocket.close();
    }
    
    }
    
   
        
    

