package Model.CATALOGO_;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.HDD_.HddDAO;
import Model.Archiviazione_.SDD_.Ssd;
import Model.Archiviazione_.SDD_.SsdDAO;
import Model.CASE_.Case;
import Model.CASE_.CaseDAO;
import Model.CPU_.Cpu;
import Model.CPU_.CpuDAO;
import Model.Carrello_.Carrello;
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
import Model.ProdottoDAO;
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
                    Cpu cpu = CpuDAO.InitCpuFromRs(rs);
                    catalogo.add(cpu);
                    break;
                }
                case "MOBO": {
                    Mobo mobo = MoboDAO.InitMoboFromRs(rs);
                    catalogo.add(mobo);
                    break;
                }
                case "CASE": {
                    Case case_ = CaseDAO.InitCaseFromRs(rs);
                    catalogo.add(case_);
                    break;
                }
                case "DISSIPATORE": {
                    //Dissipatore dissipatore = new Dissipatore(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(16), rs.getString(18), rs.getString(19));
                    Dissipatore diss = DissipatoreDAO.InitDissipatoreFromRs(rs);
                    catalogo.add(diss);
                    break;
                }
                case "GPU": {
                    Gpu gpu = GpuDAO.InitGpuFromRs(rs);
                    catalogo.add(gpu);
                    break;
                }
                case "PSU": {
                    Psu psu = PsuDAO.InitPsuFromRs(rs);
                    catalogo.add(psu);
                    break;
                }
                case "RAM": {
                    Ram ram = RamDAO.InitRamFromRs(rs);
                    catalogo.add(ram);
                    break;
                }
                case "HDD": {
                    Hdd hdd = HddDAO.InitHddFromRs(rs);
                    catalogo.add(hdd);
                    break;
                }
                case "SSD": {
                    Ssd ssd = new Ssd();
                    ssd.setID(rs.getInt(1));
                    ssd.setMarca(rs.getString(2));
                    ssd.setModello(rs.getString(3));
                    ssd.setPrezzo(rs.getInt(4));
                    ssd.setQuantita(rs.getInt(5));
                    ssd.setMBs(rs.getInt(13));
                    ssd.setUrl(rs.getString(18));
                    ssd.setDescrizione(rs.getString(19));
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

    //Scala un prodotto dal db dato un id
    public void scalaProdotto(Prodotto p) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Pezzo SET Quantita = Quantita - ? WHERE Id = ?");
        pdstmt.setInt(1, p.getQuantita());
        pdstmt.setInt(2, p.getID());
        pdstmt.executeUpdate();
    }

    //Scala una lista di prodotti dal db dato un carrello
    public void scalaProdotti(Carrello c) throws SQLException {
        CatalogoDAO service = new CatalogoDAO();
        for(Prodotto p : c.getCarrello()){
            service.scalaProdotto(p);
        }
    }
}

