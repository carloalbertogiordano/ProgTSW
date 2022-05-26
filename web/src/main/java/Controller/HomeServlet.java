package Controller;

import Model.Carrello_.CarrelloDAO;
import Model.Catalogo;
import Model.Cliente_.Cliente;
import Model.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        Cliente c = (Cliente) ss.getAttribute("cliente");
        CatalogoDAO service = new CatalogoDAO();
        if(ss.isNew()){
            Catalogo catalogo = new Catalogo();
            try {
                catalogo = service.doRetriveAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ss.setAttribute("catalogo", catalogo);
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
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
