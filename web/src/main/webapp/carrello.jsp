<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page import="Model.CATALOGO_.Catalogo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script>
        $(document).ready(function() {
            $('.input_num').click(function() {
                let id = $(this).parent().closest('div').attr('id');
                let newQuant = $('#quantityOf'+id).val();
                console.log("NEWQUANT: "+newQuant+" ID: "+id);
                $.ajax({
                    url: 'modQuantCartDB',
                    type: 'GET',
                    data: {attr_id: id, attr_newQuant: newQuant
                    },
                });

            });
        });
    </script>
</head>
<body>
<div id="divCarrello">
    <%
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");

        if(!carrello.isEmpty()) {
            List<Prodotto> carrelloList = carrello.getCarrello();
            out.println("<li>");
            for (Prodotto prodotto : carrelloList) {
                out.println(" <div id=\""+prodotto.getID()+"\"> <ul>" + prodotto.toString() +
                        "<form action=\"removeCart\" method=\"GET\">" +
                        "<input type=\"hidden\" name=\"idProdotto\" id=\"idProdotto\" class=\""+prodotto.getID()+"\" value=\"" + prodotto.getID() + "\">" +
                        "<input type=\"number\" id=\"quantityOf"+prodotto.getID()+"\" class=\"input_num\" name=\"quantity\" min=\"1\" value=\""+prodotto.getQuantità()+"\" max=\"" + catalogo.doRetriveById(prodotto.getID()).getQuantità() + "\">" +
                        "<input type=\"submit\" value=\"Rimuovi\" id=\"submit\"></form>" +
                        "</ul> </div>");
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
