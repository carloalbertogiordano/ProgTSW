package Controller;

import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Elimina", value = "/Elimina")
public class Elimina extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("ID"));
        try {
            ProdottoDAO.elimina(ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("admin.jsp");
    }
}
