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
    <button onclick="nuovo()">Nuovo Prodotto</button>
    <button onclick="elimina()">Cancella Prodotto</button>
    <button onclick="aggiorna()">Mod. Quantità Prodotto</button>

    <br>
    <p id="p">Premi un pulsante.</p>

<script>
    //funzione per l'aggionamento della quantità nel DB
    function aggiorna(){
        document.getElementById("p").innerHTML =
            "<form action=\"Aggiorna\" method=\"get\">" +
            "<table>" +
            "<tr>" +
            "<td>ID</td>" +
            "<td><input type=\"text\" name=\"ID\"/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Quantità</td>" +
            "<td><input type=\"text\" name=\"quantita\"/></td>" +
            "</tr>" +
            "</table>" +
            "<input type=\"submit\" value=\"Invia\"/>" +
            "</form>";
    }

    function resetForm(){
        document.getElementById("wattaggio").disabled = false;
        document.getElementById("frequenza").disabled = false;
        document.getElementById("n_core").disabled = false;
        document.getElementById("n_ram").disabled = false;
        document.getElementById("n_usb").disabled = false;
        document.getElementById("n_pci").disabled = false;
        document.getElementById("mbs").disabled = false;
        document.getElementById("vram").disabled = false;
        document.getElementById("n_watt").disabled = false;
        document.getElementById("w_cpu").disabled = false;
        document.getElementById("formaMobo").disabled = false;
    }

    //prende il tipo di prodotto scelto e disabilita i campi non necessari
    function controllo() {
            let tipo = document.getElementById("tipo").value;
            console.log(tipo);
            resetForm();
            switch (tipo) {
                case "HDD":
                    document.getElementById("wattaggio").disabled = true;
                    document.getElementById("frequenza").disabled = true;
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    break;

                case "SSD":
                    document.getElementById("wattaggio").disabled = true;
                    document.getElementById("frequenza").disabled = true;
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    break;

                case "CASE":
                    document.getElementById("wattaggio").disabled = true;
                    document.getElementById("frequenza").disabled = true;
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    break;

                case "CPU":
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    break;

                case "DISSIPATORE":
                    document.getElementById("wattaggio").disabled = true;
                    document.getElementById("frequenza").disabled = true;
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    break;

                case "GPU":

                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    break;

                case "MOBO":
                    document.getElementById("wattaggio").disabled = true;
                    document.getElementById("frequenza").disabled = true;
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    break;

                case "PSU":
                    document.getElementById("wattaggio").disabled = true;
                    document.getElementById("frequenza").disabled = true;
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    break;

                case "RAM":
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    break;

                default:
                    document.getElementById("marca").disabled = true;
                    document.getElementById("modello").disabled = true;
                    document.getElementById("prezzo").disabled = true;
                    document.getElementById("quantità").disabled = true;
                    document.getElementById("wattaggio").disabled = true;
                    document.getElementById("frequenza").disabled = true;
                    document.getElementById("n_core").disabled = true;
                    document.getElementById("n_ram").disabled = true;
                    document.getElementById("n_usb").disabled = true;
                    document.getElementById("n_pci").disabled = true;
                    document.getElementById("mbs").disabled = true;
                    document.getElementById("vram").disabled = true;
                    document.getElementById("n_watt").disabled = true;
                    document.getElementById("w_cpu").disabled = true;
                    document.getElementById("formaMobo").disabled = true;
                    document.getElementById("url").disabled = true;
                    document.getElementById("descrizione").disabled = true;
                    break;

            }
    }
        //Funzione che stampa il form per l'inserimento di un nuovo prodotto nel database
        function nuovo() {
            document.getElementById("p").innerHTML =
                "<form action=\"Upload\" method=\"post\" enctype=\"multipart/form-data\" >" +
                "<table>" +
                //La funzione controllo() è chiamata per disabilitare i campi del form in base al tipo di prodotto scelto
                "<select name=\"tipo\" id=\"tipo\" onchange =\"controllo()\">" +
                "<option value=\"HDD\">Hdd</option>" +
                "<option value=\"SSD\">Ssd</option>" +
                "<option value=\"CASE\">Case</option>" +
                "<option value=\"CPU\">Cpu</option>" +
                "<option value=\"DISSIPATORE\">Dissipatore</option>" +
                "<option value=\"GPU\">Gpu</option>" +
                "<option value=\"MOBO\">Mobo</option>" +
                "<option value=\"PSU\">Psu</option>" +
                "<option value=\"RAM\">Ram</option>" +
                "</select>" +
                "<tr>" +
                "<td>Marca</td>" +
                "<td><input type=\"text\" name=\"marca\" id=\"marca\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Modello</td>" +
                "<td><input type=\"text\" name=\"modello\" id=\"modello\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Prezzo</td>" +
                "<td><input type=\"text\" name=\"prezzo\" id=\"prezzo\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Quantità</td>" +
                "<td><input type=\"text\" name=\"quantita\" id=\"quantita\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Wattaggio</td>" +
                "<td><input type=\"text\" name=\"wattaggio\" id=\"wattaggio\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Frequenza</td>" +
                "<td><input type=\"text\" name=\"frequenza\" id=\"frequenza\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Numero di cores</td>" +
                "<td><input type=\"text\" name=\"N_Core\" id=\"n_core\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Numero di slot RAM</td>" +
                "<td><input type=\"text\" name=\"N_Ram\" id=\"n_ram\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Numero di USB</td>" +
                "<td><input type=\"text\" name=\"N_Usb\" id=\"n_usb\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Numero slot PCI</td>" +
                "<td><input type=\"text\" name=\"N_Pci\" id=\"n_pci\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>MB/s</td>" +
                "<td><input type=\"text\" name=\"MBs\" id=\"mbs\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Quantià VRAM</td>" +
                "<td><input type=\"text\" name=\"Vram\" id=\"vram\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Numero di WATT</td>" +
                "<td><input type=\"text\" name=\"N_Watt\" id=\"n_watt\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Watt possibili dissipare</td>" +
                "<td><input type=\"text\" name=\"W_Cpu\" id=\"w_cpu\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Forma MOBO</td>" +
                "<td><input type=\"text\" name=\"formaMobo\" id=\"formaMobo\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Immagine</td>" +
                "<td><input type=\"file\" name=\"image\" id=\"url\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Descrizione</td>" +
                "<td><input type=\"text\" name=\"descrizione\" id=\"descrizione\"/></td>" +
                "</tr>" +
                "</table>" +
                "<input type=\"submit\" value=\"Invia\"/>" +
                "</form>";
            controllo();
        }

    //Funzione che elimina un prodotto nel db dato l'id
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
