<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Carrello_.Carrello" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 7/8/22
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Indirizzo spedizione</title>
</head>
<body>
  <%
      Cliente cliente = (Cliente) session.getAttribute("cliente");
      Carrello carrello = (Carrello) session.getAttribute("carrello");
      out.println("<form action=\"expireCart\" method=\"GET\">" +
              "<div id=\"modIndirizzo\">" +
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
  %>
</body>
</html>
