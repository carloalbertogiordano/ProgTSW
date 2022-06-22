package Model.CATALOGO_;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.HDD_.HddDAO;
import Model.Archiviazione_.SDD_.Ssd;
import Model.Archiviazione_.SDD_.SsdDAO;
import Model.CASE_.Case;
import Model.CASE_.CaseDAO;
import Model.CPU_.Cpu;
import Model.CPU_.CpuDAO;
import Model.ConPool;
import Model.DISSIPATORE_.Dissipatore;
import Model.DISSIPATORE_.DissipatoreDAO;
import Model.GPU_.Gpu;
import Model.GPU_.GpuDAO;
import Model.MOBO_.Mobo;
import Model.MOBO_.MoboDAO;
import Model.PSU_.Psu;
import Model.PSU_.PsuDAO;
import Model.Prodotto;
import Model.RAM_.Ram;
import Model.RAM_.RamDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDAO {
    public Catalogo doRetriveAll() throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo");
        ResultSet rs = pdstmt.executeQuery();
        List<Prodotto> catalogo = new ArrayList<Prodotto>();
        while (rs.next()) {
            String type = rs.getString(7);
            switch (type) {
                case "CPU": {
                    Cpu cpu = new Cpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(8), rs.getInt(9), rs.getString(18), rs.getString(19));
                    catalogo.add(cpu);
                    break;
                }
                case "MOBO": {
                    Mobo mobo = new Mobo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getShort(17), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(18), rs.getString(19));
                    catalogo.add(mobo);
                    break;
                }
                case "CASE": {
                    Case case_ = new Case(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getShort(17), rs.getString(18), rs.getString(19));
                    catalogo.add(case_);
                    break;
                }
                case "DISSIPATORE": {
                    Dissipatore dissipatore = new Dissipatore(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(16), rs.getString(18), rs.getString(19));
                    catalogo.add(dissipatore);
                    break;
                }
                case "GPU": {
                    Gpu gpu = new Gpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(8), rs.getInt(13), rs.getString(18), rs.getString(19));
                    catalogo.add(gpu);
                    break;
                }
                case "PSU": {
                    Psu psu = new Psu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(15), rs.getString(18), rs.getString(19));
                    catalogo.add(psu);
                    break;
                }
                case "RAM": {
                    Ram ram = new Ram(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getFloat(8), rs.getString(18), rs.getString(19));
                    catalogo.add(ram);
                    break;
                }
                case "HDD": {
                    Hdd hdd = new Hdd(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(13), rs.getString(18), rs.getString(19));
                    catalogo.add(hdd);
                    break;
                }
                case "SSD": {
                    Ssd ssd = new Ssd(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(13), rs.getString(18), rs.getString(19));
                    catalogo.add(ssd);
                    break;
                }
                default: {

                }
            }
        }
        Catalogo cat = new Catalogo();
        cat.setCatalogo(catalogo);
        return cat;
    }


}

