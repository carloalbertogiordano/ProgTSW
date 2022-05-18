package Controller;

import Model.Cliente;
import Model.ClienteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "NuovoCliente", value = "/NuovoCliente")
public class NuovoCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nikName = request.getParameter("NikName");
        String password = request.getParameter("Password");
        String email = request.getParameter("Email");
        String telefono = request.getParameter("Telefono");
        String cap = request.getParameter("CAP");
        String provincia = request.getParameter("Provincia");

        if(nikName.equals("") || password.equals("") || email.equals("") || telefono.equals("") || cap.equals("") || provincia.equals("")){
            request.setAttribute("errore", "Compilare tutti i campi");
            request.getRequestDispatcher("/WEB-INF/jsp/NuovoCliente.jsp").forward(request, response);
        }
        else{
            ClienteDAO CDAO = new ClienteDAO();
            Cliente c = new Cliente(nikName, password, email, telefono, cap, provincia, false);
            try {
                CDAO.addCliente(c);
                System.out.println("Cliente aggiunto");
                request.getRequestDispatcher("/WEB-INF/jsp/NuovoCliente.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
