package Model.CASE_;

import Model.ConPool;

import Model.CASE_.Case;

import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class CaseDAO {

    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "CASE");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                                 int ID,                String marca,            String modello,             double prezzo,           int quantità,          short formaMobo,                 String url,         String descrizione
            Case case_ = new Case(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getShort(17), rs.getString(18), rs.getString(19));
            list.add(case_);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di Case
    public ArrayList<Case> doRetriveByType() throws SQLException{
        CaseDAO cDAO = new CaseDAO();
        ArrayList<Prodotto> listP = cDAO.doRetrive();
        ArrayList<Case> listC = new ArrayList<Case>();
        for(Prodotto p : listP){
            listC.add((Case) p);
        }
        return listC;
    }

    public void Upload(Case c) throws SQLException {
        ProdottoDAO.Upload(c.getMarca(), c.getModello(), c.getPrezzo(), c.getQuantità(), null, c.getTipo(), null, null, null, null, null, null, null, null, null, c.getFormaMobo(), c.getUrl(), c.getDescrizione());
    }

}
