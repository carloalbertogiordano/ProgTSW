package Model;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdottoDAO<E> {
    public List<Prodotto> doRetriveAll() throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pezzo");
        ResultSet rs = ps.executeQuery();
        List<Prodotto> list = new ArrayList<Prodotto>();
        while(rs.next()) {
            Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5));
            list.add(p);
        }
        return list;
    }

    public List<Prodotto> doRetriveByType(String type) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pezzo WHERE tipo = ?");
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        List<Prodotto> list = new ArrayList<Prodotto>();
        while(rs.next()){
            Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(6));
            p.setWattaggio(rs.getInt(5));
            p.setFrequenza(rs.getInt(7));
            p.setN_CORE(rs.getInt(8));
            p.setN_RAM(rs.getInt(9));
            p.setN_USB(rs.getInt(10));
            p.setN_PCI(rs.getInt(11));
            p.setMBs(rs.getInt(12));
            p.setVRAM(rs.getInt(13));
            p.setN_WATT(rs.getInt(14));
            p.setW_CPU(rs.getInt(15));
            p.setFormaMobo(rs.getShort(16));
            p.setUrl(rs.getString(17));
            p.setDescrizione(rs.getString(18));
            list.add(p);
        }
        return list;
    }
}
