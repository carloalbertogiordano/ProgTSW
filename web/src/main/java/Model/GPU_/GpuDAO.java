package Model.GPU_;

import Model.Archiviazione_.SDD_.Ssd;
import Model.Archiviazione_.SDD_.SsdDAO;
import Model.CASE_.Case;
import Model.ConPool;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class GpuDAO {

    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "GPU");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                               int ID,                String marca,           String modello,              double prezzo,            int quantità,          int wattaggio,         int frequenza,              int vRam,                 , String url,           String descrizione,
            Gpu ssd = new Gpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(8), rs.getInt(14), rs.getString(18), rs.getString(19));
            list.add(ssd);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di GPU
    public ArrayList<Gpu> doRetriveByType() throws SQLException{
        GpuDAO gDAO = new GpuDAO();
        ArrayList<Prodotto> listP = gDAO.doRetrive();
        ArrayList<Gpu> listG = new ArrayList<Gpu>();
        for(Prodotto p : listP){
            listG.add((Gpu) p);
        }
        return listG;
    }

    public void Upload(Gpu g) throws SQLException {
        ProdottoDAO.Upload(g.getMarca(), g.getModello(), g.getPrezzo(), g.getQuantità(), g.getWattaggio(), g.getTipo(), null, null, null, null, null, null, g.getVRam(), null, null, null, g.getUrl(), g.getDescrizione());
    }

}
