<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Creazione Utente</title>
  <link rel = "stylesheet" type = "text/css" href = "style.css">

  <script src="https://code.jquery.com/jquery-git.js"></script>
  <script type = "text/javascript">
    function testNickname() {
      let reNick = /[a-zA-Z0-9]{1,70}/ ;
      return reNick.test($("#nick"));
    }

    function testMail() {
      const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
      return cerca.test($("#mail").val());
    }

    function testPassword() {
      const cerca = /[a-zA-Z0-9]{1,130}/;
      return cerca.test($("#password"));
    }

    function testTel() {
      const cerca = /[0-9]{13}/;
      return cerca.test($("#tel"))
    }

    function testCap() {
      const cerca = /[0-9]{5}/;
      return cerca.test($("#cap"));
    }

    function testProv() {
      const cerca = /[A-Z]{2}/;
      return cerca.test($("#provincia"));
    }

    function validateForm(){
      return testNickname() && testMail() && testPassword() && testTel() && testCap() && testProv();
    }
  </script>

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
<form action="NuovoCliente" method="post" onsubmit="return validateForm()">
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
