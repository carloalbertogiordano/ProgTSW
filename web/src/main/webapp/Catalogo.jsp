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
<%@ page import="Controller.CatalogoDAO" %><%--
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
        List<Cpu> cpuList = new ArrayList<Cpu>();
        List<Case> caseList = new ArrayList<Case>();
        List<Dissipatore> dissipatoreList = new ArrayList<Dissipatore>();
        List<Gpu> gpuList = new ArrayList<Gpu>();
        List<Mobo> moboList = new ArrayList<Mobo>();
        List<Psu> psuList = new ArrayList<Psu>();
        List<Ram> ramList = new ArrayList<Ram>();
        List<Hdd> hddList = new ArrayList<Hdd>();
        List<Ssd> ssdList = new ArrayList<Ssd>();

        CatalogoDAO catalogoDAO = new CatalogoDAO();

        try {
            cpuList = catalogoDAO.doRetriveByType("CPU").stream().map(x -> (Cpu) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            caseList = catalogoDAO.doRetriveByType("CASE").stream().map(x -> (Case) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dissipatoreList = catalogoDAO.doRetriveByType("DISSIPATORE").stream().map(x -> (Dissipatore) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            gpuList = catalogoDAO.doRetriveByType("GPU").stream().map(x -> (Gpu) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            moboList = catalogoDAO.doRetriveByType("MOBO").stream().map(x -> (Mobo) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            psuList = catalogoDAO.doRetriveByType("PSU").stream().map(x -> (Psu) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            ramList = catalogoDAO.doRetriveByType("RAM").stream().map(x -> (Ram) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            hddList = catalogoDAO.doRetriveByType("HDD").stream().map(x -> (Hdd) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            ssdList = catalogoDAO.doRetriveByType("SSD").stream().map(x -> (Ssd) x).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    %>
    <div class="wrapper">
        <div class="CPU-list">
            <%
                for (Prodotto prodotto : cpuList) {
                    Cpu cpu = new Cpu();
                    if (prodotto instanceof Cpu) {
                        cpu = (Cpu) prodotto;
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
