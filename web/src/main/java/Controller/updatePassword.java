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
        Cliente cliente = ((Cliente)request.getSession().getAttribute("cliente"));
        ClienteDAO cDAO = new ClienteDAO();
        HttpSession ss = request.getSession();
        boolean success;

        //Aggiorno la password nel db controllando che abbia avuto successo
        try {
            success = cDAO.updatePassword(cliente, newPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(success){
            ss.setAttribute("queryUpdatePassword", true);
        }
        else ss.setAttribute("queryUpdatePassword",false);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
