package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.Cliente_.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

//Scade il carrello settandolo a evaso nel DB e eliminandolo dalla sessione
@WebServlet(name = "expireCart", value = "/expireCart")
public class expireCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer idCarrello = Integer.parseInt(request.getParameter("idCarrello"));
        HttpSession session = request.getSession();
        Carrello carrelloSession = (Carrello) session.getAttribute("carrello");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        CatalogoDAO serviceCatalogo = new CatalogoDAO();

        if(idCarrello==null || session==null || carrelloSession==null || cliente==null || serviceCatalogo==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);

        if(((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
            request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);

        //Info spedizione
        String via = request.getParameter("via");
        String provincia = request.getParameter("provincia");
        String citta = request.getParameter("citta");
        int cap = Integer.parseInt(request.getParameter("cap"));

        //Scala i prodotti dal catalogo
        try {
            serviceCatalogo.scalaProdotti(carrelloSession);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            //Sceglie se aggiornare l'indirizzo oppure no e quindi invia l'ordine al DAO
            if(cliente.getVia().equals(via) && cliente.getProvincia().equals(provincia) && cliente.getCitta().equals(citta) && cliente.getCap() == cap)
                carrelloSession.forwardOrderWithDifferentAddress(idCarrello, cliente);
            else
                carrelloSession.forwardOrder(idCarrello, cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Crea un nuovo carrello
        Carrello newCarrello;
        try {
            newCarrello = Carrello.createNewCarrello(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Crea un nuovo catalogo
        Catalogo newCatalogo = new Catalogo();
        try {
            newCatalogo.setCatalogo(serviceCatalogo.doRetriveAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Setta i nuovi carrello e catalogo come attributi nella sessione
        session.setAttribute("catalogo", newCatalogo);
        session.setAttribute("carrello", newCarrello);
        response.sendRedirect("index.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
