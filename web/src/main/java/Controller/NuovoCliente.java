package Controller;

import Model.Carrello_.CarrelloDAO;
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
        String nikName = request.getParameter("nikname");
        String plainTxtPassword = request.getParameter("password");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String via = request.getParameter("via");
        String provincia = request.getParameter("provincia");
        String citta = request.getParameter("citta");
        int cap = Integer.parseInt(request.getParameter("cap"));


        //Hash della password
        String password = PasswordEncrypter.encryptThisString(plainTxtPassword);

        //Se uno dei dati non è stato inserito o è andato perso nella request
        if(nikName.equals("") || password.equals("") || email.equals("") || telefono.equals("") || via.equals("") || provincia.equals("") || citta.equals("") || cap<0
            || nikName == null || password == null || email == null || telefono == null || via == null || provincia == null || citta == null
        ){
            request.setAttribute("register.error", "Compilare tutti i campi");
            request.getRequestDispatcher("/WEB-INF/jsp/CreazioneUtente.jsp").forward(request, response);
        }
        else{
            ClienteDAO clienteDAO = new ClienteDAO();
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            Cliente c = new Cliente(email, password, nikName, telefono, via, provincia, citta, cap);
            try {
                //Aggiungi cliente ad DB
                clienteDAO.addCliente(c);
                System.out.println("Cliente aggiunto");
                //Creo un nuovo carrello nel DB
                int codCarrello = carrelloDAO.createCarrello();
                //Associo il nuovo cliente al carrello
                System.out.println("DEBUG: Codice nuovo carrello creato: "+codCarrello);
                carrelloDAO.createNewOrdine(c, codCarrello);
                //Reindirizzo al login
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
