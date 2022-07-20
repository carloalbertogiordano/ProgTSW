<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info personali</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
    <script src="js/common.js"></script>
    <script src="js/modInfoCliente.js"></script>
</head>
<body>
    <%

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        out.println("<h2>Info personali</h2>");
        out.println("<table>"+
            "<tr>"+
            "<td> NickName: </td>"+
            "<td><input type=\"text\" name=\"nick\" id=\"nick\" class=\"infoPers\" value=\""+ cliente.getNickname() +"\" > </td>"+
            "</tr>"+
            "<tr>"+
            "<td> Telefono: </td>"+
            "<td><input type=\"text\" name=\"tel\" id=\"tel\" class=\"infoPers\" value=\""+ cliente.getTel() +"\" > </td>"+
            "</tr>"+
            "<input type=\"submit\" value=\"Modifica info. personali\" id=\"submitModInfoCliente\">" +
            "</table>");
        out.println("<br>");

        out.println("<h2>Password</h2>");
        out.println("<table>"+
                "<tr>" +
                "<td><input type=\"password\" value=\"**********\" id=\"password\" class=\"pass\" /></td>" +
                "</tr>" +
                "<tr>" +
                "<td><input type=\"submit\" value=\"Modifica password\" id=\"submitModPass\"/></td>" +
                "</tr>" +
                "</table>"+
                "</form>");

        out.println("<br><h2>Info spedizione</h2>");
        out.println("<table>" +
                "<div id=\"modIndirizzo\">" +
                "<tr>"+
                "<td> Via: </td>"+
                "<td><input type=\"text\" name=\"via\" id=\"via\" class=\"infoSped\" value=\""+ cliente.getVia() +"\" > </td>" +
                "</tr>"+
                "<tr>"+
                "<td> Provincia: </td>"+
                "<td><input type=\"text\" name=\"provincia\" id=\"provincia\" class=\"infoSped\" value=\""+ cliente.getProvincia() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> Città: </td>"+
                "<td><input type=\"text\" name=\"citta\" id=\"citta\" class=\"infoSped\" value=\""+ cliente.getCitta() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> CAP: </td>"+
                "<td><input type=\"text\" name=\"cap\" id=\"cap\" class=\"infoSped\" value=\""+ cliente.getCap() +"\" > </td>"+
                "</tr>"+
                "<input type=\"submit\" value=\"Modifica info. spedizione\" id=\"submitModInfoSped\">" +
                "</table>"+
                "</div>");


    %>


</body>
</html>
