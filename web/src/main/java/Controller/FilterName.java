package Controller;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.CASE_.Case;
import Model.CATALOGO_.Catalogo;
import Model.CPU_.Cpu;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.RAM_.Ram;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.out;

@WebServlet(name = "FilterName", value = "/FilterName")
public class FilterName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("input_cerca");
        String scelta = request.getParameter("radio_scelta");
        System.out.println("cerco per: " + text);

        Catalogo oldCatalogo = (Catalogo) request.getSession().getAttribute("catalogo");
        assert oldCatalogo != null;

        Catalogo c = null;

        if (scelta.equals("Marca")) {
            System.out.println("scelta: " + scelta);
            c = oldCatalogo.filterByMarca(text);

        } else if (scelta.equals("Modello")) {
            System.out.println("scelta: " + scelta);
            c = oldCatalogo.filterByModello(text);
        }

        assert c != null;
        if (!c.isEmpty()) {
            request.getSession().setAttribute("filterCatalog", c);
            request.getSession().setAttribute("printCatalog", c);
            response.sendRedirect("AsincCatalogPrinter");
        } else {
            response.getWriter().println("Err: nessun risultato trovato");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}


