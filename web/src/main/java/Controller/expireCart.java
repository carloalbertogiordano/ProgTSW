package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.Cliente_.Cliente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "expireCart", value = "/expireCart")
public class expireCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCarrello = Integer.parseInt(request.getParameter("idCarrello"));
        HttpSession session = request.getSession();
        Carrello carrelloSession = (Carrello) session.getAttribute("carrello");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
        CarrelloDAO service = new CarrelloDAO();
        CatalogoDAO serviceCatalogo = new CatalogoDAO();
        try {
            Carrello.scalaProdotti(carrelloSession);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            service.setOrdineEvaso(cliente.getMail(), idCarrello);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Carrello newCarrello;
        try {
            newCarrello = Carrello.createNewCarrello(cliente.getMail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Catalogo newCatalogo;
        try {
            newCatalogo = serviceCatalogo.doRetriveAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("catalogo", newCatalogo);
        session.setAttribute("carrello", newCarrello);
        response.sendRedirect("index.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}