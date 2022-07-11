<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <script>
        $document.ready(function(){
           $("#quantity").change(function(){
               $.ajax({
                   url: 'changeQuantity',
                   type: 'POST',
                   data: {newQuantity: $("#quantity").val(), idProdotto: $("#idProdotto").val()},
                   success: function(response){
                       $("#divCarrello").html(response);
                   }
               })
           });
        });
    </script>
</head>
<body>
<div id="divCarrello">
    <%
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if(!carrello.isEmpty()) {
            List<Prodotto> carrelloList = carrello.getCarrello();
            out.println("<li>");
            for (Prodotto prodotto : carrelloList) {
                out.println("<ul>" + prodotto.toString() +
                        "<form action=\"removeCart\" method=\"GET\">" +
                        "<input type=\"hidden\" name=\"idProdotto\" id=\"idProdotto\" value=\"" + prodotto.getID() + "\">" +
                        "<input type=\"number\" id=\"quantity\" name=\"quantity\" min=\"1\" max=\"" + prodotto.getQuantità() + "\">" +
                        "<input type=\"submit\" value=\"Rimuovi\" id=\"submit\"></form>" +
                        "</ul>");
            }

            if (cliente != null) {
                out.println("</li>" +
                        "<br>" +
                        "<a href=\"redirectToIndirizzoSpedizione\">Procedi all'ordine</a>");
            }
            else {
                out.println("</li>" +
                        "<br>" +
                        "Devi essere loggato per acquistare. Procedi al <a href=\"login.jsp\"> login </a>");
            }
        }
        else{
            out.println("<h1>Il carrello è vuoto</h1>");
        }
    %>
</div>
</body>
</html>
