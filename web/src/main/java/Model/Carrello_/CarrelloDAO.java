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
        PreparedStatement pdstmt = con.prepareStatement("SELECT Totale FROM Carrello WHERE CarrelloCod = ?;");
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
        pdstmt.setInt(1, carrelloCod);
        ResultSet rs = pdstmt.executeQuery();
        int quantita = -1;
        while(rs.next()){
            quantita = rs.getInt(1);
        }
        return quantita;
    }

    public void doUpdateQuantitaRichiestaById(int id, int carrelloCod) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Comporre SET Quantita WHERE PezzoID = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, id);
        pdstmt.setInt(2, carrelloCod);
        pdstmt.executeUpdate();
    }

    public Carrello doRetriveByMailCliente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT PezzoID FROM Comporre WHERE CarrelloCod IN (SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true);");
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
        Carrello carrello = new Carrello(Prodotto.doRetriveByIdLis(prodotti), carrelloCod, totaleCarrello);
        return carrello;
    }

    //Quanrtità pezzo è quantità richiesta
    public Carrello joinCarrelli(Carrello carrelloDB, Carrello carrelloSession){
        for(int i = 0; i < carrelloSession.getCarrello().size(); i++){
            for(int j = 0; j < carrelloDB.getCarrello().size(); j++){
                if(carrelloSession.getCarrello().get(i).getID() == carrelloDB.getCarrello().get(j).getID()){
                    int quantita = carrelloSession.getCarrello().get(i).getQuantità() +  carrelloDB.getCarrello().get(j).getQuantità();
                    carrelloSession.getCarrello().get(i).setQuantità(quantita);
                    carrelloDB.getCarrello().remove(j);
                }
            }
        }
        for(Prodotto p : carrelloDB.getCarrello()){
            carrelloSession.getCarrello().add(p);
        }
        return carrelloSession;
    }
}
