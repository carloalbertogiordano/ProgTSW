package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
import Model.PasswordEncrypter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updatePassword", value = "/updatePassword")
public class updatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("input_pass");
        String newPassword = PasswordEncrypter.encryptThisString(password);
        String mail = ((Cliente)request.getSession().getAttribute("cliente")).getMail();
        ClienteDAO cDAO = new ClienteDAO();
        boolean success = false;

        try {
            success = cDAO.updatePassword(newPassword, mail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(success){
            request.setAttribute("queryUpdatePassword", true);
        }
        else request.setAttribute("queryUpdatePassword",false);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
