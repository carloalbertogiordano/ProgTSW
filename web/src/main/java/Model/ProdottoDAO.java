package Model;

import java.sql.*;

public abstract class ProdottoDAO {

    public static void Upload(String marca, String modello,
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
        ResultSet rs = pdstmt.executeQuery();

    }

}
