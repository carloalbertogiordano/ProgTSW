package Model.Archiviazione_;

import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.SQLException;

public class ArchivioDati extends Prodotto {
//ArchivioDati estende prodotto ed è superclasse per HDD e SSD che contengono fondamentalmente gli stessi valori
//quindi hanno metodi e variabili comuni. La divisione data, sia nel db sia nella classe
//è logica.
    private int MBs;

    public ArchivioDati (int ID, String marca, String modello, double prezzo, int quantità, String tipo, int MBs,String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantità, tipo, url, descrizione);
        this.MBs = MBs;
    }

    public int getMBs() {
        return MBs;
    }

    public void setMBs(int MBs) {
        this.MBs = MBs;
    }

    @Override
    public String toString() {
        return  "{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantità() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                ", Forma MOBO: " + MBs + "}\n";
    }



}
