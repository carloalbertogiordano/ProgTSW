package Model;

public abstract class Prodotto {
    private int ID;
    private String marca;
    private String modello;
    private double prezzo;
    private String tipo;
    private String url;
    private String descrizione;
    private int quantità;



    public Prodotto() {}

    public Prodotto(int ID, String marca, String modello, double prezzo, int quantità, String tipo, String url, String descrizione) {
        this.ID = ID;
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
        this.tipo = tipo;
        this.url = url;
        this.descrizione = descrizione;
        this.quantità = quantità;
    }

    public Prodotto(String marca, String modello, double prezzo, int quantità, String tipo, String url, String descrizione) {
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
        this.tipo = tipo;
        this.url = url;
        this.descrizione = descrizione;
        this.quantità = quantità;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //@Override
    /*public String toString() {
        return "Prodotto{" +
                "ID=" + ID +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", prezzo=" + prezzo +
                ", tipo='" + tipo + '\'' +
                ", url='" + url + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }*/
}