package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
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

        //Questo try deve essere fatto prima dell'if successivo perchè la terza condizione può dare errore
        try {
            CDAO.doRetrieveByMail(mail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            //Se mail e password sono valide controlla che la coppia sia corretta con isCorrectLogin()
            if (mail == null || password == null || !CDAO.isCorrectLogin(mail, password)) {
                request.setAttribute("login.error", "Cliente non trovato o password errata");
                System.out.println("Cliente non trovato o password errata");
                //Rimanda alla pagina di login
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            else{
                //Se la coppia è corretta, prendi il cliente dal DB e lo metti nella sessione
                System.out.println("Cliente trovato");
                //Prendi nickname e mail dal DB e crea l' oggetto cliente da tenere in sessione
                Cliente c = new Cliente(mail, CDAO.doRetriveNickByEmail(mail));
                HttpSession session = request.getSession();
                //Setta come attributo della sessione il cliente
                session.setAttribute("cliente", c);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Prendi la password dal form
        String password = request.getParameter("Password");
        doGet(request, response, password);
    }
}
