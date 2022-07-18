<%@ page import="Model.Cliente_.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 7/15/22
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info personali</title>
</head>
<body>
    <%
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        out.println("<h2>Info personali</h2>");
        out.println("<table>"+
        "<form id=\"clienteInfo\" action=\"updateInfo\" method=\"POST\">" +
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
            "<input type=\"submit\" value=\"Modifica Info\" id=\"submit\">" +
            "</form>" +
            "</table>"+
            "</form>");
        out.println("<br>");
        out.println("<h2>Password</h2>");
        out.println("<input type=\"password\" value=\"**********\" id=\"pass\" form=\"clienteInfo\">");
        out.println("<br><h2>Info spedizione</h2>");
        //out.println("<>");


    %>


</body>
</html>
