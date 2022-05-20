package Model;

public class Mobo extends Prodotto{
    private int forma;
    private int N_RAM;
    private int N_USB;
    private int N_PCI;
    public Mobo(int ID, String marca, String modello, double prezzo, int quantità, int forma, int N_RAM, int N_USB, int N_PCI, String url, String descrizione){
        super( ID, marca, modello, prezzo, quantità, "MOBO", url, descrizione);
        this.forma = forma;
        this.N_RAM = N_RAM;
        this.N_USB = N_USB;
        this.N_PCI = N_PCI;
    }

    public int getForma(){
        return forma;
    }
    public int getN_RAM(){
        return N_RAM;
    }
    public int getN_USB(){
        return N_USB;
    }
    public int getN_PCI(){
        return N_PCI;
    }
    public void setForma(int forma){
        this.forma = forma;
    }
    public void setN_RAM(int N_RAM){
        this.N_RAM = N_RAM;
    }
    public void setN_USB(int N_USB){
        this.N_USB = N_USB;
    }
    public void setN_PCI(int N_PCI){
        this.N_PCI = N_PCI;
    }
    public String toString(){
        return "ID: " + getID() + "\nMarca: " + getMarca() + "\nModello: " + getModello() + "\nPrezzo: " + getPrezzo() + "\nQuantità: " + getQuantità() + "\nURL: " + getUrl() + "\nDescrizione: " + getDescrizione() + "\nForma: " + getForma() + "\nN_RAM: " + getN_RAM() + "\nN_USB: " + getN_USB() + "\nN_PCI: " + getN_PCI();
    }


}


