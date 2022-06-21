package Controller;

import Model.CASE_.Case;
import Model.CATALOGO_.Catalogo;
import Model.CPU_.Cpu;
import Model.GPU_.Gpu;
import Model.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.out;

@WebServlet(name = "FilterName", value = "/FilterName")
public class FilterName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String marca = request.getParameter("input_cerca");
        System.out.println("MARCA: "+marca);

        Catalogo oldCatalogo = (Catalogo) request.getSession().getAttribute("catalogo");

        if(oldCatalogo == null) {
            System.out.println("Catalogo vecchio null");
        }

        assert oldCatalogo != null;
        Catalogo c = oldCatalogo.filterByMarca(marca);

        if(c == null){
            System.out.println("c is null");
        }

        assert c != null;
        for(Prodotto p : c.getCatalogo()) {
            switch (p.getTipo()) {
                case "CPU":
                    Cpu cpu = (Cpu) p;
                    response.getWriter().println(
                            "<a href=\"info-pezzo.jsp?Id=" + cpu.getID()
                                    + "\"><div class = \"cpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            cpu.getMarca() + "</li>" +
                            "<li>Modello: " + cpu.getModello() + "</li>" +
                            "<li>Prezzo: " + cpu.getPrezzo() + "</li>" +
                            "<li>Numero di core:" + cpu.getN_Core() + "</li>" +
                            "<li>Descrizione: " + cpu.getDescrizione() + "</li>" +
                            "<li>Url: " + cpu.getUrl() + "</li>" +
                            "<li>Disponibilità: " + cpu.getQuantità() + "</li></ul></div></a>");
                    break;
                case "Gpu":
                    Gpu gpu = (Gpu) p;
                    response.getWriter().println("<a href=\"info-pezzo.jsp?Id=" + gpu.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            gpu.getMarca() + "</li>" +
                            "<li>Modello: " + gpu.getModello() + "</li>" +
                            "<li>Prezzo: " + gpu.getPrezzo() + "</li>" +
                            "<li>W_Cpu: " + gpu.getWattaggio() + "</li>" +
                            "<li>Frequenza: " + gpu.getFrequenza() + "</li>" +
                            "<li>vRam:" + gpu.getVRam() + "</li>" +
                            "<li>Descrizione: " + gpu.getDescrizione() + "</li>" +
                            "<li>Url: " + gpu.getUrl() + "</li>" +
                            "<li>Disponibilità: " + gpu.getQuantità() + "</li></ul></div></a>");
                    break;
                default:
                    response.getWriter().println("Errore");
            }
        }




    }
}
