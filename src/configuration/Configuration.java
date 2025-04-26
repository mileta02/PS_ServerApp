/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Properties;

/**
 *
 * @author milan
 */
public class Configuration {

    private static Configuration instance;
    private Properties config = new Properties();
    private static final String CONFIG_PATH = "server_config.properties";

    private Configuration() {
        if(doesExist())
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                config.load(fis);
            } catch (IOException e) {
                System.out.println("Greska pri ucitavanju konfiguracije: " + e.getMessage());
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty(String key) {
        return config.getProperty(key);
    }

    public void setProperty(String key, String value){
        config.setProperty(key, value);
        saveProperties();
    }
    
    public void saveProperties() {
    try (FileOutputStream fos = new FileOutputStream(CONFIG_PATH)) {
        config.store(fos, "Azurirana konfiguracija");
    } catch (IOException ex) {
        System.out.println("Greska u konfiguraciji: " + ex.getMessage());
    }
}
    
    public boolean doesExist(){
        File file = new File(CONFIG_PATH);
        return file.exists();
    }

    public void createFile() {
        File file = new File(CONFIG_PATH);
        try {
            file.createNewFile();
            System.out.println("Konfiguracioni fajl kreiran.");
        } catch (IOException ex) {
            System.out.println("Greska prilikom kreiranja konfiguracionog fajla: " + ex.getMessage());
        }
    }

}
