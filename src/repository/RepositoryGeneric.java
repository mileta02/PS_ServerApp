/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
/**
 *
 * @author milan
 */
public interface RepositoryGeneric<T> {
    List<T> read(T param) throws Exception;
    List<T> readWithCondition(T param, List<T> list) throws Exception;
    boolean create(T param) throws Exception;
    boolean update(T param) throws Exception;
    boolean delete(T param) throws Exception;    
    boolean doesExistForCreate(T param) throws Exception;
    boolean doesExistForUpdate(T param) throws Exception;
}
