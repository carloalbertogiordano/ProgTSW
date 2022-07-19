package Controller;

import Model.CATALOGO_.Catalogo;
import Model.CATALOGO_.CatalogoDAO;
import Model.Carrello_.Carrello;
import Model.Carrello_.CarrelloDAO;
import Model.Cliente_.Cliente;
import Model.Prodotto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

//Cambia la quantità di un prodotto
@WebServlet(name = "changeQuantity", value = "/changeQuantity")
public class changeQuantity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        Catalogo catalogo = (Catalogo) request.getSession().getAttribute("catalogo");

        //Cambia la quantità nel carrello e la aggiorna nel catalogo
        for(Prodotto p : carrello.getCarrello()){
            if(idProdotto == p.getID()){
                p.setQuantità(newQuantity);
                catalogo.aggiornaQuantita(p);
            }
        }
        //In caso l'utente sia loggato dobbiamo anche modificare il suo carrello nel DB
        if(cliente != null){
            CarrelloDAO serviceCarrello = new CarrelloDAO();
            try {
                serviceCarrello.delCarrelloFromComporre(carrello);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            CatalogoDAO serviceCatalogo = new CatalogoDAO();

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
