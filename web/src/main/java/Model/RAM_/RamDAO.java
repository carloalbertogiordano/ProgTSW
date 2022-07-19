package Model.RAM_;

import Model.ConPool;
import Model.MOBO_.Mobo;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class RamDAO {

    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "RAM");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                                 int ID,              String marca,               String modello,              double prezzo,          int quantità,        float frequenza,              String url,             String descrizione
            Ram ram = new Ram(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getFloat(8), rs.getString(18), rs.getString(19));
            list.add(ram);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di RAM
    public ArrayList<Ram> doRetriveByType() throws SQLException{
        RamDAO rDAO = new RamDAO();
        ArrayList<Prodotto> listP = rDAO.doRetrive();
        ArrayList<Ram> listR = new ArrayList<Ram>();
        for(Prodotto p : listP){
            listR.add((Ram) p);
        }
        return listR;
    }

    public void Upload(Ram r) throws SQLException {
        ProdottoDAO.Upload(r.getMarca(), r.getModello(), r.getPrezzo(), r.getQuantita(), null, r.getTipo(), r.getFrequenza(), null, null, null, null, null, null, null, null, null, r.getUrl(), r.getDescrizione());
    }

}
