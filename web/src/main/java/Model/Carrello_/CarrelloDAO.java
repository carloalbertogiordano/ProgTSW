package Model.Carrello_;

import Model.CASE_.Case;
import Model.CPU_.Cpu;
import Model.ConPool;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.RAM_.Ram;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {
    public int doRetriveCarrelloCodByMailCLiente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true;");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        int codice = -1;
        while(rs.next()){
            codice = rs.getInt(1);
        }
        return codice;
    }

    public double doRetrivePrezzoByIdCarrello(int id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Totale FROM Carrello WHERE Cod = ?;");
        pdstmt.setInt(1, id);
        ResultSet rs = pdstmt.executeQuery();
        double totale = -1;
        while(rs.next()){
            totale = rs.getInt(1);
        }
        return totale;
    }

    private int getComporreQuantita(int id, int carrelloCod) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Quantita FROM Comporre WHERE PezzoId = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, id);
        pdstmt.setInt(2, carrelloCod);
        ResultSet rs = pdstmt.executeQuery();
        int quantita = -1;
        while(rs.next()){
            quantita = rs.getInt(1);
        }
        return quantita;
    }

    public void doUpdateQuantitaRichiestaById(int id, int carrelloCod, int quantita) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Comporre SET Quantita = ? WHERE PezzoID = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, quantita);
        pdstmt.setInt(2, id);
        pdstmt.setInt(3, carrelloCod);
        pdstmt.executeUpdate();
    }

    public Carrello doRetriveByMailCliente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT PezzoID FROM Comporre WHERE CarrelloCod = (SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true);");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        ArrayList<Integer> prodotti = new ArrayList<Integer>();
        //ArrayList<Integer> richiesti = new ArrayList<Integer>();
        while(rs.next()){
            Integer i = rs.getInt(1);
            //Integer quantita = rs.getInt(2);
            prodotti.add(i);
            //richiesti.add(quantita);
        }
        int carrelloCod = doRetriveCarrelloCodByMailCLiente(mail);
        double totaleCarrello = doRetrivePrezzoByIdCarrello(carrelloCod);
        List<Prodotto> listProdotti = new ArrayList<Prodotto>();
        listProdotti = Prodotto.doRetriveByIdLis(prodotti);
        for(int i = 0; i < listProdotti.size(); i++){
            int quantitaRichiesta = getComporreQuantita(listProdotti.get(i).getID(), carrelloCod);
            listProdotti.get(i).setQuantità(quantitaRichiesta);
        }
        Carrello carrello = new Carrello(listProdotti, carrelloCod, totaleCarrello);
        return carrello;
    }
}
