package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class CPU extends Prodotto {
    private double frequenza;
    private int N_Core;

    public CPU(int ID, String marca, String modello, double prezzo, String type, double frequenza, int N_Core) {
        super(ID, marca, modello, prezzo, type);
        this.frequenza = frequenza;
        this.N_Core = N_Core;
    }

   /* public ArrayList<CPU> getCpuList() throws SQLException {
        ArrayList<CPU> list_CPUs = new ArrayList<CPU>();
        ProdottoDAO PDAO = new ProdottoDAO();
        ArrayList<Prodotto> list = (ArrayList<Prodotto>) PDAO.doRetriveByType("CPU");

        return list;
    }*/

}
