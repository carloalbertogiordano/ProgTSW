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
    Cliente cliente = (Cliente) session.getAttribute("cliente");

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
        out.println("<br>");
        out.println("<div id=\"modIndirizzo\">" +
                "<table>"+
                "<form action=\"expireCart\" method=\"\">" +
                "<input type=\"hidden\" name=\"idCarrello\" id=\"idCarrello\" value=\"" + carrello.getCarrelloCod() + "\">" +
                "<tr>"+
                "<td> Via: </td>"+
                "<td><input type=\"text\" name=\"via\" id=\"via\" value=\""+ cliente.getVia() +"\" > </td>" +
                "</tr>"+
                "<tr>"+
                "<td> Provincia: </td>"+
                "<td><input type=\"text\" name=\"provincia\" id=\"provincia\" value=\""+ cliente.getProvincia() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> Città: </td>"+
                "<td><input type=\"text\" name=\"citta\" id=\"citta\" value=\""+ cliente.getCitta() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> CAP: </td>"+
                "<td><input type=\"text\" name=\"cap\" id=\"cap\" value=\""+ cliente.getCap() +"\" > </td>"+
                "</tr>"+
                "<input type=\"submit\" value=\"Concludi ordine\" id=\"submit\">" +
                "</form>" +
                "</table>"+
                "</div>");
    }
    else{
        out.println("<h1>Il carrello è vuoto</h1>");
    }
%>
</body>
</html>
