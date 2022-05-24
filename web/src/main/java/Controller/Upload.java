package Controller;

import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Upload", value = "/Upload")
public class Upload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String marca = request.getParameter("marca");
        String modello = request.getParameter("modello");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int quantità = Integer.parseInt(request.getParameter("quantità"));
        int wattaggio = Integer.parseInt(request.getParameter("wattaggio"));
        String tipo = request.getParameter("tipo");
        float frequenza = Float.parseFloat(request.getParameter("frequenza"));
        int N_Core = Integer.parseInt(request.getParameter("N_Core"));
        int N_Ram = Integer.parseInt(request.getParameter("N_Ram"));
        int N_Usb = Integer.parseInt(request.getParameter("N_Usb"));
        int N_Pci = Integer.parseInt(request.getParameter("N_Pci"));
        int MBs = Integer.parseInt(request.getParameter("MBs"));
        int Vram = Integer.parseInt(request.getParameter("Vram"));
        int N_Watt = Integer.parseInt(request.getParameter("N_Watt"));
        int W_Cpu = Integer.parseInt(request.getParameter("W_Cpu"));
        short formaMobo = Short.parseShort(request.getParameter("formaMobo"));
        String url = request.getParameter("url");
        String descrizione = request.getParameter("descrizione");
        try {
            ProdottoDAO.Upload(marca, modello, prezzo, quantità, wattaggio, tipo, frequenza, N_Core, N_Ram, N_Usb, N_Pci, MBs, Vram, N_Watt, W_Cpu, formaMobo, url, descrizione);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
