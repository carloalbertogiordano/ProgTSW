<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Indirizzo spedizione</title>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <script src="js/indirizzoSpedizione.js"></script>
    <script src="js/common.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
<div class="header">
    <div class="flex-container topnav" id ="topnav">
        <div class="flex-left-item logo">
            <a href="index.jsp"><img src="Images/PCBuilder-logo.png" id="header-logo"></a>
        </div>
        <a href="javascript:void(0);" class="right-buttons burger"  onclick="dropDownBurger()">&#9776;</a>
        <div class="nav flex-right-item" id="nav-list">
            <ul class="flex-container">
                <li><a href="index.jsp" class="active">Home</a></li>
                <li><a href="Catalogo.jsp">Catalogo</a></li>
                <li><a href="#">Chi siamo</a></li>
                <li class="empty-flex-field" id="emptyFlexField"></li>
                <li class="right-buttons"><a href="carrello.jsp" class="carrello-link"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <%
                    if(c!=null){
                        out.println("<li class=\"right-buttons\">" +
                                "<div class=\"dropdown\">" +
                                "<button class=\"dropbtn\" onclick=\"dropdownMenu()\">" +
                                "<i class=\"fa-solid fa-circle-user\"></i>Profilo\n" +
                                "</button>" +
                                "<div class=\"dropdown-content\" id=\"myDropdown\">\n" +
                                "<a href=\"modInfoCliente\">Il mio profilo</a>\n" +
                                "<a href=\"storicoOrdini\">I miei ordini</a>\n" +
                                "<a href=\"Logout\" class=\"logout-link\">LogOut</a>\n" +
                                "</div>" +
                                "</div>" +
                                "</li>");
                    }
                %>
                <%
                    if(c==null){
                        out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
                    }/*
                        else{
                            out.println("<li class=\"right-buttons\"><a href=\"Logout\">Logout</a></li>");
                        }*/
                %>
            </ul>
        </div>
    </div>
</div>
  <%
      Cliente cliente = (Cliente) session.getAttribute("cliente");
      Carrello carrello = (Carrello) session.getAttribute("carrello");
      out.println("<form action=\"expireCart\" method=\"GET\" onsubmit=\"return validateInfoSped()\">" +
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
<div class=\"total-price\">
    <h3>Totale: <%=carrello.calculateTotal()%> </h3>
    </div>
</body>
</html>
