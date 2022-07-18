<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Indirizzo spedizione</title>

    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script type = "text/javascript">
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

        function validateInfoSped(){
            return testCap() && testProv() && testVia() && testCitta();
        }
    </script>
</head>
<body>
  <%
      Cliente cliente = (Cliente) session.getAttribute("cliente");
      Carrello carrello = (Carrello) session.getAttribute("carrello");
      out.println("<form action=\"expireCart\" method=\"GET\" onsubmit=\"return validateInfoSped()\">" +
              "<div id=\"modIndirizzo\">" +
              "<table>"+
              "<form action=\"expireCart\" method=\"\">" +
              "<input type=\"hidden\" name=\"idCarrello\" id=\"idCarrello\" value=\"" + carrello.getCarrelloCod() + "\">" +
              "<tr>"+
              "<td> Via: </td>"+
              "<td><input type=\"text\" name=\"via\" id=\"via\" value=\""+ cliente.getVia() +"\" > </td>" +
              "</tr>"+
              "<tr>"+
              "<td> Provincia: </td>"+
              "<td><input type=\"text\" name=\"provincia\" id=\"provincia\" value=\""+ cliente.getProvincia() +"\" > </td>"+
              "</tr>"+
              "<tr>"+
              "<td> Città: </td>"+
              "<td><input type=\"text\" name=\"citta\" id=\"citta\" value=\""+ cliente.getCitta() +"\" > </td>"+
              "</tr>"+
              "<tr>"+
              "<td> CAP: </td>"+
              "<td><input type=\"text\" name=\"cap\" id=\"cap\" value=\""+ cliente.getCap() +"\" > </td>"+
              "</tr>"+
              "<input type=\"submit\" value=\"Concludi ordine\" id=\"submit\">" +
              "</form>" +
              "</table>"+
              "</div>");
  %>
</body>
</html>
