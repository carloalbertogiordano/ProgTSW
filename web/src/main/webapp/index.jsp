<%@ page import="Model.Cliente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel = "stylesheet" type = "text/css" href = "WEB-INF/css/style.css">
</head>
<body>
<%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
    <div class="navbar">
        <%
            if(c==null){
                out.println("<a href=\"login.jsp\"><button>Login</button></a>");
            }
            else{
                out.println("<a href=\"logout.jsp\"><button id=\"login\">Logout</button></a>");
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


</body>
</html>