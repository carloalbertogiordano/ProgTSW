<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.GPU_.Gpu" %>
<%@ page import="Model.CATALOGO_.Catalogo" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="static com.sun.tools.javac.jvm.ByteCodes.illegal" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Info</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<div><%
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
        int id = Integer.parseInt(request.getParameter("Id"));
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");

        Prodotto p = catalogo.doRetriveById(id);
        if(p != null) {
            out.println("<div><ul>" +
                    "<li>Marca: " + p.getMarca() + "</li>" +
                    "<li>Modello: " + p.getModello() + "</li>" +
                    "</ul>" +
                    "<form action=\"addCart\" id=\"buy\">" +
                    "<input type=\"hidden\" name=\"Id\" value=" + p.getID() + ">");
                    if(p.getQuantita() > 0) {
                        out.println(
                                "<input type=\"number\" id=\"quantity\" name=\"quantity\" min=\"1\" max=\"" + p.getQuantita() + "\">" +
                                        "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                                        "</div>");
                    }
                    else{
                        out.println("Ci dispiace, questo prodotto non è più disponibile.</form></div>");
                    }
        }
        else{
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request,response);
        }
    %>
</div>
</body>
</html>
