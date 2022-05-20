package Model;

import java.sql.*;
import java.util.ArrayList;

public class CPUDAO {

    public CPUDAO(){
        super();
    }

    //Interroga il DB per avere una lista di prodotti di tipo CPU
    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "CPU");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
                                          //int ID,                 String marca,             String modello,           double prezzo,           int quantità,            int wattaggio,         double frequenza,             int N_Core,                  String url,       String descrizione
            CPU cpu = new CPU(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(8), rs.getInt(9), rs.getString(18), rs.getString(19));
            list.add(cpu);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di cpu
    public ArrayList<CPU> doRetriveByType() throws SQLException{
        CPUDAO cDAO = new CPUDAO();
        ArrayList<Prodotto> listP = cDAO.doRetrive();
        ArrayList<CPU> listC = new ArrayList<CPU>();
        for(Prodotto p : listP){
            listC.add((CPU) p);
        }
        return listC;
    }


}
