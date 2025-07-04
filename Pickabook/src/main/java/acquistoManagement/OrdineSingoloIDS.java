package acquistoManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import catalogoManagement.Prodotto;

/**
 * Implementazione concreta dell'interfaccia OrdineSingoloDAO usando JDBC.
 * Utilizza un DataSource per la connessione al database.
 */
public class OrdineSingoloIDS implements OrdineSingoloDAO {

    private DataSource ds = null;         // DataSource per la connessione DB
    private Connection connection = null; // Connessione al DB mantenuta internamente

    // Costruttore che inizializza il DataSource e la connessione
    public OrdineSingoloIDS(DataSource ds) {
        super();
        this.ds = ds;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            logger.log(Level.ALL, ERROR, e);
        }
    }

    /**
     * Inserisce un nuovo OrdineSingolo nel database.
     */
    @Override
    public Boolean doSaveOrdineSingolo(OrdineSingolo ordineSingolo) throws SQLException {
        String query = "INSERT INTO " + OrdineSingoloIDS.TABLE
                + " (quantità, totale_parziale, ordini_id, immagine_prodotto, nome_prodotto) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ordineSingolo.getQuantita());
            preparedStatement.setDouble(2, ordineSingolo.getTotParziale());
            preparedStatement.setInt(3, ordineSingolo.getOrdineId());
            preparedStatement.setString(4, ordineSingolo.getProdotto().getImmagine());
            preparedStatement.setString(5, ordineSingolo.getProdotto().getNome());
            
            if(preparedStatement.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            logger.log(Level.ALL, ERROR, e);
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Elimina un OrdineSingolo dal database dato il suo id.
     */
    @Override
    public Boolean doDeleteOrdineSingolo(Integer id) throws SQLException {
        String query = "DELETE FROM " + OrdineSingoloIDS.TABLE + " WHERE id = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() > 0)
                return true;

        } catch (SQLException e) {
            logger.log(Level.ALL, ERROR, e);
        }
        return false;
    }

    /**
     * Aggiorna i dati di un OrdineSingolo già presente.
     */
    @Override
    public Boolean doUpdateOrdineSingolo(OrdineSingolo ordineSingolo) throws SQLException {
        String query = "UPDATE " + OrdineSingoloIDS.TABLE
                + " SET quantità = ?, totale_parziale = ?, ordini_id = ?, immagine_prodotto = ?, nome_prodotto = ? WHERE id = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ordineSingolo.getQuantita());
            preparedStatement.setDouble(2, ordineSingolo.getTotParziale());
            preparedStatement.setInt(3, ordineSingolo.getOrdineId());
            preparedStatement.setString(4, ordineSingolo.getProdotto().getImmagine());
            preparedStatement.setString(5, ordineSingolo.getProdotto().getNome());
            preparedStatement.setInt(6, ordineSingolo.getId());

            if (preparedStatement.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            logger.log(Level.ALL, ERROR, e);
        }
        return false;
    }

    /**
     * Recupera tutti gli OrdiniSingoli dal database.
     */
    @Override
    public Collection<OrdineSingolo> doRetrieveAllOrdineSingolo() throws SQLException {
        String query = "SELECT * FROM " + OrdineSingoloIDS.TABLE;
        ArrayList<OrdineSingolo> ordiniSingoli = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery();) {

            while (rs.next()) {
                Integer id = rs.getInt(ID);
                Integer quantita = rs.getInt(QUANTITA);
                Double totParziale = rs.getDouble(TOT_PARZIALE);
                Integer ordineId = rs.getInt(ORDINE_ID);
                String imagePath = rs.getString(IMMAGINE_PRODOTTO);
                String nomeProdotto = rs.getString(NOME_PRODOTTO);

                // Creazione prodotto basato su nome e immagine
                Prodotto prodotto = new Prodotto(nomeProdotto, imagePath);

                ordiniSingoli.add(new OrdineSingolo(id, quantita, totParziale, ordineId, prodotto));
            }
        } catch (SQLException e) {
            logger.log(Level.ALL, ERROR, e);
        }

        return ordiniSingoli;
    }

    /**
     * Recupera tutti gli OrdiniSingoli associati ad un determinato ordine (ordineId).
     */
    @Override
    public Collection<OrdineSingolo> doRetrieveAllByOrdineId(Integer ordineId) throws SQLException {
        String query = "SELECT * FROM " + OrdineSingoloIDS.TABLE + " WHERE ordini_id = ?";
        ArrayList<OrdineSingolo> ordiniSingoli = new ArrayList<>();

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ordineId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(ID);
                Integer quantita = rs.getInt(QUANTITA);
                Double totParziale = rs.getDouble(TOT_PARZIALE);
                String imagePath = rs.getString(IMMAGINE_PRODOTTO);
                String nomeProdotto = rs.getString(NOME_PRODOTTO);

                Prodotto prodotto = new Prodotto(nomeProdotto, imagePath);

                ordiniSingoli.add(new OrdineSingolo(id, quantita, totParziale, ordineId, prodotto));
            }
            rs.close();
        } catch (SQLException e) {
            logger.log(Level.ALL, ERROR, e);
        }

        return ordiniSingoli;
    }

    /**
     * Recupera un OrdineSingolo dato il suo id.
     */
    @Override
    public OrdineSingolo doRetrieveById(Integer id) throws SQLException {
        String query = "SELECT * FROM " + OrdineSingoloIDS.TABLE + " WHERE id = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Integer quantita = rs.getInt(QUANTITA);
                Double totParziale = rs.getDouble(TOT_PARZIALE);
                Integer ordineId = rs.getInt(ORDINE_ID);
                String imagePath = rs.getString(IMMAGINE_PRODOTTO);
                String nomeProdotto = rs.getString(NOME_PRODOTTO);

                Prodotto prodotto = new Prodotto(nomeProdotto, imagePath);

                return new OrdineSingolo(id, quantita, totParziale, ordineId, prodotto);
            }

            rs.close();

        } catch (SQLException e) {
            logger.log(Level.ALL, ERROR, e);
        }

        return null;
    }

    /*** Costanti per nomi tabelle e colonne ***/
    private static final String TABLE = "ordine_singolo";
    private static final String ID = "id";
    private static final String QUANTITA = "quantità";
    private static final String TOT_PARZIALE = "totale_parziale";
    private static final String ORDINE_ID = "ordini_id";
    private static final String IMMAGINE_PRODOTTO = "immagine_prodotto";
    private static final String NOME_PRODOTTO = "nome_prodotto";
    
    /*** Logger per gestione errori ***/
    private static final Logger logger = Logger.getLogger(OrdineSingoloIDS.class.getName());
    private static final String ERROR = "Errore";
}
