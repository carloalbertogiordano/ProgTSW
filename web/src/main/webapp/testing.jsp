<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.*" %>
<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CPU_.CpuDAO" %>
<%@ page import="Model.Archiviazione_.HDD_.Hdd" %>
<%@ page import="Model.Archiviazione_.HDD_.HddDAO" %>
<%@ page import="Model.MOBO_.Mobo" %>
<%@ page import="Model.MOBO_.MoboDAO" %>
<%@ page import="Model.RAM_.Ram" %>
<%@ page import="Model.RAM_.RamDAO" %>
<%@ page import="Model.Archiviazione_.SDD_.SsdDAO" %>
<%@ page import="Model.Archiviazione_.SDD_.Ssd" %>
<%@ page import="Model.GPU_.Gpu" %>
<%@ page import="Model.GPU_.GpuDAO" %>
<%@ page import="Model.PSU_.PsuDAO" %>
<%@ page import="Model.PSU_.Psu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.CASE_.CaseDAO" %>
<%@ page import="Model.DISSIPATORE_.DissipatoreDAO" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 5/20/22
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>TESTING:</h1>

<%
    CpuDAO cDAO = new CpuDAO();
    try {
        ArrayList<Cpu> cpus = cDAO.doRetriveByType();
        for (Cpu cpu : cpus) {
            out.println("<p>CPU: " + cpu.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    MoboDAO mDAO = new MoboDAO();
    try {
        ArrayList<Mobo> mobos = mDAO.doRetriveByType();
        for (Mobo mobo : mobos) {
            out.println("<p>Mobo: " + mobo.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    RamDAO rDAO = new RamDAO();
    try {
        ArrayList<Ram> rams = rDAO.doRetriveByType();
        for (Ram ram : rams) {
            out.println("<p>Ram: " + ram.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    HddDAO hDAO = new HddDAO();
    try {
        ArrayList<Hdd> hdds = hDAO.doRetriveByType();
        for (Hdd hdd : hdds) {
            out.println("<p>Hdd: " + hdd.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    SsdDAO sDAO = new SsdDAO();
    try {
        ArrayList<Ssd> ssds = sDAO.doRetriveByType();
        for (Ssd ssd : ssds) {
            out.println("<p>Ssd: " + ssd.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    GpuDAO gDAO = new GpuDAO();
    try {
        ArrayList<Gpu> gpus = gDAO.doRetriveByType();
        for (Gpu gpu : gpus) {
            out.println("<p>Gpu: " + gpu.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    PsuDAO pDAO = new PsuDAO();
    try {
        ArrayList<Psu> psus = pDAO.doRetriveByType();
        for (Psu psu : psus) {
            out.println("<p>Psu: " + psu.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    CaseDAO caseDAO = new CaseDAO();
    try {
        ArrayList<Case> cases = caseDAO.doRetriveByType();
        for (Case case_ : cases) {
            out.println("<p>Case: " + case_.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    DissipatoreDAO dDAO = new DissipatoreDAO();
    try {
        ArrayList<Dissipatore> ds = dDAO.doRetriveByType();
        for (Dissipatore d : ds) {
            out.println("<p>Dissipatore: " + d.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


%>

</body>
</html>
