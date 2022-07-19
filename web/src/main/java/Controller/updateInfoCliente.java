package Controller;

import Model.Cliente_.Cliente;
import Model.Cliente_.ClienteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

//Aggiorna le info personali del cliente (Tranne la mail)
@WebServlet(name = "updateInfoCliente", value = "/updateInfoCliente")
public class updateInfoCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nick = request.getParameter("input_nick");
        String tel = request.getParameter("input_tel");
        HttpSession ss = request.getSession();
        ClienteDAO cDAO = new ClienteDAO();
        Cliente cliente = (Cliente) ss.getAttribute("cliente");
        boolean success;

        //Aggiorno le info nel DB
        try {
            success = cDAO.updateInfoPersonaliCliente(cliente, nick, tel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Setto le info aggiornate nella sessione
        cliente.setNickname(nick);
        cliente.setTel(tel);

        //Se l'aggiornamento è andato a buon fine setto l'utente anche nella sessione
        if(success){
            ss.setAttribute("queryUpdateInfoCliente", true);
            ss.setAttribute("cliente", cliente);
        }
        else ss.setAttribute("queryUpdateInfoCliente",false);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
