<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.GPU_.Gpu" %>
<%@ page import="Model.CATALOGO_.Catalogo" %>
<%@ page import="Model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
</head>
<body>
<div>
    <%
        int id = Integer.parseInt(request.getParameter("Id"));
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
        Prodotto p = catalogo.doRetriveById(id);
        out.println("<div><ul>" +
                "<li>Marca: " + p.getMarca() + "</li>" +
                "<li>Modello: " + p.getModello() + "</li>" +
                "</ul>" +
                "<form action=\"addCart\" id=\"buy\">" +
                "<input type=\"hidden\" name=\"Id\" value=" + p.getID() + ">" +
                "<input type=\"number\" id=\"quantity\" name=\"quantity\" min=\"1\" max=\"" + p.getQuantità() + "\">" +
                "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                "</div>");
    %>
</div>
</body>
</html>
