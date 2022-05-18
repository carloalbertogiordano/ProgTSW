package Controller;

import Model.ClienteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response, String password) throws ServletException, IOException {
        String mail = request.getParameter("Mail");
        ClienteDAO CDAO = new ClienteDAO();

        System.out.println("Mail: "+mail+"  Password: "+password);

        try {
            CDAO.doRetrieveByMail(mail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (mail == null || password == null || !CDAO.isCorrectLogin(mail, password)) {
                request.setAttribute("login.error", "Cliente non trovato o password errata");
                System.out.println("Cliente non trovato o password errata");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                //TODO: redirect to the right page
            }
            else{
                System.out.println("Cliente trovato");
                HttpSession session = request.getSession();
                session.setAttribute("mail", mail);
                //TODO: redirect to the right page
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("Password");
        doGet(request, response, password);
    }
}
