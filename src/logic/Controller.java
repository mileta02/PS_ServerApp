/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import database.DBBroker;

/**
 *
 * @author milan
 */
public class Controller {
    private static Controller instance;
    private DBBroker broker;
    
    private Controller(){
        broker = new DBBroker();
    }

    public static Controller getInstance() {
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    
}
