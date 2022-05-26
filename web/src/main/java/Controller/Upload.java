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
        String prezzo1 = request.getParameter("prezzo");
        prezzo1.replace(",", ".");
        double prezzo = Double.parseDouble(prezzo1);
        int quantità = Integer.parseInt(request.getParameter("quantita"));
        String tipo = request.getParameter("tipo");

        //Inizializza un campo a null e in caso sia stato passato ne aggiorna il valore
        Integer wattaggio = null;
        if(request.getParameter("wattaggio") != null) {
            wattaggio = Integer.parseInt(request.getParameter("wattaggio"));
        }
        Float frequenza = null;
        if(request.getParameter("frequenza") != null) {
            frequenza = Float.parseFloat(request.getParameter("frequenza"));
        }
        Integer N_Core = null;
        if(request.getParameter("N_Core") != null) {
            N_Core = Integer.parseInt(request.getParameter("N_Core"));
        }
        Integer N_Ram = null;
        if(request.getParameter("N_Ram") != null) {
            N_Ram = Integer.parseInt(request.getParameter("N_Ram"));
        }
        Integer N_Usb = null;
        if(request.getParameter("N_Usb") != null) {
            N_Usb = Integer.parseInt(request.getParameter("N_Usb"));
        }
        Integer N_Pci = null;
        if(request.getParameter("N_Pci") != null) {
            N_Pci = Integer.parseInt(request.getParameter("N_Pci"));
        }
        Integer MBs = null;
        if(request.getParameter("MBs") != null) {
            MBs = Integer.parseInt(request.getParameter("MBs"));
        }
        Integer Vram = null;
        if(request.getParameter("Vram") != null) {
            Vram = Integer.parseInt(request.getParameter("Vram"));
        }
        Integer N_Watt = null;
        if(request.getParameter("N_Watt") != null) {
            N_Watt = Integer.parseInt(request.getParameter("N_Watt"));
        }
        Integer W_Cpu = null;
        if(request.getParameter("W_Cpu") != null) {
            W_Cpu = Integer.parseInt(request.getParameter("W_Cpu"));
        }
        Short formaMobo = null;
        if(request.getParameter("formaMobo") != null) {
            formaMobo = Short.parseShort(request.getParameter("formaMobo"));
        }
        String url = request.getParameter("url");
        String descrizione = request.getParameter("descrizione");
        try {
            //Carica il prodotto nal DB. Il metodo Upload gestisce eventuali paramentri nulli
            ProdottoDAO.Upload(marca, modello, prezzo, quantità, wattaggio, tipo, frequenza, N_Core, N_Ram, N_Usb, N_Pci, MBs, Vram, N_Watt, W_Cpu, formaMobo, url, descrizione);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("admin.jsp");
    }
}
