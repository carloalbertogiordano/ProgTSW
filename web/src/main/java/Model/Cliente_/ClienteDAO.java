package Model.Cliente_;

import Model.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    public void addCliente(Cliente c) throws SQLException {
        Connection con = ConPool.getConnection();
        String ins = "INSERT INTO Cliente (Nickname, Mail, Pass, Tel, Via, Provincia, Citta, Cap) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pdstmt = con.prepareStatement(ins);
        pdstmt.setString(1, c.getNickname());
        pdstmt.setString(2, c.getMail());
        pdstmt.setString(3, c.getPass());
        pdstmt.setString(4, c.getTel());
        pdstmt.setString(5, c.getVia());
        pdstmt.setString(6, c.getProvincia());
        pdstmt.setString(7, c.getCitta());
        pdstmt.setInt(8, c.getCap());
        pdstmt.executeUpdate();
    }

    public Cliente doRetrieveByMail(String m) throws SQLException {
        Cliente cliente = null;
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String select = "SELECT * FROM Cliente WHERE Mail = '" + m + "'";
        PreparedStatement pdstmt = con.prepareStatement(select);
        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            cliente = new Cliente( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9));
        }
        return cliente;
    }

    public ArrayList<Cliente> doRetrieveAll() throws SQLException{
        ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String select = "SELECT * FROM Cliente";
        PreparedStatement pdstmt = con.prepareStatement(select);
        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            Cliente c = new Cliente( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9));
            listaClienti.add(c);
        }
        return listaClienti;
    }

    public void doRemove(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String delete = "DELETE FROM Cliente where Mail = " + mail + " ;";
        PreparedStatement pdstmt = con.prepareStatement(delete);
        ResultSet rs = pdstmt.executeQuery();
    }

    public boolean isCorrectLogin(String mail, String pass) throws SQLException {
            Connection con = ConPool.getConnection();
            Statement stmt = (Statement) con.createStatement();
            PreparedStatement pdstmt = con.prepareStatement("SELECT Pass FROM Cliente WHERE Mail = ? ");
            pdstmt.setString(1, mail);
            ResultSet rs = pdstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(pass)) {
                    return true;
                }
            }
        return false;
    }

    public static boolean isAdministrator(String mail) {
        try {
            Connection con = ConPool.getConnection();
            Statement stmt = (Statement) con.createStatement();
            PreparedStatement pdstmt = con.prepareStatement("SELECT Administrator FROM Cliente WHERE Mail = ? ");
            pdstmt.setString(1, mail);
            ResultSet rs = pdstmt.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean(1)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
