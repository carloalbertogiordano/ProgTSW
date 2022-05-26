package Model;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.CASE_.Case;
import Model.CPU_.Cpu;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.RAM_.Ram;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ProdottoDAO {

    //Aggiorna lo stock di un prodotto
    public static void aggiorna(int id, int q) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("UPDATE Pezzo SET quantita=? WHERE Id=?");
        ps.setString(1, String.valueOf(q));
        ps.setString(2, String.valueOf(id));
        ps.executeUpdate();
    }

    //Carica un prodotto nel db
    public static void Upload(String marca, String modello,
                              Double prezzo, Integer quantita, Integer wattaggio, String tipo,
                              Float frequenza, Integer N_Core, Integer N_Ram, Integer N_Usb, Integer N_Pci,
                              Integer MBs, Integer Vram, Integer N_Watt, Integer W_Cpu, Short formaMobo,
                              String url, String Descrizione) throws SQLException {
        //Inizializza la stringa di connessione
        String insProd = "INSERT INTO Pezzo (Marca, Modello, Prezzo, Quantita, " +
                "Wattaggio, Tipo, Frequenza, N_Core, N_Ram, N_USB, N_PCI, MBs, " +
                "VRAM, N_Watt, W_Cpu, FormaMobo, url, Descrizione)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(insProd);
        //Se l'oggetto passato non è null ne viene inserito il valore nela query, altrimenti viene inserito null in quella posizione
        if (marca != null) pdstmt.setString(1, marca);
        else pdstmt.setNull(1, Types.VARCHAR);

        if (modello != null) pdstmt.setString(2, modello);
        else pdstmt.setNull(2, Types.VARCHAR);

        if (prezzo != null) pdstmt.setDouble(3, prezzo);
        else pdstmt.setNull(3, Types.DOUBLE);

        if (quantita != null) pdstmt.setInt(4, quantita);
        else pdstmt.setNull(4, Types.INTEGER);

        if (wattaggio != null) pdstmt.setInt(5, wattaggio);
        else pdstmt.setNull(5, Types.INTEGER);

        if (tipo != null) pdstmt.setString(6, tipo);
        else pdstmt.setNull(6, Types.VARCHAR);

        if (frequenza != null) pdstmt.setFloat(7, frequenza);
        else pdstmt.setNull(7, Types.FLOAT);

        if (N_Core != null) pdstmt.setInt(8, N_Core);
        else pdstmt.setNull(8, Types.INTEGER);

        if (N_Ram != null) pdstmt.setInt(9, N_Ram);
        else pdstmt.setNull(9, Types.INTEGER);

        if (N_Usb != null) pdstmt.setInt(10, N_Usb);
        else pdstmt.setNull(10, Types.INTEGER);

        if (N_Pci != null) pdstmt.setInt(11, N_Pci);
        else pdstmt.setNull(11, Types.INTEGER);

        if (MBs != null) pdstmt.setInt(12, MBs);
        else pdstmt.setNull(12, Types.INTEGER);

        if (Vram != null) pdstmt.setInt(13, Vram);
        else pdstmt.setNull(13, Types.INTEGER);

        if (N_Watt != null) pdstmt.setInt(14, N_Watt);
        else pdstmt.setNull(14, Types.INTEGER);

        if (W_Cpu != null) pdstmt.setInt(15, W_Cpu);
        else pdstmt.setNull(15, Types.INTEGER);

        if (formaMobo != null) pdstmt.setShort(16, formaMobo);
        else pdstmt.setNull(16, Types.SMALLINT);

        if (url != null) pdstmt.setString(17, url);
        else pdstmt.setNull(17, Types.VARCHAR);

        if (Descrizione != null) pdstmt.setString(18, Descrizione);
        else pdstmt.setNull(18, Types.VARCHAR);

        pdstmt.executeUpdate();

    }

    //Metodo per prendere un prodotto dal database dato l'ID
    public static Prodotto doRetriveById(Integer ID) throws SQLException {
        System.out.println("Metodo doRetriveById, parametro: " + ID);
        int x = ID;
        Connection con = ConPool.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Pezzo WHERE Id = ?");
        ps.setInt(1, x);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String type = rs.getString(7);
            //In base al valore contenuto nel parametro type viene istanziato un oggetto di quella classe
            switch (type) {
                case "CPU":{
                    Cpu cpu = new Cpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(8), rs.getInt(9), rs.getString(18), rs.getString(19));
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
                case "HDD":{
                    Hdd hdd = new Hdd(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(13), rs.getString(18), rs.getString(19));
                    return hdd;
                }
                case "SSD":{
                    Ssd ssd = new Ssd(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(13), rs.getString(18), rs.getString(19));
                    System.out.println(ssd.toString());
                    return ssd;
                }
                default:{
                    Prodotto prodotto = null;
                    return prodotto;
                }
            }
        }
        return null;
    }
    //Elimina un prodotto dal DB
    public static void elimina(int id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Pezzo WHERE Id=?");
        ps.setString(1, String.valueOf(id));
        ps.executeUpdate();
    }

    public static List<Prodotto> doRetriveByType(String type) throws SQLException {
        List<Prodotto> list = new ArrayList<Prodotto>();
        Connection con = ConPool.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ?");
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            switch (type) {
                case "CPU":{
                    Cpu cpu = new Cpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(8), rs.getInt(9), rs.getString(18), rs.getString(19));
                    list.add(cpu);
                }
                case "MOBO":{
                    Mobo mobo = new Mobo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getShort(17), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(18), rs.getString(19));
                    list.add(mobo);
                }
                case "CASE":{
                    Case case_ = new Case(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getShort(17), rs.getString(18), rs.getString(19));
                    list.add(case_);
                }
                case "DISSIPATORE":{
                    Dissipatore dissipatore = new Dissipatore(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(16), rs.getString(18), rs.getString(19));
                    list.add(dissipatore);
                }
                case "GPU":{
                    Gpu gpu = new Gpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(8), rs.getInt(13), rs.getString(18), rs.getString(19));
                    list.add(gpu);
                }
                case "PSU":{
                    Psu psu = new Psu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(15), rs.getString(18), rs.getString(19));
                    list.add(psu);
                }
                case "RAM":{
                    Ram ram = new Ram(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getFloat(8), rs.getString(18), rs.getString(19));
                    list.add(ram);
                }
                case "HDD":{
                    Hdd hdd = new Hdd(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(13), rs.getString(18), rs.getString(19));
                    list.add(hdd);
                }
                case "SSD":{
                    Ssd ssd = new Ssd(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(13), rs.getString(18), rs.getString(19));
                    list.add(ssd);
                }
                default:{
                    Prodotto prodotto = null;
                }
            }
        }
        return list;
    }
}
