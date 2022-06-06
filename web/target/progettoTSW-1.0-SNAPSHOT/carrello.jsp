<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Carrello_.Carrello" %><%--
  Created by IntelliJ IDEA.
  User: mattiacavaliere
  Date: 20/05/22
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrello</title>
</head>
<body>
<%
    Carrello carrello = new Carrello();
    carrello = (Carrello) session.getAttribute("carrello");
    if(!carrello.isEmpty()){
        List<Prodotto> carrelloList = carrello.getCarrello();
        out.println("<li>");
        for (Prodotto prodotto : carrelloList) {
            out.println("<ul>" + prodotto.toString() +
                    "<form action=\"removeCart\" method=\"GET\">" +
                    "<input type=\"hidden\" name=\"idProdotto\" id=\"idProdotto\" value=\"" + prodotto.getID() + "\">" +
                    "<input type=\"submit\" value=\"Rimuovi\" id=\"submit\"></form>" +
                    "</ul>");
        }
        out.println("</li>");
        out.println("<div>" +
                "<form action=\"expireCart\" method=\"\">" +
                "<input type=\"hidden\" name=\"idCarrello\" id=\"idCarrello\" value=\"" + carrello.getCarrelloCod() + "\">" +
                "<input type=\"submit\" value=\"Concludi ordine\" id=\"submit\">" +
                "</form>" +
                "</div>");
    }
    else{
        out.println("<h1>Il carrello è vuoto</h1>");
    }
%>
</body>
</html>
