package Model.DISSIPATORE_;

import Model.CASE_.Case;
import Model.ConPool;
import Model.PSU_.Psu;
import Model.PSU_.PsuDAO;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class DissipatoreDAO {
    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "DISSIPATORE");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                                            int ID,              String marca,            String modello,              double prezzo,            int quantità,               int W_Cpu,                 String url,           String descrizione
            Dissipatore d = new Dissipatore(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(16), rs.getString(18), rs.getString(19));
            list.add(d);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di Dissipatore
    public ArrayList<Dissipatore> doRetriveByType() throws SQLException{
        DissipatoreDAO dDAO = new DissipatoreDAO();
        ArrayList<Prodotto> listP = dDAO.doRetrive();
        ArrayList<Dissipatore> listD = new ArrayList<Dissipatore>();
        for(Prodotto p : listP){
            listD.add((Dissipatore) p);
        }
        return listD;
    }

    public void Upload(Dissipatore d) throws SQLException {
        ProdottoDAO.Upload(d.getMarca(), d.getModello(), d.getPrezzo(), d.getQuantita(), null, d.getTipo(), null, null, null, null, null, null, null, null, d.getW_Cpu(), null, d.getUrl(), d.getDescrizione());
    }

}
