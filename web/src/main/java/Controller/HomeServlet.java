package Controller;

import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.Catalogo;
import Model.CatalogoDAO;
import Model.Cliente_.Cliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        CatalogoDAO service = new CatalogoDAO();
        Cliente c = (Cliente) ss.getAttribute("cliente");
        if(ss.isNew()){
            Carrello carrello = new Carrello();
            Catalogo catalogo = new Catalogo();
            try {
                catalogo = service.doRetriveAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ss.setAttribute("catalogo", catalogo);
            ss.setAttribute("carrello", carrello);
        }
        else{
            Catalogo catalogo = new Catalogo();
            catalogo = (Catalogo) ss.getAttribute("catalogo");
            if(catalogo == null){
                try {
                    catalogo = service.doRetriveAll();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                ss.setAttribute("catalogo", catalogo);
            }
            else{
                ss.setAttribute("catalogo", catalogo);
            }

            if(c != null) {
                //L'utente è loggato
                //Prendo il carrello dalla sessione
                Carrello carrello = (Carrello) ss.getAttribute("carrello");
                Carrello carrelloDB = new Carrello();
                CarrelloDAO serviceCarrello = new CarrelloDAO();
                if (carrello != null) {
                        //join tra il carrello nella sessione e carrello dell'utente nel database (tabella Ordine)
                        //Prendo il carrello dal DB
                        try {
                            carrelloDB = serviceCarrello.doRetriveByMailCliente(c.getMail());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        //Richiamo il metodo per joinare i carrelli della sessione e del DB
                        carrello = carrello.joinCarrelli(carrelloDB);
                        Catalogo cat = new Catalogo();
                        cat = (Catalogo) ss.getAttribute("catalogo");
                        cat.aggiornaQuantita(carrello);
                        ss.setAttribute("catalogo", cat);
                        ss.setAttribute("carrello", carrello);
                } else {
                    //caricare il carrello dell'utente dal database (tabella Ordine)
                    Carrello carrelloDb = new Carrello();
                    try {
                        carrelloDb = serviceCarrello.doRetriveByMailCliente(c.getMail());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Catalogo cat = new Catalogo();
                    cat = (Catalogo) ss.getAttribute("catalogo");
                    cat.aggiornaQuantita(carrelloDb);
                    ss.setAttribute("catalogo", cat);
                    ss.setAttribute("carrello", carrelloDb);
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
