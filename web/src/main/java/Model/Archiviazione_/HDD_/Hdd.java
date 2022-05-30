package Model.Archiviazione_.HDD_;

import Model.Archiviazione_.ArchivioDati;
import Model.Prodotto;

public class Hdd extends ArchivioDati {

    public Hdd(int ID, String marca, String modello, double prezzo, int quantità, int MBs, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantità, "HDD", MBs, url, descrizione);
    }

    @Override
    public String toString() {
        return "HDD" + super.toString();
    }


}