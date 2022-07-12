package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.Cliente_.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Take session
        HttpSession ss = request.getSession();

        //Take Attribute from session
        Cliente c = (Cliente) ss.getAttribute("cliente");

        //Create new instance
        CatalogoDAO service = new CatalogoDAO();
        Catalogo catalogo = new Catalogo();

        //Check if session is new
        if (ss.isNew()) {
            //if the session is new we need to take all elements from database, set it in a catalogo, create a new empty carrello, and add catalogo and carrello as session Attribute
            Carrello carrello = new Carrello();
            try {
                catalogo = service.doRetriveAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ss.setAttribute("catalogo", catalogo);
            ss.setAttribute("carrello", carrello);
        }
        //The session isn't new
        else {
            //We need to take catalogo from database because in this attribute we have the updated quantity
            CatalogoDAO catalogoDAO = new CatalogoDAO();
            try {
                catalogo = catalogoDAO.doRetriveAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //If the session isn't new we need to control if the Cliente is null or not to check if he's logged in
            if (c != null) {
                //Cliente is logged
                //Take Carrello from session
                Carrello carrello = (Carrello) ss.getAttribute("carrello");
                //create a new Carrello where we put carrello from DataBase
                Carrello carrelloDB = new Carrello();
                CarrelloDAO serviceCarrello = new CarrelloDAO();
                try {
                    carrelloDB = serviceCarrello.doRetriveByMailCliente(c.getMail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                };
                //If session Carrello isn't null we need to do join between session Carrello and DataBase Carrello
                if (carrello != null) {
                    //Take Carrello from DataBase using the mail of Cliente

                    //Do join
                    carrello.joinCarrelli(carrelloDB);

                    //Checks if the quantity required by the cart is less than the quantity avilable
                    try {
                        carrello.doCheckList(catalogo);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        //Update carrello in the DB
                        serviceCarrello.updateCarrello(carrello);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //Use Catalogo to store the session Catalogo and update the quantit
                    //Sets the quantity in catalogo as the quantity in catalogo minus the quantity in carrello
                    catalogo.aggiornaQuantita(carrello);
                    //set session attribute catalogo and carrello
                    ss.setAttribute("catalogo", catalogo);
                    ss.setAttribute("carrello", carrello);
                } else {
                    //If session Carrello is null we need to take Carrello from the DataBase and set it as session Carrello
                    Carrello carrelloDb = new Carrello();

                    //Create new Catalogo and use it to store the session Catalogo and update the quantity
                    catalogo.aggiornaQuantita(carrelloDb);
                    //set session attribute catalogo and carrello
                    ss.setAttribute("catalogo", catalogo);
                    ss.setAttribute("carrello", carrelloDb);
                }
            } else {
                //il cliente non è loggato
                Carrello carrelloSession = (Carrello) ss.getAttribute("carrello");
                if (carrelloSession == null) {
                    Carrello carrello = new Carrello();
                    ss.setAttribute("carrello", carrello);
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
