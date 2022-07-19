package Model.CPU_;

import Model.CASE_.Case;
import Model.ConPool;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class CpuDAO {

    public CpuDAO(){
        super();
    }

    //Interroga il DB per avere una lista di prodotti di tipo CPU
    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "CPU");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                       int ID,                      String marca,           String modello,           double prezzo,           int quantità,             int wattaggio,          float frequenza,       int N_Core,                 String url,               String descrizione
            Cpu cpu = new Cpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(8), rs.getInt(9), rs.getString(18), rs.getString(19));
            list.add(cpu);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di cpu
    public ArrayList<Cpu> doRetriveByType() throws SQLException{
        CpuDAO cDAO = new CpuDAO();
        ArrayList<Prodotto> listP = cDAO.doRetrive();
        ArrayList<Cpu> listC = new ArrayList<Cpu>();
        for(Prodotto p : listP){
            listC.add((Cpu) p);
        }
        return listC;
    }
    public void Upload(Cpu c) throws SQLException {
        ProdottoDAO.Upload(c.getMarca(), c.getModello(), c.getPrezzo(), c.getQuantita(), c.getWattaggio(), c.getTipo(), c.getFrequenza(), c.getN_Core(), null, null, null, null, null, null, null, null, c.getUrl(), c.getDescrizione());
    }

}
