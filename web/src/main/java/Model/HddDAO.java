package Model;

import java.sql.*;
import java.util.ArrayList;

public class HddDAO {

    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "HDD");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                                int ID,              String marca,             String modello,              double prezzo,            int quantità,               int MBs,                   String url,          String descrizione
            Hdd hdd = new Hdd(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(13), rs.getString(18), rs.getString(19));
            list.add(hdd);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di HDD
    public ArrayList<Hdd> doRetriveByType() throws SQLException{
        HddDAO hDAO = new HddDAO();
        ArrayList<Prodotto> listP = hDAO.doRetrive();
        ArrayList<Hdd> listH = new ArrayList<Hdd>();
        for(Prodotto p : listP){
            listH.add((Hdd) p);
        }
        return listH;
    }


}