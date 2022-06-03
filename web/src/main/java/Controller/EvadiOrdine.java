package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.Cliente_.Cliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EvadiOrdine", value = "/EvadiOrdine")
public class EvadiOrdine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        Carrello carrello = (Carrello) ss.getAttribute("carrello");

        CarrelloDAO cDAO = new CarrelloDAO();

        //Evadi l'ordine nel db
        try {
            cDAO.setOrdineEvaso(cliente.getMail(), carrello.getCarrelloCod());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Rimuovi i prodotti acquistati dal db
        try {
            cDAO.scalaProdotti(carrello);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Rimuovi il carrello e il catalogo dalla sessione e
        //rigenera il catalogo in caso venga riconsultato
        ss.removeAttribute("carrello");
        ss.removeAttribute("catalogo");
        HomeServlet hs = new HomeServlet();
        hs.doGet(request, response);

        //redireziona alla home
        response.sendRedirect("index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
