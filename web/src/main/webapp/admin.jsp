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
            "<select name=\"tipo\">" +
            "<option value=\"HDD\">Hdd</option>" +
            "<option value=\"SSD\">Ssd</option>" +
            "<option value=\"Case\">Case</option>" +
            "<option value=\"Cpu\">Cpu</option>" +
            "<option value=\"Dissipatore\">Dissipatore</option>" +
            "<option value=\"Gpu\">Gpu</option>" +
            "<option value=\"Mobo\">Mobo</option>" +
            "<option value=\"Psu\">Psu</option>" +
            "<option value=\"Ram\">Ram</option>" +
            "</select>" +
            "<tr>" +
            "<td>Marca</td>" +
            "<td><input type=\"text\" name=\"marca\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Modello</td>" +
            "<td><input type=\"text\" name=\"modello\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Prezzo</td>" +
            "<td><input type=\"text\" name=\"prezzo\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Quantità</td>" +
            "<td><input type=\"text\" name=\"quantita\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Wattaggio</td>" +
            "<td><input type=\"text\" name=\"wattaggio\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Frequenza</td>" +
            "<td><input type=\"text\" name=\"frequenza\"/></td>" +
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
            "<td><input type=\"text\" name=\"N_Usb\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Numero slot PCI</td>" +
            "<td><input type=\"text\" name=\"N_Pci\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>MB/s</td>" +
            "<td><input type=\"text\" name=\"MBs\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Quantià VRAM</td>" +
            "<td><input type=\"text\" name=\"Vram\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Numero di WATT</td>" +
            "<td><input type=\"text\" name=\"N_Watt\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Watt possibili dissipare</td>" +
            "<td><input type=\"text\" name=\"W_Cpu\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Forma MOBO</td>" +
            "<td><input type=\"text\" name=\"formaMobo\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>url Immagine</td>" +
            "<td><input type=\"text\" name=\"url\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Descrizione</td>" +
            "<td><input type=\"text\" name=\"descrizione\"/></td>" +
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
