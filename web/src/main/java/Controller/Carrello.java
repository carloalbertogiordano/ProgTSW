package Controller;

import Model.Cliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Carrello", value = "/Carrello")
public class Carrello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente c = (Cliente) session.getAttribute("cliente");
        if(c != null){
            //L'utente è loggato
            Carrello carrello = session.getAttribute("carrello");
            if(carrello != null){
                //join tra il carrello nella sessione e carrello dell'utente nel database (tabella Ordine)
            }
            else{
                //caricare il carrello dell'utente dal database (tabella Ordine)
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
