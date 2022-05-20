package Model;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdottoDAO {
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pezzo WHERE ");
    }
}
