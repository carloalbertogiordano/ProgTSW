package Controller;

import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
////Aggiorna il prodotto nal DB e nella sessione
@WebServlet(name = "Aggiorna", value = "/Aggiorna")
public class Aggiorna extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        String marca = request.getParameter("marca");
        String modello = request.getParameter("modello");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        String desc = request.getParameter("desc");
        String url = request.getParameter("url");

        //Inizializza un campo a null e in caso sia stato passato ne aggiorna il valore
        Integer wattaggio = null;
        if (request.getParameter("wattaggio") != null) {
            wattaggio = Integer.parseInt(request.getParameter("wattaggio"));
        }
        Float frequenza = null;
        if (request.getParameter("frequenza") != null) {
            frequenza = Float.parseFloat(request.getParameter("frequenza"));
        }
        Integer N_Core = null;
        if (request.getParameter("N_Core") != null) {
            N_Core = Integer.parseInt(request.getParameter("N_Core"));
        }
        Integer N_Ram = null;
        if (request.getParameter("N_Ram") != null) {
            N_Ram = Integer.parseInt(request.getParameter("N_Ram"));
        }
        Integer N_Usb = null;
        if (request.getParameter("N_Usb") != null) {
            N_Usb = Integer.parseInt(request.getParameter("N_Usb"));
        }
        Integer N_Pci = null;
        if (request.getParameter("N_Pci") != null) {
            N_Pci = Integer.parseInt(request.getParameter("N_Pci"));
        }
        Integer MBs = null;
        if (request.getParameter("MBs") != null) {
            MBs = Integer.parseInt(request.getParameter("MBs"));
        }
        Integer Vram = null;
        if (request.getParameter("Vram") != null) {
            Vram = Integer.parseInt(request.getParameter("Vram"));
        }
        Integer N_Watt = null;
        if (request.getParameter("N_Watt") != null) {
            N_Watt = Integer.parseInt(request.getParameter("N_Watt"));
        }
        Integer W_Cpu = null;
        if (request.getParameter("W_Cpu") != null) {
            W_Cpu = Integer.parseInt(request.getParameter("W_Cpu"));
        }
        Short formaMobo = null;
        if (request.getParameter("formaMobo") != null) {
            formaMobo = Short.parseShort(request.getParameter("formaMobo"));
        }

        try {
            //Aggiorna il prodotto nal DB. Il metodo Upload gestisce eventuali paramentri nulli
            ProdottoDAO.Update( id,  marca,  modello,
                     prezzo,  quantita,  wattaggio,
                     frequenza,  N_Core,  N_Ram,  N_Usb, N_Pci,
                     MBs,  Vram,  N_Watt,  W_Cpu,  formaMobo,
                     url,  desc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Rigenera il catalogo con i nuovi prodotti
        CatalogoDAO service = new CatalogoDAO();
        Catalogo newCatalogo;
        try {
            newCatalogo = service.doRetriveAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession ss = request.getSession();
        ss.setAttribute("catalogo", newCatalogo);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
