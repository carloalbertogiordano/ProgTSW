<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Creazione Utente</title>
  <script src="https://code.jquery.com/jquery-git.js"></script>
  <link rel = "stylesheet" type = "text/css" href = "css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
  <script src="js/navbar.js"></script>
  <script src="js/crazioneUtente.js"></script>

</head>
<body>
<%
  Cliente c = (Cliente) session.getAttribute("cliente");
%>
<div class="header">
  <div class="flex-container topnav" id ="topnav">
    <div class="flex-left-item logo">
      <a href="index.jsp"><img src="Images/PCBuilder-logo.png" id="header-logo"></a>
    </div>
    <a href="javascript:void(0);" class="right-buttons burger"  onclick="dropDownBurger()">&#9776;</a>
    <div class="nav flex-right-item" id="nav-list">
      <ul class="flex-container">
        <li><a href="index.jsp" class="active">Home</a></li>
        <li><a href="Catalogo.jsp">Catalogo</a></li>
        <li><a href="#">Chi siamo</a></li>
        <li class="empty-flex-field" id="emptyFlexField"></li>
        <li class="right-buttons"><a href="carrello.jsp" class="carrello-link"><i class="fa-solid fa-cart-shopping"></i></a></li>
        <%
          if(c!=null){
            out.println("<li class=\"right-buttons\">" +
                    "<div class=\"dropdown\">" +
                    "<button class=\"dropbtn\" onclick=\"dropdownMenu()\">" +
                    "<i class=\"fa-solid fa-circle-user\"></i>Profilo\n" +
                    "</button>" +
                    "<div class=\"dropdown-content\" id=\"myDropdown\">\n" +
                    "<a href=\"modInfoCliente\">Il mio profilo</a>\n" +
                    "<a href=\"storicoOrdini\">I miei ordini</a>\n" +
                    "<a href=\"Logout\" class=\"logout-link\">LogOut</a>\n" +
                    "</div>" +
                    "</div>" +
                    "</li>");
          }

          if(c==null){
            out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
          }/*
                        else{
                            out.println("<li class=\"right-buttons\"><a href=\"Logout\">Logout</a></li>");
                        }*/
        %>
      </ul>
    </div>
  </div>
</div>
<%
  if (request.getAttribute("register.error") != null) {
    out.println("<div class=\"alert\">");
    out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
    out.println("<strong>Attenzione: </strong> Compila tutti i campi.");
    out.println("</div>");
  }
  if (request.getAttribute("creation.error") != null) {
    out.println("<div class=\"alert\">");
    out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
    out.println("<strong>Attenzione: </strong> Mail già registrata");
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
