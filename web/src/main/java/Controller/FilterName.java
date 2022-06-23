package Controller;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.CASE_.Case;
import Model.CATALOGO_.Catalogo;
import Model.CPU_.Cpu;
import Model.DISSIPATORE_.Dissipatore;
import Model.GPU_.Gpu;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.RAM_.Ram;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.out;

@WebServlet(name = "FilterName", value = "/FilterName")
public class FilterName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("input_cerca");
        String scelta = request.getParameter("radio_scelta");
        System.out.println("cerco per: "+text);
        System.out.println("scelta: "+scelta );

        Catalogo oldCatalogo = (Catalogo) request.getSession().getAttribute("catalogo");

        assert oldCatalogo != null;
        Catalogo c = oldCatalogo.filterByMarca(text);
        assert c != null;

        if(scelta.equals("Marca")){
            System.out.println(scelta + " = Marca");
            for(Prodotto p : c.getCatalogo()) {
                if(p.getMarca().equals(text))
                    doSwCase(p.getTipo(), p, response);
                }
            }

        else if(request.getParameter("radio_scelta").equals("Modello")){
            System.out.println(scelta + " = Modello");
            for(Prodotto p : c.getCatalogo()) {
                if(p.getModello().equals(text)) {
                    doSwCase(p.getTipo(), p, response);
                }
            }
        }

    }

    private void doSwCase(String tipo,Prodotto p,HttpServletResponse response) throws IOException {
        System.out.println("in switchCase per cercare tipo: "+tipo+"Per ID: "+p.getID() + "Nome:" +p.getMarca()+p.getModello());
        switch (tipo) {
            case "CPU":
                writeCpuForCatalog(response, p);
                break;
            case "CASE":
                writeCaseForCatalog(response, p);
                break;
            case "DISSIPATORE":
                writeDissipatoreForCatalog(response, p);
                break;
            case "PSU":
                writePsuForCatalog(response, p);
                break;
            case "MOBO":
                writeMoboForCatalog(response, p);
                break;
            case "RAM":
                writeRamForCatalog(response, p);
                break;
            case "HDD":
                writeHddForCatalog(response, p);
                break;
            case "SSD":
                writeSsdForCatalog(response, p);
                break;
            case "Gpu":
                writeGpuForCatalog(response, p);
                break;
            default:
                response.getWriter().println("Errore");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void writePsuForCatalog(HttpServletResponse response, Prodotto p) throws IOException {
        Psu psu = (Psu) p;
        response.getWriter().println(
                "<a href=\"info-pezzo.jsp?Id=" + psu.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                        psu.getMarca() + "</li>" +
                        "<li>Modello: " + psu.getModello() + "</li>" +
                        "<li>Prezzo: " + psu.getPrezzo() + "</li>" +
                        "<li>Watt: " + psu.getN_Watt() + "</li>" +
                        "<li>Descrizione: " + psu.getDescrizione() + "</li>" +
                        "<li>Url: " + psu.getUrl() + "</li>" +
                        "<li>Disponibilità: " + psu.getQuantità() + "</li></ul></div></a>"
        );
    }
    private void writeDissipatoreForCatalog(HttpServletResponse response, Prodotto p) throws IOException {
        Dissipatore dissipatore = (Dissipatore) p;
        response.getWriter().println(
                "<a href=\"info-pezzo.jsp?Id=" + dissipatore.getID() + "\"><div class = \"dissipatore-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                        dissipatore.getMarca() + "</li>" +
                        "<li>Modello: " + dissipatore.getModello() + "</li>" +
                        "<li>Prezzo: " + dissipatore.getPrezzo() + "</li>" +
                        "<li>W_Cpu:" + dissipatore.getW_Cpu() + "</li>" +
                        "<li>Descrizione: " + dissipatore.getDescrizione() + "</li>" +
                        "<li>Url: " + dissipatore.getUrl() + "</li>" +
                        "<li>Disponibilità: " + dissipatore.getQuantità() + "</li></ul></div></a>"
        );
    }
    private void writeCpuForCatalog(HttpServletResponse response, Prodotto p) throws IOException {
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
    }
    private void writeGpuForCatalog(HttpServletResponse response, Prodotto p) throws IOException{
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

    }
    private void writeMoboForCatalog(HttpServletResponse response, Prodotto p) throws IOException{
        Mobo mobo = (Mobo) p;
        response.getWriter().println(
                "<a href=\"info-pezzo.jsp?Id=" + mobo.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                        mobo.getMarca() + "</li>" +
                        "<li>Modello: " + mobo.getModello() + "</li>" +
                        "<li>Prezzo: " + mobo.getPrezzo() + "</li>" +
                        "<li>Forma: " + mobo.getForma() + "</li>" +
                        "<li>Banchi RAM: " + mobo.getN_RAM() + "</li>" +
                        "<li>Numero USB:" + mobo.getN_USB() + "</li>" +
                        "<li>Numero PCI:" + mobo.getN_PCI() + "</li>" +
                        "<li>Descrizione: " + mobo.getDescrizione() + "</li>" +
                        "<li>Url: " + mobo.getUrl() + "</li>" +
                        "<li>Disponibilità: " + mobo.getQuantità() + "</li></ul></div></a>");
    }
    private void writeHddForCatalog(HttpServletResponse response, Prodotto p) throws IOException{
        Hdd hdd = (Hdd) p;
        response.getWriter().println(
                "<a href=\"info-pezzo.jsp?Id=" + hdd.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                        hdd.getMarca() + "</li>" +
                        "<li>Modello: " + hdd.getModello() + "</li>" +
                        "<li>Prezzo: " + hdd.getPrezzo() + "</li>" +
                        "<li>MB/s: " + hdd.getMBs() + "</li>" +
                        "<li>Descrizione: " + hdd.getDescrizione() + "</li>" +
                        "<li>Url: " + hdd.getUrl() + "</li>" +
                        "<li>Disponibilità: " + hdd.getQuantità() + "</li></ul></div></a>"
        );
    }
    private void writeSsdForCatalog(HttpServletResponse response, Prodotto p) throws IOException{
        Ssd ssd = (Ssd) p;
        response.getWriter().println("<a href=\"info-pezzo.jsp?Id=" + ssd.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                ssd.getMarca() + "</li>" +
                "<li>Modello: " + ssd.getModello() + "</li>" +
                "<li>Prezzo: " + ssd.getPrezzo() + "</li>" +
                "<li>MB/s: " + ssd.getMBs() + "</li>" +
                "<li>Descrizione: " + ssd.getDescrizione() + "</li>" +
                "<li>Url: " + ssd.getUrl() + "</li>" +
                "<li>Disponibilità: " + ssd.getQuantità() + "</li></ul></div></a>");
    }
    private void writeRamForCatalog(HttpServletResponse response, Prodotto p) throws IOException{
        Ram ram = (Ram) p;
        response.getWriter().println(
                "<a href=\"info-pezzo.jsp?Id=" + ram.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                        ram.getMarca() + "</li>" +
                        "<li>Modello: " + ram.getModello() + "</li>" +
                        "<li>Prezzo: " + ram.getPrezzo() + "</li>" +
                        "<li>Frequenza: " + ram.getFrequenza() + "</li>" +
                        "<li>Descrizione: " + ram.getDescrizione() + "</li>" +
                        "<li>Url: " + ram.getUrl() + "</li>" +
                        "<li>Disponibilità: " + ram.getQuantità() + "</li></ul></div></a>"
        );
    }
    private void writeCaseForCatalog(HttpServletResponse response, Prodotto p) throws IOException{
        Case case_ = (Case) p;
        response.getWriter().println(
                "<a href=\"info-pezzo.jsp?Id=" + case_.getID() + "\"><div class = \"case-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                        case_.getMarca() + "</li>" +
                        "<li>Modello: " + case_.getModello() + "</li>" +
                        "<li>Prezzo: " + case_.getPrezzo() + "</li>" +
                        "<li>Forma mobo:" + case_.getFormaMobo() + "</li>" +
                        "<li>Descrizione: " + case_.getDescrizione() + "</li>" +
                        "<li>Url: " + case_.getUrl() + "</li>" +
                        "<li>Disponibilità: " + case_.getQuantità() + "</li></ul></div></a>"
        );
    }
}
