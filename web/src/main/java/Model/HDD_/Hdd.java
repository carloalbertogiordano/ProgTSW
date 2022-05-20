package Model.HDD_;

import Model.Prodotto;

public class Hdd extends Prodotto {

    private int MBs;

    public Hdd(int ID, String marca, String modello, double prezzo, int quantità, int MBs, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantità, "HDD", url, descrizione);
    }

    public int getMBs() {
        return MBs;
    }

    public void setMBs(int MBs) {
        this.MBs = MBs;
    }

    @Override
    public String toString() {
        return super.toString() + "Hdd{" + "MBs=" + MBs + '}';
    }

}