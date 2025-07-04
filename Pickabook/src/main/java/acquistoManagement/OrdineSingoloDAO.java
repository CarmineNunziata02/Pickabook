package acquistoManagement;

import java.sql.SQLException;
import java.util.Collection;

import catalogoManagement.Prodotto;

/**
 * Interfaccia DAO per la gestione delle operazioni CRUD sul singolo ordine (OrdineSingolo).
 */
public interface OrdineSingoloDAO {

    public Boolean doSaveOrdineSingolo(OrdineSingolo ordineSingolo) throws SQLException;


    public Boolean doDeleteOrdineSingolo(Integer id) throws SQLException;


    public Boolean doUpdateOrdineSingolo(OrdineSingolo ordineSingolo) throws SQLException;

    public Collection<OrdineSingolo> doRetrieveAllOrdineSingolo() throws SQLException;

    public Collection<OrdineSingolo> doRetrieveAllByOrdineId(Integer ordineId) throws SQLException;

    public OrdineSingolo doRetrieveById(Integer id) throws SQLException;
}
