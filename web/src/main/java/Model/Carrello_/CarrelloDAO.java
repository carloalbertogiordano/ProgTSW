package Model.Carrello_;

import Model.CASE_.Case;
import Model.CPU_.Cpu;
import Model.ConPool;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.RAM_.Ram;

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

    public Prodotto doRetriveById(int ID) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto WHERE Id = ?");
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        String type = rs.getString(7);
        switch (type) {
            case "CPU":{
                Cpu cpu = new Cpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getDouble(8), rs.getInt(9), rs.getString(18), rs.getString(19));
                return cpu;
            }
            case "MOBO":{
                Mobo mobo = new Mobo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getShort(17), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(18), rs.getString(19));
                return mobo;
            }
            case "CASE":{
                Case case_ = new Case(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getShort(17), rs.getString(18), rs.getString(19));
                return case_;
            }
            case "DISSIPATORE":{
                Dissipatore dissipatore = new Dissipatore(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(16), rs.getString(18), rs.getString(19));
                return dissipatore;
            }
            case "GPU":{
                Gpu gpu = new Gpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(8), rs.getInt(13), rs.getString(18), rs.getString(19));
                return gpu;
            }
            case "PSU":{
                Psu psu = new Psu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(15), rs.getString(18), rs.getString(19));
                return psu;
            }
            case "RAM":{
                Ram ram = new Ram(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getFloat(8), rs.getString(18), rs.getString(19));
                return ram;
            }
            default:{
                Prodotto prodotto = null;
                return prodotto;
            }
        }

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
