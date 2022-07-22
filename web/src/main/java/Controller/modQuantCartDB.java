package Controller;

import Model.CATALOGO_.Catalogo;
import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.Cliente_.Cliente;
import Model.Prodotto;
import Model.ProdottoDAO;
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
        Carrello carrelloSessione = ((Carrello) ss.getAttribute("carrello"));
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        Catalogo catalogoSessione = (Catalogo) ss.getAttribute("catalogo");
        int oldQuant = Integer.parseInt(request.getParameter("attr_OldQuant")); //catalogoDB.getCatalogo().size() - catalogoSessione.getCatalogo().size();
        Prodotto p = null;

        //Aggiorna il catalogo nella sessione
        for (Prodotto pcart : catalogoSessione.getCatalogo()) {
            if (pcart.getID() == id)
                pcart.setQuantita((pcart.getQuantita() + oldQuant) - quant);
            ss.setAttribute("catalogo", catalogoSessione);
        }

            //Aggiorna il carrello della sessione
        System.out.println("q="+quant);
        carrelloSessione.setPrezzo(0);
            for (Prodotto pcarr : carrelloSessione.getCarrello()) {
                if (pcarr.getID() == id){
                    pcarr.setQuantita(quant);
                    p = pcarr;
                }
                carrelloSessione.setPrezzo(carrelloSessione.getPrezzo()+(pcarr.getPrezzo()* pcarr.getQuantita()));
            }
            System.out.println("id:"+carrelloSessione.getCarrelloCod()+" prezzo:"+carrelloSessione.getPrezzo());
            ss.setAttribute("carrello", carrelloSessione);

            //Se il cliente è loggato aggiorna il carrello nel DB
            if (cliente != null) {
                try {
                    carrelloDAO.modCart(carrelloSessione, p);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
