// Package in cui si trova la classe
package catalogoManagement;

// Import per la serializzazione e metodi utility
import java.io.Serializable;
import java.util.Objects;

// Classe che rappresenta un prodotto (es. libro) e che implementa Serializable per supportare la serializzazione
public class Prodotto implements Serializable {

	// Versione UID per la serializzazione
	private static final long serialVersionUID = 1L;

	// Attributi del prodotto
	private String isbn;          // Codice ISBN identificativo
	private String nome;          // Titolo o nome del prodotto
	private String autore;        // Autore del libro
	private String descrizione;   // Descrizione del contenuto
	private String immagine;      // Percorso o URL dell'immagine
	private Double prezzo;        // Prezzo del prodotto
	private Integer quantita;     // Quantità disponibile in magazzino
	private String genere;        // Genere letterario
	private String categoria;     // Categoria generale
	private Integer copieVendute; // Numero di copie vendute

	// Costruttore di default
	public Prodotto() {
		super();
	}
	
	// Costruttore senza copieVendute (inizializzate a 0)
	public Prodotto(String isbn, String nome, String autore, String descrizione, String immagine, Double prezzo,
			Integer quantita, String genere, String categoria) {
		super();
		this.isbn = isbn;
		this.nome = nome;
		this.autore = autore;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.genere = genere;
		this.categoria = categoria;
		this.copieVendute = 0;
	}

	// Costruttore completo con tutte le informazioni
	public Prodotto(String isbn, String nome, String autore, String descrizione, String immagine, Double prezzo,
			Integer quantita, String genere, String categoria, Integer copieVendute) {
		super();
		this.isbn = isbn;
		this.nome = nome;
		this.autore = autore;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.genere = genere;
		this.categoria = categoria;
		this.copieVendute = copieVendute;
	}
	
	// Costruttore parziale per casi di anteprima (es. solo nome e immagine)
	public Prodotto(String nome, String immagine) {
		super();
		this.nome = nome;
		this.immagine = immagine;
	}

	// Metodo per aggiornare i campi non vuoti
	public void setNotEmpty(String nome, String autore, String descrizione, String immagine, Double prezzo,
							Integer quantita, String genere, String categoria) {
		if (!nome.equals(""))
			this.nome = nome;
		if (!autore.equals(""))
			this.autore = autore;
		if (!descrizione.equals(""))
			this.descrizione = descrizione;
		if (!immagine.equals(""))
			this.immagine = immagine;
		if (prezzo > 0.0)
			this.prezzo = prezzo;
		if (quantita >= 0)
			this.quantita = quantita;
		if (!genere.equals(""))
			this.genere = genere;
		if (!categoria.equals(""))
			this.categoria = categoria;
	}

	// Metodo per sommare una quantità alle scorte attuali
	public Integer sommaQuantita(Integer quantita) {
		this.quantita += quantita;
		return this.quantita;
	}

	// Metodo per aggiungere nuove copie vendute (solo se la quantità è positiva)
	public Integer sommaCopieVendute(Integer quantita) {
		if (quantita > 0) {
			this.copieVendute += quantita;
			return copieVendute;
		}
		return this.copieVendute;
	}

	// Getter e setter per tutti gli attributi
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getCopieVendute() {
		return copieVendute;
	}

	public void setCopieVendute(Integer copieVendute) {
		this.copieVendute = copieVendute;
	}

	// Metodo toString per rappresentazione testuale dell’oggetto
	@Override
	public String toString() {
		return "Prodotto [isbn=" + isbn + ", nome=" + nome + ", autore=" + autore + ", descrizione=" + descrizione
				+ ", immagine=" + immagine + ", prezzo=" + prezzo + ", quantita=" + quantita + ", genere=" + genere
				+ ", categoria=" + categoria + ", copieVendute=" + copieVendute + "]";
	}

	// Metodo equals per confrontare due prodotti (utile per test e collezioni)
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Prodotto prodotto = (Prodotto) o;

		// Confronto campo per campo
		if (!Objects.equals(isbn, prodotto.isbn)) return false;
		if (!Objects.equals(nome, prodotto.nome)) return false;
		if (!Objects.equals(autore, prodotto.autore)) return false;
		if (!Objects.equals(descrizione, prodotto.descrizione)) return false;
		if (!Objects.equals(immagine, prodotto.immagine)) return false;
		if (!Objects.equals(prezzo, prodotto.prezzo)) return false;
		if (!Objects.equals(quantita, prodotto.quantita)) return false;
		if (!Objects.equals(genere, prodotto.genere)) return false;
		if (!Objects.equals(categoria, prodotto.categoria)) return false;
        return Objects.equals(copieVendute, prodotto.copieVendute);
    }

	// Metodo hashCode per usare l'oggetto in hash map/set
	@Override
	public int hashCode() {
		int result = isbn != null ? isbn.hashCode() : 0;
		result = 31 * result + (nome != null ? nome.hashCode() : 0);
		result = 31 * result + (autore != null ? autore.hashCode() : 0);
		result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
		result = 31 * result + (immagine != null ? immagine.hashCode() : 0);
		result = 31 * result + (prezzo != null ? prezzo.hashCode() : 0);
		result = 31 * result + (quantita != null ? quantita.hashCode() : 0);
		result = 31 * result + (genere != null ? genere.hashCode() : 0);
		result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
		result = 31 * result + (copieVendute != null ? copieVendute.hashCode() : 0);
		return result;
	}
}
