package Model.PSU_;

import Model.ConPool;
import Model.GPU_.Gpu;
import Model.GPU_.GpuDAO;
import Model.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class PsuDAO {

    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "PSU");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                              int ID,                 String marca,         String modello,              double prezzo,             int quantità,           int N_Watt ,                  String url,        String descrizione
            Psu psu = new Psu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(15), rs.getString(18), rs.getString(19));
            list.add(psu);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di GPU
    public ArrayList<Psu> doRetriveByType() throws SQLException{
        PsuDAO pDAO = new PsuDAO();
        ArrayList<Prodotto> listP = pDAO.doRetrive();
        ArrayList<Psu> listPs = new ArrayList<Psu>();
        for(Prodotto p : listP){
            listPs.add((Psu) p);
        }
        return listPs;
    }


}
