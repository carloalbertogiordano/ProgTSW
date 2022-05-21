package Model.GPU_;

import Model.Prodotto;

public class Gpu extends Prodotto {
    private int wattaggio, frequenza;
    private float vRam;

    public Gpu(int ID, String marca, String modello, double prezzo, int quantità,
               int wattaggio, int frequenza, float vRam, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantità, "GPU", url, descrizione);
        this.wattaggio = wattaggio;
        this.frequenza = frequenza;
        this.vRam = vRam;
    }

    public int getWattaggio() {
        return wattaggio;
    }

    public int getFrequenza() {
        return frequenza;
    }

    public float getVRam() {
        return vRam;
    }

    public void setWattaggio(int wattaggio) {
        this.wattaggio = wattaggio;
    }

    public void setFrequenza(int frequenza) {
        this.frequenza = frequenza;
    }

    public void setVRam(float vRam) {
        this.vRam = vRam;
    }

    @Override
    public String toString() {
        return super.toString() + "Gpu{" +
                "wattaggio=" + wattaggio +
                ", frequenza=" + frequenza +
                ", vRam=" + vRam +
                '}';
    }
}
