<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.ProdottoDAO" %>
<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.GPU_.Gpu" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="Model.MOBO_.Mobo" %>
<%@ page import="Model.PSU_.Psu" %>
<%@ page import="Model.RAM_.Ram" %>
<%@ page import="Model.Archiviazione_.HDD_.Hdd" %>
<%@ page import="Model.Archiviazione_.SDD_.Ssd" %>
<%@ page import="Model.CATALOGO_.CatalogoDAO" %>
<%@ page import="Model.CATALOGO_.Catalogo" %>
<%@ page import="Model.Carrello_.Carrello" %><%--
  Created by IntelliJ IDEA.
  User: mattiacavaliere
  Date: 25/05/22
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalogo</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#cerca').click(function () {
                $.ajax({
                    url: 'FilterName',
                    type: 'POST',
                    data: 'input_cerca='+$('#input_cerca').val(),
                    success: function (response) {
                        $('#divCatalogo').html(response);
                    }
                });
            });
        });
    </script>
</head>
<body>
<h2>Filtra per nome: </h2>
<input type="text" id="input_cerca" placeholder="Nome da cercare"></input>
<button id="cerca">Cerca</button>
<br><br>

    <%
        HttpSession ss = request.getSession();
        Catalogo catalogo = null;
        if(ss.getAttribute("catalogo") == null){
            System.out.println("Catalogo non presente in sessione");
            catalogo = new Catalogo();

        }
        else{
            System.out.println("Catalogo presente in sessione");
            catalogo = (Catalogo) ss.getAttribute("catalogo");
        }

        List<Cpu> cpuList = (List<Cpu>) catalogo.doRetriveByType("CPU");
        List<Case> caseList = (List<Case>) catalogo.doRetriveByType("CASE");
        List<Dissipatore> dissipatoreList = (List<Dissipatore>) catalogo.doRetriveByType("DISSIPATORE");
        List<Gpu> gpuList = (List<Gpu>) catalogo.doRetriveByType("GPU");
        List<Mobo> moboList = (List<Mobo>) catalogo.doRetriveByType("MOBO");
        List<Psu> psuList = (List<Psu>) catalogo.doRetriveByType("PSU");
        List<Ram> ramList = (List<Ram>) catalogo.doRetriveByType("RAM");
        List<Hdd> hddList = (List<Hdd>) catalogo.doRetriveByType("HDD");
        List<Ssd> ssdList = (List<Ssd>) catalogo.doRetriveByType("SSD");
    %>
    <div id="divCatalogo" class="wrapper">
        <div>
            <%
                for (Cpu value : cpuList) {
                    Cpu cpu = new Cpu();
                    if (value instanceof Cpu) {
                        cpu = (Cpu) value;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + value.getID() + "\"><div class = \"cpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            cpu.getMarca() + "</li>" +
                            "<li>Modello: " + cpu.getModello() + "</li>" +
                            "<li>Prezzo: " + cpu.getPrezzo() + "</li>" +
                            "<li>Numero di core:" + cpu.getN_Core() + "</li>" +
                            "<li>Descrizione: " + cpu.getDescrizione() + "</li>" +
                            "<li>Url: " + cpu.getUrl() + "</li>" +
                            "<li>Disponibilità: " + cpu.getQuantità() + "</li></ul></div></a>");

                }
            %>
        </div>
        <div>
            <%
                for (Case aCase : caseList) {
                    Case case_ = new Case();
                    if (aCase instanceof Case) {
                        case_ = (Case) aCase;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + aCase.getID() + "\"><div class = \"case-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            case_.getMarca() + "</li>" +
                            "<li>Modello: " + case_.getModello() + "</li>" +
                            "<li>Prezzo: " + case_.getPrezzo() + "</li>" +
                            "<li>Forma mobo:" + case_.getFormaMobo() + "</li>" +
                            "<li>Descrizione: " + case_.getDescrizione() + "</li>" +
                            "<li>Url: " + case_.getUrl() + "</li>" +
                            "<li>Disponibilità: " + case_.getQuantità() + "</li></ul></div></a>");
                }
            %>
        </div>
        <div>
            <%
                for (Dissipatore item : dissipatoreList) {
                    Dissipatore dissipatore = new Dissipatore();
                    if (item instanceof Dissipatore) {
                        dissipatore = (Dissipatore) item;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + item.getID() + "\"><div class = \"dissipatore-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            dissipatore.getMarca() + "</li>" +
                            "<li>Modello: " + dissipatore.getModello() + "</li>" +
                            "<li>Prezzo: " + dissipatore.getPrezzo() + "</li>" +
                            "<li>W_Cpu:" + dissipatore.getW_Cpu() + "</li>" +
                            "<li>Descrizione: " + dissipatore.getDescrizione() + "</li>" +
                            "<li>Url: " + dissipatore.getUrl() + "</li>" +
                            "<li>Disponibilità: " + dissipatore.getQuantità() + "</li></ul></div></a>");
                }
            %>
        </div>
        <div>
            <%
                for (Gpu item : gpuList) {
                    Gpu gpu = new Gpu();
                    if (item instanceof Gpu) {
                        gpu = (Gpu) item;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + item.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
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
            %>
        </div>
        </div>

        <div>
            <%
                for (Mobo value : moboList) {
                    Mobo mobo = new Mobo();
                    if (value instanceof Mobo) {
                        mobo = (Mobo) value;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + value.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
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
            %>
        </div>
        <div>
            <%
                for (Psu value : psuList) {
                    Psu psu = new Psu();
                    if (value instanceof Psu) {
                        psu = (Psu) value;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + value.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            psu.getMarca() + "</li>" +
                            "<li>Modello: " + psu.getModello() + "</li>" +
                            "<li>Prezzo: " + psu.getPrezzo() + "</li>" +
                            "<li>Watt: " + psu.getN_Watt() + "</li>" +
                            "<li>Descrizione: " + psu.getDescrizione() + "</li>" +
                            "<li>Url: " + psu.getUrl() + "</li>" +
                            "<li>Disponibilità: " + psu.getQuantità() + "</li></ul></div></a>");
                }
            %>
        </div>
        <div>
            <%
                for (Ram value : ramList) {
                    Ram ram = new Ram();
                    if (value instanceof Ram) {
                        ram = (Ram) value;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + value.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            ram.getMarca() + "</li>" +
                            "<li>Modello: " + ram.getModello() + "</li>" +
                            "<li>Prezzo: " + ram.getPrezzo() + "</li>" +
                            "<li>Frequenza: " + ram.getFrequenza() + "</li>" +
                            "<li>Descrizione: " + ram.getDescrizione() + "</li>" +
                            "<li>Url: " + ram.getUrl() + "</li>" +
                            "<li>Disponibilità: " + ram.getQuantità() + "</li></ul></div></a>");
                }
            %>
        </div>
        <div class="hdd-list">
            <%
            for (Hdd value : hddList) {
                Hdd hdd = new Hdd();
                if (value instanceof Hdd) {
                    hdd = (Hdd) value;
                }
                out.println("<a href=\"info-pezzo.jsp?Id=" + value.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                        hdd.getMarca() + "</li>" +
                        "<li>Modello: " + hdd.getModello() + "</li>" +
                        "<li>Prezzo: " + hdd.getPrezzo() + "</li>" +
                        "<li>MB/s: " + hdd.getMBs() + "</li>" +
                        "<li>Descrizione: " + hdd.getDescrizione() + "</li>" +
                        "<li>Url: " + hdd.getUrl() + "</li>" +
                        "<li>Disponibilità: " + hdd.getQuantità() + "</li></ul></div></a>");
            }
        %>
        </div>
        <div class="ssd-list">
            <%
                for (Ssd value : ssdList) {
                    Ssd ssd = new Ssd();
                    if (value instanceof Ssd) {
                        ssd = (Ssd) value;
                    }
                    out.println("<a href=\"info-pezzo.jsp?Id=" + value.getID() + "\"><div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            ssd.getMarca() + "</li>" +
                            "<li>Modello: " + ssd.getModello() + "</li>" +
                            "<li>Prezzo: " + ssd.getPrezzo() + "</li>" +
                            "<li>MB/s: " + ssd.getMBs() + "</li>" +
                            "<li>Descrizione: " + ssd.getDescrizione() + "</li>" +
                            "<li>Url: " + ssd.getUrl() + "</li>" +
                            "<li>Disponibilità: " + ssd.getQuantità() + "</li></ul></div></a>");
                }
            %>
        </div>
</body>
</html>
