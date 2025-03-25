/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.sql.SQLException;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.imp.DbRepositoryGeneric;

/**
 *
 * @author milan
 */
public abstract class ApstraktnaGenerickaOperacija {
    
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object obj, String key) throws Exception {

        try {
            preduslovi(obj);
            zaspocniTransakciju();
            izvrsiOperaciju(obj);
            if(!key.equalsIgnoreCase("read") && !key.equalsIgnoreCase("login") )
                potvrdiTransakciju(obj, key);
        } catch (Exception ex) {
            ponistiTransakciju();
            throw ex;
        } finally {
             //ugasiKonekciju();
        }
    }

    protected abstract void preduslovi(Object obj) throws Exception;

    private void zaspocniTransakciju() {
        ((DbRepository) broker).connect();
    }

    protected abstract void izvrsiOperaciju(Object obj) throws Exception;

    private void potvrdiTransakciju(Object object, String kljuc) throws SQLException {
        ((DbRepository) broker).commit();
    }

    private void ponistiTransakciju() throws SQLException {
        ((DbRepository) broker).rollback();
    }

    private void ugasiKonekciju() throws SQLException {
        ((DbRepository) broker).disconnect();
    }
    
}
