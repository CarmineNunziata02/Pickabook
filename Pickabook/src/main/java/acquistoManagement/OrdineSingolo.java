package acquistoManagement;

import java.io.Serializable;
import catalogoManagement.Prodotto;

public class OrdineSingolo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;               // Identificativo univoco del singolo ordine
    private Integer quantita;         // Quantità del prodotto ordinato
    private Double totParziale;       // Totale parziale relativo a questo singolo ordine
    private Integer ordineId;         // ID dell'ordine a cui questo singolo ordine appartiene
    private Prodotto prodotto;        // Prodotto associato a questo ordine singolo

    public OrdineSingolo() {
        super();
    }

    public OrdineSingolo(Integer id, Integer quantita, Double totParziale, Integer ordineId, Prodotto prodotto) {
        super();
        this.id = id;
        this.quantita = quantita;
        this.totParziale = totParziale;
        this.ordineId = ordineId;
        this.prodotto = prodotto;
    }
    

    public OrdineSingolo(Integer quantita, Double totParziale, Integer ordineId, Prodotto prodotto) {
        super();
        this.quantita = quantita;
        this.totParziale = totParziale;
        this.ordineId = ordineId;
        this.prodotto = prodotto;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuantita() { return quantita; }
    public void setQuantita(Integer quantita) { this.quantita = quantita; }
    public Double getTotParziale() { return totParziale; }
    public void setTotParziale(Double totParziale) { this.totParziale = totParziale; }
    public Integer getOrdineId() { return ordineId; }
    public void setOrdineId(Integer ordineId) { this.ordineId = ordineId; }
    public Prodotto getProdotto() { return prodotto; }
    public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }

    @Override
    public String toString() {
        return "OrdineSingolo [id=" + id + ", quantita=" + quantita + ", totParziale=" + totParziale + ", ordineId="
                + ordineId + ", prodotto=" + prodotto + "]";
    }
}
