package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
import Model.PasswordEncrypter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

//Aggiorna la password dell'utente
@WebServlet(name = "updatePassword", value = "/updatePassword")
public class updatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("input_pass");
        String newPassword = PasswordEncrypter.encryptThisString(password);
        HttpSession ss = request.getSession();
        Cliente cliente = ((Cliente)ss.getAttribute("cliente"));

        if(password==null || newPassword==null || ss==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);

        assert cliente != null;
        cliente.setPassword(newPassword);

        //Aggiorno la password nel DB
        try {
            ClienteDAO.updatePassword(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
