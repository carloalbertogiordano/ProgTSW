package Model;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    public void addCliente(Cliente c) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String insert = "INSERT INTO Cliente (Nickname, Mail, Pass, Tel, Via, Provincia) VALUES ('" + c.getNickname() + "', '" + c.getMail() + "', '" + c.getPass() + "', '" + c.getTel() + "', '" + c.getVia() + "', '" + c.getProvincia() + "')";
        stmt.executeUpdate(insert);
    }

    public Cliente doRetrieveByMail(String m) throws SQLException {
        Cliente cliente = null;
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String select = "SELECT * FROM Cliente WHERE Mail = '" + m + "'";
        PreparedStatement pdstmt = con.prepareStatement(select);
        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            cliente = new Cliente( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7));
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
            Cliente c = new Cliente( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7));
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
            String select = "SELECT Pass FROM Cliente WHERE Mail = '" + mail + "'";
            PreparedStatement pdstmt = con.prepareStatement(select);
            ResultSet rs = pdstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(pass)) {
                    return true;
                }
            }
        return false;
    }
}
