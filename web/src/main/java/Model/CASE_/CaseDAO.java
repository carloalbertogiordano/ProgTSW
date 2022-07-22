package Model.CASE_;

import Model.ConPool;

import Model.CASE_.Case;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.RAM_.Ram;

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

    public static void Upload(Case c) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, FormaMobo, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, c.getTipo());
        pdstmt.setString(2, c.getMarca());
        pdstmt.setString(3, c.getModello());
        pdstmt.setDouble(4, c.getPrezzo());
        pdstmt.setInt(5, c.getQuantita());
        pdstmt.setFloat(6, c.getFormaMobo());
        pdstmt.setString(7, c.getUrl());
        pdstmt.setString(8, c.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        c.setID(rs.getInt(1));
    }

    public static void Update(Case c) throws SQLException {
        String updProd = "UPDATE Pezzo "+
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "FormaMobo = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(updProd);

        pdstmt.setString(1, c.getMarca());
        pdstmt.setString(2, c.getModello());
        pdstmt.setDouble(3, c.getPrezzo());
        pdstmt.setInt(4, c.getQuantita());
        pdstmt.setInt(5, c.getFormaMobo());
        pdstmt.setString(6, c.getUrl());
        pdstmt.setString(7, c.getDescrizione());
        pdstmt.setInt(8, c.getID());
        pdstmt.executeUpdate();
    }

}
