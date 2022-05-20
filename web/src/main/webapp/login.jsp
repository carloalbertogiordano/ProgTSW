<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel = "stylesheet" type = "text/css" href = "WEB-INF/css/style.css">
</head>
<body>

<!-- Viene visualizzato solo se è stato già fallito un login -->
<%
if (request.getAttribute("login.error") != null) {
    out.println("<div class=\"alert\">");
    out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
    out.println("<strong>Attenzione: </strong> Mail o password errata.");
    out.println("</div>");
}
%>

<h1>Login</h1>
<form action="Login" method="post">
    <table>
        <tr>
            <td>Mail:</td>
            <td><input type="text" name="Mail"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="Password"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Login"/></td>
        </tr>
    </table>
</form>
<p>Oppure <a href="CreazioneUtente.jsp">registrati</a></p>

</body>
</html>
