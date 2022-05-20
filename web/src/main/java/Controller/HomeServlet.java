package Controller;

import Model.CarrelloDAO;
import Model.Cliente;
import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        Cliente c = (Cliente) ss.getAttribute("cliente");
        if(c!=null){
            if(ss.getAttribute("carrello") == null){
                CarrelloDAO carrelloDAO = new CarrelloDAO();
                try {
                    ss.setAttribute("carrello", carrelloDAO.doRetriveByMailCliente(c.getMail()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{

            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
