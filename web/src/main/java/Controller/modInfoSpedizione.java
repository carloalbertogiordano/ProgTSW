package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "modInfoSpedizione", value = "/modInfoSpedizione")
public class modInfoSpedizione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String via = request.getParameter("input_via");
        String provincia = request.getParameter("input_provincia");
        String citta = request.getParameter("input_citta");
        int cap = Integer.parseInt(request.getParameter("input_cap"));
        HttpSession ss = request.getSession();
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        ClienteDAO cDAO = new ClienteDAO();
        boolean success =false;

        cliente.setVia(via);
        cliente.setProvincia(provincia);
        cliente.setCitta(citta);
        cliente.setCap(cap);

        try {
            success = cDAO.updateInfoSpedizioneCliente(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(success){
            request.setAttribute("queryUpdateInfoCliente", true);
            ss.setAttribute("cliente", cliente);
        }
        else
            request.setAttribute("queryUpdateInfoCliente",false);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
