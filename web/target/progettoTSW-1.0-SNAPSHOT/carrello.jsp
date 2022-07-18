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

        $(document).ready(function(){
            function saveOldQuant() {
                let id = $('#submit').parent().attr("id");
                return $('#quantOf' + id).val();
            }
            let oldQuant = saveOldQuant();
            $('#submit').click(function(){
                let id = $(this).parent().attr("id");
                let quant = $('#quantOf'+id).val();
                console.log(quant+' Id'+id+' oldQuant'+oldQuant);
                $.ajax({
                    url: 'modQuantCartDB',
                    type: 'GET',
                    data: {attr_id: id, attr_newQuant: quant, attr_OldQuant: oldQuant
                    },
                });
                oldQuant = quant;
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
                out.println("<div id=\""+prodotto.getID()+"\">" +
                        "<input type=\"number\" id=\"quantOf"+prodotto.getID()+"\" name=\"quantity\" min=\"1\" value=\""+prodotto.getQuantità()+"\" max=\"" + (catalogo.doRetriveById(prodotto.getID()).getQuantità()+carrello.doRetriveQuantitaProdottoById(prodotto.getID())) + "\">" +
                        "<input type=\"button\" value=\"Aggiorna quantità\" id=\"submit\">" +
                        "</ul> </div>");

                out.println("<form action=\"removeCart\" method=\"GET\">" +
                        "<input type=\"hidden\" name=\"idProdotto\" id=\"idProdotto\" class=\""+prodotto.getID()+"\" value=\"" + prodotto.getID() + "\">" +
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
