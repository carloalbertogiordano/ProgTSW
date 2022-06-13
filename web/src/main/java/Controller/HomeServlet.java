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
            //We need to take catalogo from session because in this attribute we have the updated quantity
            catalogo = (Catalogo) ss.getAttribute("catalogo");
            //check catalogo is null or not
            if (catalogo == null) {
                try {
                    //if is null we take all elements from database and set session attribute catalogo
                    catalogo = service.doRetriveAll();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                ss.setAttribute("catalogo", catalogo);
            }
            /*else{
                ss.setAttribute("catalogo", catalogo);
            }*/
            //If the session isn't new we need to control if the Cliente is null or not to check if he's logged in
            if (c != null) {
                //Cliente is logged
                //Take Carrello from session
                Carrello carrello = (Carrello) ss.getAttribute("carrello");
                //create a new Carrello where we put carrello from DataBase
                Carrello carrelloDB = new Carrello();
                CarrelloDAO serviceCarrello = new CarrelloDAO();
                //If session Carrello isn't null we need to do join between session Carrello and DataBase Carrello
                if (carrello != null) {
                    //Take Carrello from DataBase using the mail of Cliente
                    try {
                        carrelloDB = serviceCarrello.doRetriveByMailCliente(c.getMail());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //Do join
                    carrello = carrello.joinCarrelli(carrelloDB);
                    try {
                        serviceCarrello.updateCarrello(carrello);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //Use Catalogo to store the session Catalogo and update the quantit
                    catalogo = (Catalogo) ss.getAttribute("catalogo");
                    catalogo.aggiornaQuantita(carrello);
                    //set session attribute catalogo and carrello
                    ss.setAttribute("catalogo", catalogo);
                    ss.setAttribute("carrello", carrello);
                } else {
                    //If session Carrello is null we need to take Carrello from the DataBase and set it as session Carrello
                    Carrello carrelloDb = new Carrello();
                    try {
                        carrelloDb = serviceCarrello.doRetriveByMailCliente(c.getMail());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //Create new Catalogo and use it to store the session Catalogo and update the quantity
                    catalogo = (Catalogo) ss.getAttribute("catalogo");
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
