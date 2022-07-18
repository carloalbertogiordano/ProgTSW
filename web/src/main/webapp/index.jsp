<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
    <div class="header">
        <div class="flex-container">
            <div class="flex-left-item logo">
                <a href="index.jsp"><img src="Images/PCBuilder-logo.png" id="header-logo"></a>
            </div>
            <div class="nav flex-right-item">
                <ul class="flex-container">
                    <li><a href="HomeServlet" class="active">Home</a></li>
                    <li><a href="Catalogo.jsp">Catalogo</a></li>
                    <li><a href="#">Chi siamo</a></li>
                    <%
                        if(c != null){
                            out.println("<li><a href=\"storicoOrdini\">Strorico ordini</a></li>");
                        }
                    %>
                    <li class="empty-flex-field"></li>
                    <li class="right-buttons"><a href="carrello.jsp" class="carrello-link"><i class="fa-solid fa-cart-shopping"></i></a></li>
                    <%
                        if(c!=null){
                            out.println("<li class=\"right-buttons\">" +
                                        "<div class=\"dropdown\">" +
                                            "<button class=\"dropbtn\" onclick=\"myFunction()\">" +
                                                "<i class=\"fa-solid fa-circle-user\"></i>Profilo\n" +
                                            "</button>" +
                                            "<div class=\"dropdown-content\" id=\"myDropdown\">\n" +
                                                "<a href=\"#\">Il mio profilo</a>\n" +
                                                "<a href=\"storicoOrdini.jsp\">I miei ordini</a>\n" +
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
            <a href="javascript:void(0);" class="right-buttons burger"  onclick="responsiveNavBar()">&#9776;</a>
        </div>
    </div>
    <div class="main flex-container">
        <%
            if(c != null){
                out.println("<div class=\"welcome-header\">" +
                        "<h1>Bentornato " + c.getNickname() + "</h1>" +
                        "</div>");
            }
            else{
                out.println("<div class=\"welcome-header\">" +
                        "<h1>Benvenuto su PCBuilder</h1><br>" +
                        "</div>");
            }
        %>
    </div>

<p>Testing test: <a href="testing.jsp"> TEST </a></p>

</body>
</html>