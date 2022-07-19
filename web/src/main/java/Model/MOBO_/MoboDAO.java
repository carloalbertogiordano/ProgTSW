package Model.MOBO_;

import Model.ConPool;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class MoboDAO {

    //Interroga il DB per avere una lista di prodotti di tipo MOBO
    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "MOBO");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                                int ID,              String marca,             String modello,           double prezzo,            int quantità,                int forma,               int N_RAM,               int N_USB,                int N_PCI,                 String url,                String descrizione,
            Mobo cpu = new Mobo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(17), rs.getShort(6), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(18), rs.getString(19));
            list.add(cpu);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di schede madri
    public ArrayList<Mobo> doRetriveByType() throws SQLException{
        MoboDAO mDAO = new MoboDAO();
        ArrayList<Prodotto> listP = mDAO.doRetrive();
        ArrayList<Mobo> listM = new ArrayList<Mobo>();
        for(Prodotto p : listP){
            listM.add((Mobo) p);
        }
        return listM;
    }

    public void Upload(Mobo m) throws SQLException {
        ProdottoDAO.Upload(m.getMarca(), m.getModello(), m.getPrezzo(), m.getQuantita(), null, m.getTipo(), null, null, m.getN_RAM(), m.getN_USB(), m.getN_PCI(), null, null, null, null, m.getForma(), m.getUrl(), m.getDescrizione());
    }

}
