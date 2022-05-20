package Model.Carrello_;

import Model.Prodotto;

import java.util.List;

public class Carrello {
    private List<Prodotto> carrello;

    public Carrello(){

    }

    public List<Prodotto> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<Prodotto> carrello) {
        this.carrello = carrello;
    }
}
