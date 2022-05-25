<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.ProdottoDAO" %>
<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.GPU_.Gpu" %><%--
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
</head>
<body>
    <%
        List<Prodotto> cpuList = new ArrayList<Prodotto>();
        List<Prodotto> caseList = new ArrayList<Prodotto>();
        List<Prodotto> dissipatoreList = new ArrayList<Prodotto>();
        List<Prodotto> gpuList = new ArrayList<Prodotto>();
        List<Prodotto> moboList = new ArrayList<Prodotto>();
        List<Prodotto> psuList = new ArrayList<Prodotto>();
        List<Prodotto> ramList = new ArrayList<Prodotto>();
        List<Prodotto> hddList = new ArrayList<Prodotto>();
        List<Prodotto> ssdList = new ArrayList<Prodotto>();

        cpuList = ProdottoDAO.doRetriveByType("CPU");
        caseList = ProdottoDAO.doRetriveByType("CASE");
        dissipatoreList = ProdottoDAO.doRetriveByType("DISSIPATORE");
        gpuList = ProdottoDAO.doRetriveByType("CPU");
        moboList = ProdottoDAO.doRetriveByType("MOBO");
        psuList = ProdottoDAO.doRetriveByType("PSU");
        ramList = ProdottoDAO.doRetriveByType("RAM");
        hddList = ProdottoDAO.doRetriveByType("HDD");
        ssdList = ProdottoDAO.doRetriveByType("SSD");
    %>
    <div class="wrapper">
        <div class="CPU-list">
            <%
                for(int i = 0; i < cpuList.size(); i++){
                    Cpu cpu = new Cpu();
                    if(cpuList.get(i) instanceof Cpu){
                        cpu = (Cpu) cpuList.get(i);
                    }
                    out.println("<div class = \"cpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            cpu.getMarca() + "</li>" +
                            "<li>Modello: " + cpu.getModello() + "</li>" +
                            "<li>Prezzo: " + cpu.getPrezzo() + "</li>" +
                            "<li>Numero di core:" + cpu.getN_Core() + "</li>" +
                            "<li>Descrizione: " + cpu.getDescrizione() + "</li>" +
                            "<li>Url: " + cpu.getUrl() + "</li></ul></div>");
                }
            %>
        </div>
        <div class="case-list">
            <%
                for(int i = 0; i < caseList.size(); i++){
                    Case case_ = new Case();
                    if(caseList.get(i) instanceof Case){
                        case_ = (Case) caseList.get(i);
                    }
                    out.println("<div class = \"case-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            case_.getMarca() + "</li>" +
                            "<li>Modello: " + case_.getModello() + "</li>" +
                            "<li>Prezzo: " + case_.getPrezzo() + "</li>" +
                            "<li>Forma mobo:" + case_.getFormaMobo() + "</li>" +
                            "<li>Descrizione: " + case_.getDescrizione() + "</li>" +
                            "<li>Url: " + case_.getUrl() + "</li></ul></div>");
                }
            %>
        </div>
        <div class="dissipatore-list">
            <%
                for(int i = 0; i < dissipatoreList.size(); i++){
                    Dissipatore dissipatore = new Dissipatore();
                    if(dissipatoreList.get(i) instanceof Dissipatore){
                        dissipatore = (Dissipatore) dissipatoreList.get(i);
                    }
                    out.println("<div class = \"dissipatore-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            dissipatore.getMarca() + "</li>" +
                            "<li>Modello: " + dissipatore.getModello() + "</li>" +
                            "<li>Prezzo: " + dissipatore.getPrezzo() + "</li>" +
                            "<li>W_Cpu:" + dissipatore.getW_Cpu() + "</li>" +
                            "<li>Descrizione: " + dissipatore.getDescrizione() + "</li>" +
                            "<li>Url: " + dissipatore.getUrl() + "</li></ul></div>");
                }
            %>
        </div>
        <div class="gpu-list">
            <%
                for(int i = 0; i < gpuList.size(); i++){
                    Gpu gpu = new Gpu();
                    if(gpuList.get(i) instanceof Gpu){
                        gpu = (Gpu) gpuList.get(i);
                    }
                    out.println("<div class = \"gpu-product\" style=\"borer: 1px solid red\"><ul><li>Marca: " +
                            gpu.getMarca() + "</li>" +
                            "<li>Modello: " + gpu.getModello() + "</li>" +
                            "<li>Prezzo: " + gpu.getPrezzo() + "</li>" +
                            "<li>W_Cpu: " + gpu.getWattaggio() + "</li>" +
                            "<li>Frequenza: " + gpu.getFrequenza() + "</li>" +
                            "<li>vRam:" + gpu.getVRam() + "</li>" +
                            "<li>Descrizione: " + gpu.getDescrizione() + "</li>" +
                            "<li>Url: " + gpu.getUrl() + "</li></ul></div>");
                }
            %>
        </div>
        <div class="mobo-list">

        </div>
        <div class="psu-list">

        </div>
        <div class="ram-list">

        </div>
        <div class="hdd-list">

        </div>
        <div class="ssd-list">

        </div>
    </div>
</body>
</html>
