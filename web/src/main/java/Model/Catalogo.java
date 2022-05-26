package Model;

import java.util.List;

public class Catalogo {
    private List<Prodotto> catalgo;

    public Catalogo(){

    }

    public List<Prodotto> getCatalgo() {
        return catalgo;
    }

    public void setCatalgo(List<Prodotto> catalgo) {
        this.catalgo = catalgo;
    }
}
