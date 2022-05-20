<%@ page import="Model.Cliente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel = "stylesheet" type = "text/css" href = "style.css">
</head>
<body>
    <!--
    <div class = "img">
        <img src="Images/logo.png" alt="LOGO">
    </div>
    -->

    <div>
        <h1>
            <%
                Cliente c = (Cliente) session.getAttribute("cliente");
                if(c != null){
                    out.println("<h1>Bentornato " + c.getNickname() + "</h1>");
                }
                else{
                    out.println("<h1>Benvenuto su PCBuilder</h1>");
                }
            %>
        </h1>
    </div>

    <div class="dots-container">
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
    </div>
    <p>Registrati: <a href="CreazioneUtente.jsp">register</a></p>
    <p>Login: <a href="login.jsp">login</a></p>


</body>
</html>