package Controller;

import Model.CASE_.Case;
import Model.CPU_.Cpu;
import Model.Carrello_.Carrello;
import Model.CATALOGO_.Catalogo;
import Model.Cliente_.Cliente;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addCart", value = "/addCart")
public class addCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //take product id
        int id = Integer.parseInt(request.getParameter("Id"));
        //take quantity
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //take the session and session attribute carrello and catalogo
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (catalogo.doRetriveById(id) instanceof Cpu) {
            //take the product from catalogo by his id
            Cpu cpu = (Cpu) catalogo.doRetriveById(id);
            //take the product from carrello by his id (we need it to update quantity in catalogo)
            Cpu cpu_carrello = new Cpu(cpu.getID(), cpu.getMarca(), cpu.getModello(), cpu.getPrezzo(), quantity, cpu.getWattaggio(), cpu.getFrequenza(), cpu.getN_Core(), cpu.getUrl(), cpu.getDescrizione());
            if (cliente != null) {
                try {
                    System.out.println(carrello.getCarrelloCod());
                    carrello.addProduct(cpu_carrello);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                catalogo.aggiornaQuantita(cpu_carrello);
            } else {
                carrello.addSessionProduct(cpu_carrello);
                catalogo.aggiornaQuantita(cpu_carrello);
            }
        }
        //We can repeat the process for all type of product
        else if (catalogo.doRetriveById(id) instanceof Case) {
            Case case_ = (Case) catalogo.doRetriveById(id);
            Case case_carrello = new Case(case_.getID(), case_.getMarca(), case_.getModello(), case_.getPrezzo(), quantity, case_.getFormaMobo(), case_.getUrl(), case_.getDescrizione());
            if (cliente != null) {
                try {
                    carrello.addProduct(case_carrello);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                catalogo.aggiornaQuantita(case_carrello);
            } else {
                carrello.addSessionProduct(case_carrello);
                catalogo.aggiornaQuantita(case_carrello);
            }
        } else if (catalogo.doRetriveById(id) instanceof Gpu) {
            Gpu gpu = (Gpu) catalogo.doRetriveById(id);
            Gpu gpu_carrello = new Gpu(gpu.getID(), gpu.getMarca(), gpu.getModello(), gpu.getPrezzo(), quantity, gpu.getWattaggio(), gpu.getFrequenza(), gpu.getVRam(), gpu.getUrl(), gpu.getDescrizione());
            if (cliente != null) {
                try {
                    carrello.addProduct(gpu_carrello);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                catalogo.aggiornaQuantita(gpu_carrello);
            } else {
                carrello.addSessionProduct(gpu_carrello);
                catalogo.aggiornaQuantita(gpu_carrello);
            }
        } else if (catalogo.doRetriveById(id) instanceof Dissipatore) {
            Dissipatore dissipatore = (Dissipatore) catalogo.doRetriveById(id);
            Dissipatore dissipatore_carrello = new Dissipatore(dissipatore.getID(), dissipatore.getMarca(), dissipatore.getModello(), dissipatore.getPrezzo(), quantity, dissipatore.getW_Cpu(), dissipatore.getUrl(), dissipatore.getDescrizione());
            if (cliente != null) {
                try {
                    carrello.addProduct(dissipatore_carrello);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                catalogo.aggiornaQuantita(dissipatore_carrello);
            } else {
                carrello.addSessionProduct(dissipatore_carrello);
                catalogo.aggiornaQuantita(dissipatore_carrello);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}


