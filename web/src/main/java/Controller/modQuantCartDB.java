package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "modQuantCartDB", value = "/modQuantCartDB")
public class modQuantCartDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quant = Integer.parseInt(request.getParameter("attr_newQuant"));
        int id = Integer.parseInt(request.getParameter("attr_id"));
        CarrelloDAO cDAO = new CarrelloDAO();
        int cartCod = ((Carrello) request.getSession().getAttribute("carrello")).getCarrelloCod();

        try {
            cDAO.modCart(cartCod, id, quant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
