package Model.RAM_;

import Model.CASE_.Case;
import Model.ConPool;
import Model.MOBO_.Mobo;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class RamDAO{

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
    public ArrayList<Ram> doRetriveByType() throws SQLException {
        RamDAO rDAO = new RamDAO();
        ArrayList<Prodotto> listP = rDAO.doRetrive();
        ArrayList<Ram> listR = new ArrayList<Ram>();
        for (Prodotto p : listP) {
            listR.add((Ram) p);
        }
        return listR;
    }

    public static void Upload(Ram r) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "INSERT INTO Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Frequenza, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, r.getTipo());
        pdstmt.setString(2, r.getMarca());
        pdstmt.setString(3, r.getModello());
        pdstmt.setDouble(4, r.getPrezzo());
        pdstmt.setInt(5, r.getQuantita());
        pdstmt.setFloat(6, r.getFrequenza());
        pdstmt.setString(7, r.getUrl());
        pdstmt.setString(8, r.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        r.setID(rs.getInt(1));
    }

    public static void Update(Ram r) throws SQLException {
        String updProd = "UPDATE Pezzo " +
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "Frequenza = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(updProd);

        pdstmt.setString(1, r.getMarca());
        pdstmt.setString(2, r.getModello());
        pdstmt.setDouble(3, r.getPrezzo());
        pdstmt.setInt(4, r.getQuantita());
        pdstmt.setFloat(5, r.getFrequenza());
        pdstmt.setString(6, r.getUrl());
        pdstmt.setString(7, r.getDescrizione());
        pdstmt.setInt(8, r.getID());
        pdstmt.executeUpdate();
    }
}
