package Controller;

import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Aggiorna", value = "/Aggiorna")
public class Aggiorna extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*int id = Integer.parseInt(request.getParameter("ID"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        try {
            //Aggiorna lo stock del prodotto
            ProdottoDAO.aggiorna(id, quantita);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("admin.jsp");*/
        response.getWriter().println("TODO");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
