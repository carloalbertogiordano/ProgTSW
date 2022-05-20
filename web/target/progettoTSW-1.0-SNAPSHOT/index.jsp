<%@ page import="Model.Cliente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.CPU" %>
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
    <div class="navbar">
        <%
            if(c==null){
                out.println("<a href=\"login.jsp\"><button class = \"login\">Login</button></a>");
            }
            else{
                out.println("<a href=\"Logout\"><button class =\"login\">Logout</button></a>");
            }
        %>
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

<a href = "CarrelloServlet">Carrello</a>

<p>Testing: <a href="testing.jsp"> TEST </a></p>

</body>
</html>