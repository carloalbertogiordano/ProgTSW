package Model.CASE_;

import Model.Prodotto;

public class Case extends Prodotto {
    private short formaMobo;

    public Case() {

    }

    public Case(int ID, String marca, String modello, double prezzo, int quantità, short formaMobo, String url, String descrizione){
        super(ID, marca, modello, prezzo, quantità, "CASE", url, descrizione);
        this.formaMobo = formaMobo;
    }

    public Case(String marca, String modello, double prezzo, int quantità, short formaMobo, String url, String descrizione){
        super(marca, modello, prezzo, quantità, "CASE", url, descrizione);
        this.formaMobo = formaMobo;
    }

    public short getFormaMobo() { return formaMobo; }

    public void setFormaMobo(short formaMobo) {this.formaMobo = formaMobo;}

    public String toString() {
        return  "Case{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantità() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                        ", Forma MOBO: " + formaMobo + "}\n";
    }

}
