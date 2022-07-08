<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
</head>
<body>
<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");

    if(!carrello.isEmpty()) {
        List<Prodotto> carrelloList = carrello.getCarrello();
        out.println("<li>");
        for (Prodotto prodotto : carrelloList) {
            out.println("<ul>" + prodotto.toString() +
                    "<form action=\"removeCart\" method=\"GET\">" +
                    "<input type=\"hidden\" name=\"idProdotto\" id=\"idProdotto\" value=\"" + prodotto.getID() + "\">" +
                    "<input type=\"submit\" value=\"Rimuovi\" id=\"submit\"></form>" +
                    "</ul>");
        }
        out.println("</li>" +
                "<br>" +
                "<a href=\"redirectToIndirizzoSpedizione\">Procedi all'ordine</a>");
    }
    else{
        out.println("<h1>Il carrello è vuoto</h1>");
    }
%>
</body>
</html>
