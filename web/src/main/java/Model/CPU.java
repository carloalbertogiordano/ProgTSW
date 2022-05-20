package Model;

public class CPU extends Prodotto {
    private double frequenza;
    private int N_Core;

    public CPU(int ID, String marca, String modello, double prezzo, String type, double frequenza, int N_Core) {
        super(ID, marca, modello, prezzo, type);
        this.frequenza = frequenza;
        this.N_Core = N_Core;
    }

    public double getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(double frequenza) {
        this.frequenza = frequenza;
    }

    public int getN_Core() {
        return N_Core;
    }

    public void setN_Core(int n_Core) {
        N_Core = n_Core;
    }
}
