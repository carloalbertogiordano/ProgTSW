<%@ page import="Model.Prodotto" %>
<%@ page import="Model.CATALOGO_.Catalogo" %>
<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.PSU_.Psu" %>
<%@ page import="Model.MOBO_.Mobo" %>
<%@ page import="Model.RAM_.Ram" %>
<%@ page import="Model.Archiviazione_.HDD_.Hdd" %>
<%@ page import="Model.Archiviazione_.SDD_.Ssd" %>
<%@ page import="Model.GPU_.Gpu" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifica Pezzo</title>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script src="js/modificaPezzo.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
    <link rel = "stylesheet" type = "text/css" href = "css/modificaPezzo.css">
    <link rel = "stylesheet" type = "text/css" href = "css/style.css">
</head>
<body>
<%
    String id = request.getParameter("Id");
    Catalogo catalogo = (Catalogo) request.getSession().getAttribute("catalogo");
    Prodotto p = catalogo.doRetriveById(Integer.parseInt(id));
    Cliente c = (Cliente) request.getSession().getAttribute("cliente");

%>
<div class="main flex-container">
    <form id="modProd" method="post" action="Aggiorna" class="flex-container" onsubmit="return validateProductUpdate()" enctype="multipart/form-data">
        <input type="hidden" name="tipo" id="tipo" value="<%=p.getTipo()%>"/>
        <input type="hidden" name="ID" id="ID" value="<%=p.getID()%>"/>
        <label for="marca">Marca</label>
        <input type="text" name="marca" id="marca" value="<%=p.getMarca()%>" placeholder="Marca" equired/>
        <label for="modello">Modello</label>
        <input type="text" name="modello" id="modello" value="<%=p.getModello()%>" placeholder="Modello" required/>
        <label for="prezzo">Prezzo</label>
        <input type="text" name="prezzo" id="prezzo" value="<%=p.getPrezzo()%>" placeholder="Prezzo" required/>
        <label for="quantita">Quantità</label>
        <input type="text" name="quantita" id="quantita" value="<%=p.getQuantita()%>" placeholder="Quantità" required/>
        <label for="desc">Descrizione</label>
        <input type="text" name="desc" id="desc" value="<%=p.getDescrizione()%>" placeholder="Descrizione" required/>
        <input type="hidden" name="url" id="url" value="<%=p.getUrl()%>" required/>
        <%
            switch (p.getTipo()) {
                case "CPU":
                    Cpu cpu = (Cpu) p;
                    out.println("" +
                            "                <label for=\"numCore\">Numero di core</label>" +
                            "                <input type=\"text\" name=\"numCore\" id=\"ncore\" placeholder=\"Numero di core\" value=\"" + cpu.getN_Core() + "\" form=\"modProd\" required/>" +
                            "                <input type=\"text\" name=\"watt\" id=\"watt\" placeholder=\"Watts\" value=\"" + cpu.getWattaggio() + "\" form=\"modProd\" required/>" +
                            "                <input type=\"text\" name=\"frequenza\" id=\"frequenza\" placeholder=\"Frequenza\" value=\"" + cpu.getFrequenza() + "\" form=\"modProd\" required/>");
                    break;
                case "CASE":
                    Case case_ = (Case) p;
                    out.println("" +
                            "                <label for=\"forma\">Forma scheda madre</label>" +
                            "                <input type=\"number\" name=\"forma\" id=\"formaMobo\" placeholder=\"Formato scheda madre\" value=\"" + case_.getFormaMobo() + "\" max=\"2\" min=\"0\" form=\"modProd\" required/>");
                    break;
                case "DISSIPATORE":
                    Dissipatore dissipatore = (Dissipatore) p;
                    out.println("" +
                            "                <label for=\"wCPU\">Watt dissipabili</label>" +
                            "                <input type=\"text\" name=\"wCpu\" id=\"wCpu\" placeholder=\"Watt\" value=\"" + dissipatore.getW_Cpu() + "\" form=\"modProd\" required/>");
                    break;
                case "PSU":
                    Psu psu = (Psu) p;
                    out.println("" +
                            "                <label for=\"watt\">Watt</label>" +
                            "                <input type=\"text\" name=\"watt\" id=\"watt\" placeholder=\"Wattaggio\" value=\"" + psu.getN_Watt() + "\" form=\"modProd\" required/>");
                    break;
                case "MOBO":
                    Mobo mobo = (Mobo) p;
                    out.println("" +
                            "                <label for=\"forma\">Formato</label>" +
                            "                <input type=\"number\" name=\"forma\" id=\"forma\" placeholder=\"Form factor\" value=\"" + mobo.getForma() + "\" max=\"2\" min=\"0\" form=\"modProd\" required/>" +
                            "                <label for=\"nRam\">Numero slot RAM</label>" +
                            "                <input type=\"text\" name=\"nRam\" id=\"nRam\" placeholder=\"Numero di slot RAM\" value=\"" + mobo.getN_RAM() + "\" form=\"modProd\" required/>" +
                            "                <label for=\"nUsb\">Numero di porte USB</label>" +
                            "                <input type=\"text\" name=\"nUsb\" id=\"nUsb\" placeholder=\"Numero di porte USB\" value=\"" + mobo.getN_USB() + "\" form=\"modProd\" required/>" +
                            "                <label for=\"nPci\">Numero di slot PCI</label>" +
                            "                <input type=\"text\" name=\"nPci\" id=\"nPci\" placeholder=\"Numero di slot PCI\" value=\"" + mobo.getN_PCI() + "\" form=\"modProd\" required/>");
                    break;
                case "RAM":
                    Ram ram = (Ram) p;
                    out.println("" +
                            "                <label for=\"frequenza\">Frequenza</label>" +
                            "                <input type=\"text\" name=\"frequenza\" id=\"frequenza\" placeholder=\"Frequenza\" value=\"" + ram.getFrequenza() + "\" form=\"modProd\" required/>");
                    break;
                case "HDD":
                    Hdd hdd = (Hdd) p;
                    out.println("" +
                            "                <label for=\"mbs\">Mb/s</label>" +
                            "                <input type=\"text\" name=\"mbs\" id=\"mbs\" placeholder=\"Velocità (in Mb/s)\" class=\"memoriaFisica\" value=\"" + hdd.getMBs() + "\" form=\"modProd\" required/>");
                    break;
                case "SSD":
                    Ssd ssd = (Ssd) p;
                    out.println("" +
                            "                <label for=\"mbs\">Mb/s</label>" +
                            "                <input type=\"text\" name=\"mbs\" id=\"mbs\" placeholder=\"Velocità (in Mb/s)\" class=\"memoriaFisica\" value=\"" + ssd.getMBs() + "\" form=\"modProd\" required/>");
                    break;
                case "GPU":
                    Gpu gpu = (Gpu) p;
                    out.println("" +
                            "                <label for=\"watt\">Watt</label>" +
                            "                <input type=\"text\" name=\"watt\" id=\"watt\" placeholder=\"Wattaggio\" value=\"" + gpu.getWattaggio() + "\" form=\"modProd\" required/>" +
                            "                <label for=\"frequenza\">Frequenza</label>" +
                            "                <input type=\"text\" name=\"frequenza\" id=\"frequenza\" placeholder=\"Frequenza\" value=\"" + gpu.getFrequenza() + "\" form=\"modProd\" required/>" +
                            "                <label for=\"Vram\">VRAM</label>" +
                            "                <input type=\"text\" name=\"Vram\" id=\"Vram\" placeholder=\"VRAM\" value=\"" + gpu.getVRam() + "\" form=\"modProd\" required/>");
                    break;
                default:
                    out.println("Errore");
            }
            out.println("" +
                    "               <label for=\"image\">Immagine</label>" +
                    "               <input type=\"file\" name=\"image\" id=\"url\"/>");

        %>
        <input type="submit" value="Applica modifiche">
    </form>
</div>
</body>
</html>
