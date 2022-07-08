package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
import Model.ConPool;
import Model.PasswordEncrypter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("Mail");
        String password = PasswordEncrypter.encryptThisString(request.getParameter("Password"));
        ClienteDAO CDAO = new ClienteDAO();

        //Questo try deve essere fatto prima dell'if successivo perchè la terza condizione può dare errore
        try {
            CDAO.doRetrieveByMail(mail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            //Se mail e password sono valide controlla che la coppia sia corretta con isCorrectLogin()
            if (mail == null || password == null || !CDAO.isCorrectLogin(mail, password)) {
                request.setAttribute("loginErr", "Cliente non trovato o password errata");
                System.out.println("Cliente non trovato o password errata");
                //Rimanda alla pagina di login
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            else{
                //Se la coppia è corretta, prendi il cliente dal DB e lo metti nella sessione
                System.out.println("Cliente trovato");
                //Prendi Le info dal DB e crea l' oggetto cliente da tenere in sessione
                //Cliente c = new Cliente(mail, CDAO.doRetriveNickByEmail(mail), CDAO.doRetriveTelByEmail(mail), CDAO.doRetriveViaByEmail(mail), CDAO.doRetriveProvinciaByEmail(mail), CDAO.doRetriveCapByEmail(mail), CDAO.isAdministrator(mail));
                Cliente c = CDAO.doRetrieveByMail(mail);
                HttpSession session = request.getSession();
                //Setta come attributo della sessione il cliente
                session.setAttribute("cliente", c);
                if(c.isAdministrator()) {
                    response.sendRedirect("admin.jsp");
                }
                else{
                    response.sendRedirect(".");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Prima di passare al doGet fai l'hash della password
        doGet(request, response);
    }
}
