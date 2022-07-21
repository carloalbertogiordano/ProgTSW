package Model.DISSIPATORE_;

import Model.CASE_.Case;
import Model.ConPool;
import Model.PSU_.Psu;
import Model.PSU_.PsuDAO;
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.RAM_.Ram;

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

    public static void Upload(Dissipatore d) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, W_CPU, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, d.getTipo());
        pdstmt.setString(2, d.getMarca());
        pdstmt.setString(3, d.getModello());
        pdstmt.setDouble(4, d.getPrezzo());
        pdstmt.setInt(5, d.getQuantita());
        pdstmt.setInt(6, d.getW_Cpu());
        pdstmt.setString(7, d.getUrl());
        pdstmt.setString(8, d.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        d.setID(rs.getInt(1));

    }

}
