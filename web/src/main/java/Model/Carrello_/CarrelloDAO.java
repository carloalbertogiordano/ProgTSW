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
    public List<Prodotto> doRetriveByIdLis(ArrayList<Integer> idList) throws SQLException {
        System.out.println("Metodo doRetriveByIdList, lista passata come parametro: " + idList.toString());
        List<Prodotto> carrello = new ArrayList<Prodotto>();
        for(int i = 0; i < idList.size(); i++){
            System.out.println("Metodo doRetriveByIdList, parametri singoli for: " + idList.get(i));
            carrello.add(ProdottoDAO.doRetriveById(idList.get(i)));
        }
        return carrello;
    }

    public List<Prodotto> doRetriveByMailCliente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT PezzoID FROM Comporre WHERE CarrelloCod IN (SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true);");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        ArrayList<Integer> prodotti = new ArrayList<Integer>();
        while(rs.next()){
            Integer i = rs.getInt(1);
            prodotti.add(i);
        }
        System.out.println(prodotti.toString());
        List<Prodotto> carrello = new ArrayList<Prodotto>();
        carrello = doRetriveByIdLis(prodotti);
        return carrello;
    }
}
