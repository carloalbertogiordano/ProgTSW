package Model.PSU_;

import Model.Prodotto;

public class Psu extends Prodotto {

    private int N_Watt;

    public Psu(int ID, String marca, String modello, double prezzo, int quantità, int N_Watt ,String url, String descrizione){
        super(ID, marca, modello, prezzo, quantità, "PSU",url, descrizione);
        this.N_Watt = N_Watt;
    }

    public Psu(String marca, String modello, double prezzo, int quantità, int N_Watt ,String url, String descrizione){
        super(marca, modello, prezzo, quantità, "PSU",url, descrizione);
        this.N_Watt = N_Watt;
    }

    public int getN_Watt(){
        return N_Watt;
    }

    public void setN_Watt(int N_Watt){
        this.N_Watt = N_Watt;
    }

    @Override
    public String toString(){
        return "Psu{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantità() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                "N. Watt: " + N_Watt + "}\n";
    }
}