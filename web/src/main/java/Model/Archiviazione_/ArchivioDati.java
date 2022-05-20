package Model.Archiviazione_;

import Model.Prodotto;

public class ArchivioDati extends Prodotto {

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
        return super.toString() + "\nMBs: " + MBs;
    }
}
