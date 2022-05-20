<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 18/05/22
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Creazione Utente</title>
  <link rel = "stylesheet" type = "text/css" href = "style.css">
</head>
<body>

<%
  if (request.getAttribute("register.error") != null) {
    out.println("<div class=\"alert\">");
    out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
    out.println("<strong>Attenzione: </strong> Compila tutti i campi.");
    out.println("</div>");
  }
%>

<h1>Creazione Utente</h1>
<form action="NuovoCliente" method="post">
  <table>
    <div class="divide"> <!---non funziona la class--->
      <tr>
        <td>NikName:</td>
        <td><input type="text" name="NikName"/></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="Password"/></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="Email"/></td>
      </tr>
      <tr>
        <td>Telefono:</td>
        <td><input type="text" name="Telefono"/></td>
      </tr>
      <tr>
        <td>CAP:</td>
        <td><input type="text" name="CAP"/></td>
      </tr>
      <tr>
        <td>Provincia</td>
        <td><input type="text" name="Provincia"/></td>
      </tr>
    </div>
    <div class="divide">
      <tr>
        <td>Numero Carta</td>
        <td><input type="text" name="Carta"/></td>
      </tr>
      <tr>
        <td>Scadenza Carta</td>
        <td><input type="text" name="Scadenza"/></td>
      </tr>
      <tr>
        <td>CVV</td>
        <td><input type="text" name="CVV"/></td>
      </tr>
      <tr>
        <td>Intestatario</td>
        <td><input type="text" name="Intestatario"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="Registra"></td>
      </tr>
    </div>
  </table>
</form>
<p>Oppure  fai il <a href="login.jsp">login</a></p>
</body>
</html>
