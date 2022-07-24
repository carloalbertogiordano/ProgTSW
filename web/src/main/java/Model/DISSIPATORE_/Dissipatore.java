package Model.DISSIPATORE_;

import Model.Prodotto;

public class Dissipatore extends Prodotto {

    private int W_Cpu;

    /*public Dissipatore (int ID, String marca, String modello, double prezzo, int quantita, int W_Cpu, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantita, "DISSIPATORE", url, descrizione);
        this.W_Cpu = W_Cpu;
    }

    public Dissipatore (String marca, String modello, double prezzo, int quantita, int W_Cpu, String url, String descrizione) {
        super(marca, modello, prezzo, quantita, "DISSIPATORE", url, descrizione);
        this.W_Cpu = W_Cpu;
    }*/

    public Dissipatore() {
        super.setTipo("DISSIPATORE");
    }

    public int getW_Cpu() {
        return W_Cpu;
    }

    public void setW_Cpu(int W_Cpu) {
        this.W_Cpu = W_Cpu;
    }

    @Override
    public String toString() {
        return  "Dissipatore{"+
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantita() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                ", W_Cpu=" + W_Cpu + "}\n";
    }

}

