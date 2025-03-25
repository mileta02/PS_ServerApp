/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Instruktor;
import model.InstruktorLicenca;
import model.Skijas;
import model.Termin;
import model.TerminSkijas;

/**
 *
 * @author milan
 */
public interface Repository<T> {
    List<T> read(T param) throws Exception;
    List<T> readWithCondition(T param) throws Exception;
    boolean create(T param) throws Exception;
    boolean update(T param) throws Exception;
    boolean delete(T param) throws Exception;    
    boolean doesExist(T param);
    List<InstruktorLicenca> readInstruktorWithLicenca(Instruktor i);
    List<InstruktorLicenca> readInstruktorWithLicencaWithCondition(InstruktorLicenca il);
    List<Skijas> readSkijasWithNivoSkijanja(Skijas s);
    List<Skijas> readSkijasWithNivoSkijanjaWithCondition(Skijas s);
    List<Termin> readTerminWithInstruktorWithTipTermina(Termin t);
    List<Termin> readTerminWithInstruktorWithCondition(Termin t);
    List<TerminSkijas> readTerminWithSkijas(Termin t);
}
