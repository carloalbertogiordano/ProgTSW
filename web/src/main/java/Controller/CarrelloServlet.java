package Controller;

import Model.Carrello_.CarrelloDAO;
import Model.Carrello_.Carrello;
import Model.Cliente_.*;
import Model.Cliente_.Cliente;
import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CarrelloServlet", value = "/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente c = (Cliente) session.getAttribute("cliente");
        if(c != null){
            //L'utente è loggato
            //Prendo il carrello dalla sessione
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            if(carrello != null) {
                //join tra il carrello nella sessione e carrello dell'utente nel database (tabella Ordine)
                //Prendo il carrello dal DB
                CarrelloDAO service = new CarrelloDAO();
                Carrello newCarrello = new Carrello();
                try {
                    newCarrello = service.doRetriveByMailCliente(c.getMail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //Richiamo il metodo per joinare i carrelli della sessione e del DB

            }
            else{
                //caricare il carrello dell'utente dal database (tabella Ordine)
                CarrelloDAO service = new CarrelloDAO();
                Carrello newCarrello = new Carrello();
                try {
                    newCarrello = service.doRetriveByMailCliente(c.getMail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                session.setAttribute("carrello", newCarrello);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("carrello.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
