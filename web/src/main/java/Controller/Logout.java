package Controller;

import Model.Carrello_.Carrello;
import Model.Catalogo;
import Model.CatalogoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        //Invalido la vecchia sessione
        ss.invalidate();
        //Richiamo la HomeServlet perchè c'è bisogno che il nuovo catalogo sia riempito,
        // altrimenti dopo il logout non sarà più visionabile perchè viene cancellato dalla sessione
        HomeServlet hs = new HomeServlet();
        hs.doGet(request, response);

        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
