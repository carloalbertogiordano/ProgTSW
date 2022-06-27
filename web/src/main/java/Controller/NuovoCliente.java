package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
import Model.PasswordEncrypter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "NuovoCliente", value = "/NuovoCliente")
public class NuovoCliente extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nikName = request.getParameter("NikName");
        String plainTxtPassword = request.getParameter("Password");
        String email = request.getParameter("Email");
        String telefono = request.getParameter("Telefono");
        String cap = request.getParameter("CAP");
        String provincia = request.getParameter("Provincia");

        //Hash della password
        String password = PasswordEncrypter.encryptThisString(plainTxtPassword);

        //Se uno dei dati non è stato inserito o è andato perso nella request
        if(nikName.equals("") || password.equals("") || email.equals("") || telefono.equals("") || cap.equals("") || provincia.equals("")
            || nikName == null || password == null || email == null || telefono == null || cap == null || provincia == null
        ){
            request.setAttribute("register.error", "Compilare tutti i campi");
            request.getRequestDispatcher("/WEB-INF/jsp/CreazioneUtente.jsp").forward(request, response);
        }
        else{
            ClienteDAO CDAO = new ClienteDAO();
            Cliente c = new Cliente(email, password, nikName, telefono, cap, provincia, false);
            try {
                //Aggiungi cliente ad DB
                CDAO.addCliente(c);
                System.out.println("Cliente aggiunto");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
