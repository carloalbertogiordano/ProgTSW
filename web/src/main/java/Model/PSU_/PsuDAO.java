package Model.PSU_;

import Model.CASE_.Case;
import Model.ConPool;
import Model.GPU_.Gpu;
import Model.GPU_.GpuDAO;
import Model.MOBO_.Mobo;
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.RAM_.Ram;

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

    public static void Upload(Psu p) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_WATT, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, p.getTipo());
        pdstmt.setString(2, p.getMarca());
        pdstmt.setString(3, p.getModello());
        pdstmt.setDouble(4, p.getPrezzo());
        pdstmt.setInt(5, p.getQuantita());
        pdstmt.setInt(6, p.getN_Watt());
        pdstmt.setString(7, p.getUrl());
        pdstmt.setString(8, p.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        p.setID(rs.getInt(1));
    }

    public static void Update(Psu p) throws SQLException {
        String updProd = "UPDATE Pezzo " +
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "N_Watt = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(updProd);

        pdstmt.setString(1, p.getMarca());
        pdstmt.setString(2, p.getModello());
        pdstmt.setDouble(3, p.getPrezzo());
        pdstmt.setInt(4, p.getQuantita());
        pdstmt.setInt(5, p.getN_Watt());
        pdstmt.setString(6, p.getUrl());
        pdstmt.setString(7, p.getDescrizione());
        pdstmt.setInt(8, p.getID());
        pdstmt.executeUpdate();
    }

}
