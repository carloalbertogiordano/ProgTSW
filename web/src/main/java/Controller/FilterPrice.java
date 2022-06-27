package Controller;

import Model.CATALOGO_.Catalogo;
import Model.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "FilterPrice", value = "/FilterPrice")
public class FilterPrice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int price = Integer.parseInt(request.getParameter("input_prezzo"));
        //Se il catalogo è già stato filtrato per nome allora questo è il catalogo
        Catalogo filteredCatalog = (Catalogo) request.getSession().getAttribute("filterCatalog");
        //Altrimenti se questo non è ancora successo utilizziamo l'intero catalogo
        if (filteredCatalog == null){
            System.out.println("FileredCatalog is null. Is this firt filter applied?");
            filteredCatalog = (Catalogo) request.getSession().getAttribute("catalogo");
        }

        assert filteredCatalog != null;
        Catalogo c = filteredCatalog.filterByPrezzo(price);

        request.getSession().setAttribute("printCatalog", c);
        response.sendRedirect("AsincCatalogPrinter");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
