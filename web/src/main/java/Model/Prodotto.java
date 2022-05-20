package Model;

public class Prodotto {
    private int ID;
    private String marca;
    private String modello;
    private double prezzo;
    private String type;
    private int wattaggio;
    private int frequenza;
    private int N_CORE;
    private int N_RAM;
    private int N_USB;
    private int N_PCI;
    private int MBs;
    private int VRAM;
    private int N_WATT;
    private int W_CPU;
    private short FormaMobo;
    private String url;
    private String Descrizione;


    public Prodotto() {

    }

    public Prodotto(int ID, String marca, String modello, double prezzo, String type) {
        this.ID = ID;
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
        this.type = type;
    }

    public int getWattaggio() {
        return wattaggio;
    }

    public void setWattaggio(int wattaggio) {
        this.wattaggio = wattaggio;
    }

    public int getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(int frequenza) {
        this.frequenza = frequenza;
    }

    public int getN_CORE() {
        return N_CORE;
    }

    public void setN_CORE(int n_CORE) {
        N_CORE = n_CORE;
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

    public int getMBs() {
        return MBs;
    }

    public void setMBs(int MBs) {
        this.MBs = MBs;
    }

    public int getVRAM() {
        return VRAM;
    }

    public void setVRAM(int VRAM) {
        this.VRAM = VRAM;
    }

    public int getN_WATT() {
        return N_WATT;
    }

    public void setN_WATT(int n_WATT) {
        N_WATT = n_WATT;
    }

    public int getW_CPU() {
        return W_CPU;
    }

    public void setW_CPU(int w_CPU) {
        W_CPU = w_CPU;
    }

    public short getFormaMobo() {
        return FormaMobo;
    }

    public void setFormaMobo(short formaMobo) {
        FormaMobo = formaMobo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}