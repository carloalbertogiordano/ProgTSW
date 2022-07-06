<%@ page import="Model.PasswordEncrypter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script>
        function validateForm() {
            const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
            return (cerca.test($("#mail").val()) && $("#password").val() !== '');
    }
    </script>

    <title>Login</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
</head>
<body>

<!-- Viene visualizzato solo se è stato già fallito un login -->
<%
if (request.getAttribute("loginErr") != null) {
    out.println("<div class=\"alert\">");
    out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
    out.println("<strong>Attenzione: </strong> Mail o password errata.");
    out.println("</div>");
}
%>

<h1>Login</h1>
<form action="Login" method="post" onsubmit="return validateForm()">
    <table>
        <tr>
            <td>Mail:</td>
            <td><input type="text" name="Mail" id="mail" required/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="Password" id="password" required/></td>
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
