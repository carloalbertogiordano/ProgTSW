package Model.CPU_;

import Model.Prodotto;

import java.util.ArrayList;

public class Cpu extends Prodotto {


    private int N_Core;
    private double frequenza;
    private int wattaggio;

    public Cpu(int ID, String marca, String modello, double prezzo, int quantità, int wattaggio, double frequenza, int N_Core, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantità, "CPU", url, descrizione);
        this.wattaggio = wattaggio;
        this.frequenza = frequenza;
        this.N_Core = N_Core;
    }

    public String listCpus(ArrayList<Prodotto> lista){
        return super.toString() + " Wattaggio: " + wattaggio + " Frequenza: " + frequenza + " N_Core: " + N_Core + "\n";
    }

    public int getN_Core() {
        return N_Core;
    }

    public void setN_Core(int n_Core) {
        N_Core = n_Core;
    }

    public double getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(double frequenza) {
        this.frequenza = frequenza;
    }
}