package Model;

public class Mobo extends Prodotto{
    private int forma;
    private int N_RAM;
    private int N_USB;
    private int N_PCI;
    public Mobo(int ID, String marca, String modello, double prezzo, String type, int forma, int N_RAM, int N_USB, int N_PCI){
        super(ID, marca, modello, prezzo, type);
        this.forma = forma;
        this.N_RAM = N_RAM;
        this.N_USB = N_USB;
        this.N_PCI = N_PCI;
    }

    public int getForma() {
        return forma;
    }

    public void setForma(int forma) {
        this.forma = forma;
    }

    public int getN_RAM() {
        return N_RAM;
    }

    public void setN_RAM(int n_RAM) {
        N_RAM = n_RAM;
    }

    public int getN_USB() {
        return N_USB;
    }

    public void setN_USB(int n_USB) {
        N_USB = n_USB;
    }

    public int getN_PCI() {
        return N_PCI;
    }

    public void setN_PCI(int n_PCI) {
        N_PCI = n_PCI;
    }
}
