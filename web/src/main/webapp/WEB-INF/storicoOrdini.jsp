<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page import="Model.Carrello_.CarrelloDAO" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Strorico ordini</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
</head>
<body>
<%
    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
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
                    if(cliente!=null){
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
                    if(cliente==null){
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
<div class="main">
    <h1>Ecco lo storico dei tuoi ordini: </h1>
    <%
        ArrayList<Carrello> ordini;
        try {
            assert cliente != null;//Evita null pointer
            ordini = CarrelloDAO.doRetriveStorico(cliente.getMail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(ordini == null)
            out.println("Non hai ancora fatto acquisti");
        else {
            for (Carrello c : ordini) {
                out.println(c.toString() + "<br>");
            }
        }
    %>
</div>
</body>
</html>