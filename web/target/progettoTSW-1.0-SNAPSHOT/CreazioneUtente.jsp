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
      return reNick.test($("#nick").val());
    }

    function testMail() {
      const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
      return cerca.test($("#mail").val());
    }

    function testPassword() {
      const cerca = /[a-zA-Z0-9]{1,130}/;
      return cerca.test($("#password").val());
    }

    function testTel() {
      const cerca = /[0-9]{10}/;
      return cerca.test($("#tel").val())
    }

    function testCap() {
      const cerca = /[0-9]{5}/;
      return cerca.test($("#cap").val());
    }

    function testProv() {
      const cerca = /[A-Z]{2}/;
      return cerca.test($("#provincia").val());
    }

    function validateForm(){
      return true;//testNickname() && testMail() && testPassword() && testTel() && testCap() && testProv();
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
        <td><input type="text" name="nikname" required/></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password" required/></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email" required/></td>
      </tr>
      <tr>
        <td>Telefono:</td>
        <td><input type="text" name="telefono" required/></td>
      </tr>
      <tr>
        <td>CAP:</td>
        <td><input type="text" name="cap" required/></td>
      </tr>
      <tr>
        <td>Provincia</td>
        <td><input type="text" name="provincia" required/></td>
      </tr>
    </div>
    <div class="divide">
      <tr>
        <td>Città</td>
        <td><input type="text" name="citta" required/></td>
      </tr>
      <tr>
        <td>Via</td>
        <td><input type="text" name="via" required/></td>
      </tr>
      <tr>
        <td><input type="submit"></td>
      </tr>
    </div>
  </table>
</form>
<p>Oppure fai il <a href="login.jsp">login</a></p>
</body>
</html>
