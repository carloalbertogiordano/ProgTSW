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
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
    <div class="header">
        <a href="HomeServlet"><img src="Images/PCBuilder-logo.png" id="header-logo"></a>
        <div class="nav">
            <ul>
                <li><a href="HomeServlet" class="active">Home</a></li>
                <li><a href="Catalogo.jsp">Catalogo</a></li>
                <li><a href="carrello.jsp">Carrello</a></li>
                <li><a href="#">Chi siamo</a></li>

                <%//Da mostrare solo se il cliente è loggato
                    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                    if(cliente != null){
                        out.println("<li><a href=\"infoCliente\">Info. cliente</a></li>" +
                                "<li><a href=\"storicoOrdini\">Strorico ordini</a></li>");
                    }
                %>


                <%
                    if(c==null){
                        out.println("<li class=\"login-button\"><a href=\"login.jsp\">Login</a></li>");
                    }
                    else{
                        out.println("<li class=\"login-button\"><a href=\"Logout\">Logout</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
    <div class="headers">
        <%
            if(c != null){
                out.println("<h1>Bentornato " + c.getNickname() + "</h1>");
            }
            else{
                out.println("<h1>Benvenuto su PCBuilder</h1><br>");
            }
        %>
    </div>

<p>Testing test: <a href="testing.jsp"> TEST </a></p>

</body>
</html>