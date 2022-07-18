package Controller;

import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.Cliente_.Cliente;
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
        HttpSession ss = request.getSession();
        int quant = Integer.parseInt(request.getParameter("attr_newQuant"));
        int id = Integer.parseInt(request.getParameter("attr_id"));
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        int cartCod = ((Carrello) ss.getAttribute("carrello")).getCarrelloCod();
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        Catalogo catalogoSessione = (Catalogo) ss.getAttribute("catalogo");
        int oldQuant = Integer.parseInt(request.getParameter("attr_OldQuant")); //catalogoDB.getCatalogo().size() - catalogoSessione.getCatalogo().size();

        if(oldQuant<quant)
            for (Prodotto p : catalogoSessione.getCatalogo()) {
                if (p.getID() == id)
                    p.setQuantità( (p.getQuantità()+oldQuant) - quant);
            }

        ss.setAttribute("catalogo", catalogoSessione);

        try {
            carrelloDAO.modCart(cartCod, id, quant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Carrello cart = null;
        try {
            cart = (Carrello) carrelloDAO.doRetriveByMailCliente(cliente.getMail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ss.setAttribute("carrello", cart);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
