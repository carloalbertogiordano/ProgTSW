<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 5/24/22
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
</head>
<body>
    <button
            onclick="nuovo()">Nuovo Prodotto</button>

    <button onclick="elimina()">Cancella Prodotto</button>
    <br>
    <p id="p">Premi un pulsante.</p>

<script>
    function nuovo() {
    document.getElementById("p").innerHTML =
        "<form action=\"Upload\" method=\"get\">" +
        "<table>" +
        "<tr>" +
            "<td>Marca</td>" +
            "<td><input type=\"text\" name=\"Marca\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Modello</td>" +
        "<td><input type=\"text\" name=\"Modello\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Prezzo</td>" +
        "<td><input type=\"text\" name=\"Prezzo\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Quantità</td>" +
        "<td><input type=\"text\" name=\"Quantita\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Wattaggio</td>" +
        "<td><input type=\"text\" name=\"Wattaggio\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Tipo</td>" +
        "<td><input type=\"text\" name=\"Tipo\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Frequenza</td>" +
        "<td><input type=\"text\" name=\"Frequenza\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero di cores</td>" +
        "<td><input type=\"text\" name=\"N_Core\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero di slot RAM</td>" +
        "<td><input type=\"text\" name=\"N_Ram\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero di USB</td>" +
        "<td><input type=\"text\" name=\"N_USB\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero slot PCI</td>" +
        "<td><input type=\"text\" name=\"N_PCI\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>MB/s</td>" +
        "<td><input type=\"text\" name=\"MBS\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Quantià VRAM</td>" +
        "<td><input type=\"text\" name=\"VRAM\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Numero di WATT</td>" +
        "<td><input type=\"text\" name=\"N_Watt\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Watt possibili dissipare</td>" +
        "<td><input type=\"text\" name=\"W_CPU\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Forma MOBO</td>" +
        "<td><input type=\"text\" name=\"FormaMobo\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>url Immagine</td>" +
        "<td><input type=\"text\" name=\"url\"/></td>" +
        "</tr>" +
        "<tr>" +
        "<td>Descrizione</td>" +
        "<td><input type=\"text\" name=\"Descrizione\"/></td>" +
        "</tr>" +
        "</table>" +
        "<input type=\"submit\" value=\"Invia\"/>" +
        "</form>";
    }

    function elimina () {
        document.getElementById("p").innerHTML =
        "<form action=\"Elimina\" method=\"get\">" +
        "<table>" +
        "<tr>" +
            "<td>ID</td>" +
            "<td><input type=\"text\" name=\"ID\"/></td>" +
        "</tr>" +
        "</table>" +
        "<input type=\"submit\" value=\"Invia\"/>" +
        "</form>";
    }

</script>




</body>
</html>
