package Controller;
/*
import Model.Carrello;
import Model.CarrelloDAO;
import Model.Cliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CarrelloServlet", value = "/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente c = (Cliente) session.getAttribute("cliente");
        if(c != null){
            //L'utente è loggato
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            if(carrello != null){
                //join tra il carrello nella sessione e carrello dell'utente nel database (tabella Ordine)
                CarrelloDAO carrelloDAO = new CarrelloDAO();
                carrelloDAO.doJoinSessionDBCarrello(carrello, c.getMail());
            }
            else{
                //caricare il carrello dell'utente dal database (tabella Ordine)
                CarrelloDAO carrelloDAO = new CarrelloDAO();

            }
        }
        else{
            //L'utente non è loggato
            //caricare il carrello nella sessione
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
*/