package acquistoManagement;

import catalogoManagement.Prodotto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Carrello implements Serializable{
    private static final long serialVersionUID = 1L;

    private int carrelloId;

    private HashSet<Prodotto> listaProdotti = new HashSet<>();

    public Carrello(int carrelloId) {
        this.carrelloId = carrelloId;
    }

    public Carrello(){}

    public int getCarrelloId() {
        return carrelloId;
    }

    public Set<Prodotto> getListaProdotti() {
        if (listaProdotti == null) {
            listaProdotti = new HashSet<>();
        }
        return listaProdotti;
    }


    public void setListaProdotti(Set<Prodotto> listaProdotti) {
        this.listaProdotti = (HashSet<Prodotto>) listaProdotti;
    }

    public void add(Prodotto p){
        this.listaProdotti.add(p);
    }

    public void empty(){
        this.listaProdotti.clear();
    }

    @Override
    public String toString() {
        return "Carrello{" +
                "carrelloId=" + carrelloId +
                ", listaProdotti=" + listaProdotti +
                '}';
    }
}
