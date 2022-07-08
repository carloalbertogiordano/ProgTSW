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
      console.log("nick"+reNick.test($("#nick").val()));
      return reNick.test($("#nick").val());
    }

    function testMail() {
      const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
      console.log("mail"+cerca.test($("#mail").val()));
      return cerca.test($("#mail").val());
    }

    function testPassword() {
      const cerca = /[a-zA-Z0-9]{1,130}/;
      console.log("password"+cerca.test($("#password").val()));
      return cerca.test($("#password").val());
    }

    function testTel() {
      const cerca = /[0-9]{2}[0-9]{10}/;
      console.log("tel"+cerca.test($("#tel").val())+$("#tel").val());
      return cerca.test($("#tel").val());
    }

    function testCap() {
      const cerca = /[1-9][0-9]{4}/;
      console.log("cap"+cerca.test($("#cap").val()));
      return cerca.test($("#cap").val());
    }

    function testProv() {
      const cerca = /[A-Z]{2}/;
      console.log("provincia"+cerca.test($("#provincia").val()));
      return cerca.test($("#provincia").val());
    }

    function testVia() {
      const cerca = /[a-zA-Z ]{1,100}[0-9]{1,3}/;
      console.log("via"+cerca.test($("#via").val()));
      return cerca.test($("#via").val());
    }

    function testCitta() {
      const cerca = /[a-zA-Z]{1,100}/;
      console.log("citta"+cerca.test($("#citta").val()));
      return cerca.test($("#citta").val());
    }

    function validateForm(){
      return testNickname() && testMail() && testPassword() && testTel() && testCap() && testProv()
              && testVia() && testCitta();
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
        <td><input type="text" name="nikname" id="nikname" required/></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password" id="password" required/></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="mail" id="mail" required/></td>
      </tr>
      <tr>
        <td>Telefono:</td>
        <td>+<input type="text" name="telefono" id="tel" required/></td>
      </tr>
      <tr>
        <td>CAP:</td>
        <td><input type="text" name="cap" id="cap" required/></td>
      </tr>
      <tr>
        <td>Provincia</td>
        <td><input type="text" name="provincia" id="provincia" required/></td>
      </tr>
    </div>
    <div class="divide">
      <tr>
        <td>Città</td>
        <td><input type="text" name="citta" id="citta" required/></td>
      </tr>
      <tr>
        <td>Via</td>
        <td><input type="text" name="via" id="via" required/></td>
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
