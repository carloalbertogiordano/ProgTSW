package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {
    public ArrayList<Integer> doRetriveByMailCliente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT PezzoID FROM Comporre WHERE CarrelloCod IN (SELECT CarrelloCod FROM Ordine WHERE ClienteID = ? AND Evaso != true);");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        ArrayList<Integer> prodotti = new ArrayList<Integer>();
        int x = 0;
        while(rs.next()){
            Integer i = rs.getInt(x);
            prodotti.add(i);
            x++;
        }
        return prodotti;
    }

    public ArrayList<Integer> doJoinSessionDBCarrello(List<Integer> carrelloSessione, String mail) throws SQLException {
        ArrayList<Integer> newCarrello = new ArrayList<Integer>();
        ArrayList<Integer> dbCarrello = new ArrayList<Integer>();
        dbCarrello = doRetriveByMailCliente(mail);
        for(int i = 0; i < dbCarrello.size(); i++){
            newCarrello.add(dbCarrello.get(i));
        }
        for(int i = 0; i < carrelloSessione.size(); i++){
            newCarrello.add(dbCarrello.get(i));
        }
        return newCarrello;
    }
}
