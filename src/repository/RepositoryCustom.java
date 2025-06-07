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
public interface RepositoryCustom {
    List<InstruktorLicenca> readInstruktorWithLicenca(Instruktor i, List<InstruktorLicenca> list) throws Exception;
    List<Skijas> readSkijasWithNivoSkijanja(Skijas s, List<Skijas> list) throws Exception;
    List<Skijas> readSkijasWithNivoSkijanjaWithCondition(Skijas s, List<Skijas> list) throws Exception;
    List<Termin> readTerminWithInstruktorWithTipTermina(Termin t, List<Termin> list) throws Exception;
    List<Termin> readTerminWithInstruktorWithTipTerminaWithCondition(Termin t, List<Termin> list) throws Exception;
    List<TerminSkijas> readTerminWithSkijas(Termin t, List<TerminSkijas> list) throws Exception;
    boolean saveTerminWithSkijasi(List<TerminSkijas> list) throws Exception;
}
