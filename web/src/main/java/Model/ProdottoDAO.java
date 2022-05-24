package Model;

import java.sql.*;

public abstract class ProdottoDAO {

    /*public static void Upload(String marca, String modello,
                              Double prezzo, Integer quantita, Integer wattaggio, String tipo,
                              Float frequenza, Integer N_Core, Integer N_Ram, Integer N_Usb, Integer N_Pci,
                              Integer MBs, Integer Vram, Integer N_Watt, Integer W_Cpu, Short formaMobo,
                              String url, String Descrizione) throws SQLException {

        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String insProd = "INSERT INTO Pezzo (Marca, Modello, Prezzo, Quantita, Wattaggio, " +
                "Tipo, Frequenza, N_Core, N_Ram, N_USB, N_PCI, MBs, VRAM, N_Watt, W_Cpu," +
                " FormaMobo, url, Descrizione) " +
                "VALUES('" + marca + "','" + modello +
                "','" + prezzo + "','" + quantita + "','" + wattaggio
                + "','" + tipo + "','" + frequenza + "','" + N_Core + "','" + N_Ram + "','"
                + N_Usb + "','" + N_Pci + "','" + MBs + "','" + Vram + "','" + N_Watt + "','"
                + W_Cpu + "','" + formaMobo + "','" + url + "','" + Descrizione + "')";
        PreparedStatement pdstmt = con.prepareStatement(insProd);
        pdstmt.executeUpdate();
    }*/

    public static void Upload(String marca, String modello,
                              Double prezzo, Integer quantita, Integer wattaggio, String tipo,
                              Float frequenza, Integer N_Core, Integer N_Ram, Integer N_Usb, Integer N_Pci,
                              Integer MBs, Integer Vram, Integer N_Watt, Integer W_Cpu, Short formaMobo,
                              String url, String Descrizione) throws SQLException {

        String insProd = "INSERT INTO Pezzo (Marca, Modello, Prezzo, Quantita, " +
                "Wattaggio, Tipo, Frequenza, N_Core, N_Ram, N_USB, N_PCI, MBs, " +
                "VRAM, N_Watt, W_Cpu, FormaMobo, url, Descrizione)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(insProd);

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

}
