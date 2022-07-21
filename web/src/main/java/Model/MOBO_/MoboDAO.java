package Model.MOBO_;

import Model.ConPool;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class MoboDAO {

    //Interroga il DB per avere una lista di prodotti di tipo MOBO
    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "MOBO");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                                int ID,              String marca,             String modello,           double prezzo,            int quantità,                int forma,               int N_RAM,               int N_USB,                int N_PCI,                 String url,                String descrizione,
            Mobo cpu = new Mobo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(17), rs.getShort(6), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(18), rs.getString(19));
            list.add(cpu);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di schede madri
    public ArrayList<Mobo> doRetriveByType() throws SQLException{
        MoboDAO mDAO = new MoboDAO();
        ArrayList<Prodotto> listP = mDAO.doRetrive();
        ArrayList<Mobo> listM = new ArrayList<Mobo>();
        for(Prodotto p : listP){
            listM.add((Mobo) p);
        }
        return listM;
    }

    public static void Upload(Mobo m) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_RAM, N_USB, N_PCI, formaMobo, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, m.getTipo());
        pdstmt.setString(2, m.getMarca());
        pdstmt.setString(3, m.getModello());
        pdstmt.setDouble(4, m.getPrezzo());
        pdstmt.setInt(5, m.getQuantita());
        pdstmt.setInt(6, m.getN_RAM());
        pdstmt.setInt(7, m.getN_USB());
        pdstmt.setInt(8, m.getN_PCI());
        pdstmt.setInt(9, m.getForma());
        pdstmt.setString(10, m.getUrl());
        pdstmt.setString(11, m.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        m.setID(rs.getInt(1));
    }


}
