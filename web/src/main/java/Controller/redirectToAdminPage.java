package Controller;

import Model.Cliente_.Cliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//Reindirizza alla jsp modificaPezzo (protetta)
@WebServlet(name = "redirectToAdminPage", value = "/redirectToAdminPage")
public class redirectToAdminPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!((Cliente)request.getSession().getAttribute("cliente")).isAdministrator())
            request.getRequestDispatcher(".").forward(request, response);
        request.getRequestDispatcher("./WEB-INF/modificaPezzo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
