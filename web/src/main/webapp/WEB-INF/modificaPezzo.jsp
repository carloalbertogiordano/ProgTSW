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
</head>
<body>
<%
    String id = request.getParameter("Id");
    Catalogo catalogo = (Catalogo) request.getSession().getAttribute("catalogo");
    Prodotto p = catalogo.doRetriveById(Integer.parseInt(id));
    Cliente c = (Cliente) request.getSession().getAttribute("cliente");

    //Se il cliente non è un amministratore non potrebbe essere qua
    if (!c.isAdministrator())
        request.getRequestDispatcher("index.jsp").forward(request, response);

%>
<div class="main flex-container">
    <form id="modProd" method="post" action="Aggiorna" onsubmit="return validateProductUpdate()" enctype="multipart/form-data">
        <input type="hidden" name="tipo" id="tipo" value="<%=p.getTipo()%>"/>
        <input type="hidden" name="ID" id="ID" value="<%=p.getID()%>"/>
        <td>Marca</td>
        <input type="text" name="marca" id="marca" value="<%=p.getMarca()%>" required/>
        <td>Modello</td>
        <input type="text" name="modello" id="modello" value="<%=p.getModello()%>" required/>
        <td>Prezzo</td>
        <input type="text" name="prezzo" id="prezzo" value="<%=p.getPrezzo()%>" required/>
        <td>Quantità</td>
        <input type="text" name="quantita" id="quantita" value="<%=p.getQuantita()%>" required/>
        <td>Descrizione</td>
        <input type="text" name="desc" id="desc" value="<%=p.getDescrizione()%>" required/>
        <input type="hidden" name="url" id="url" value="<%=p.getUrl()%>" required/>
        <%
            switch (p.getTipo()) {
                case "CPU":
                    Cpu cpu = (Cpu) p;
                    out.println("" +
                            "                <td>Numero di core</td>" +
                            "                <input type=\"text\" name=\"numCore\" id=\"ncore\" value=\"" + cpu.getN_Core() + "\" form=\"modProd\" required/>" +
                            "                <td>Watts</td>" +
                            "                <input type=\"text\" name=\"watt\" id=\"watt\" value=\"" + cpu.getWattaggio() + "\" form=\"modProd\" required/>" +
                            "                <td>Frequenza</td>" +
                            "                <input type=\"text\" name=\"frequenza\" id=\"frequenza\" value=\"" + cpu.getFrequenza() + "\" form=\"modProd\" required/>");
                    break;
                case "CASE":
                    Case case_ = (Case) p;
                    out.println("" +
                            "                <td>Forma mobo</td>" +
                            "                <input type=\"number\" name=\"forma\" id=\"formaMobo\" value=\"" + case_.getFormaMobo() + "\" max=\"2\" min=\"0\" form=\"modProd\" required/>");
                    break;
                case "DISSIPATORE":
                    Dissipatore dissipatore = (Dissipatore) p;
                    out.println("" +
                                    "                <td>Watt CPU</td>" +
                                    "                <input type=\"text\" name=\"wCpu\" id=\"wCpu\" value=\"" + dissipatore.getW_Cpu() + "\" form=\"modProd\" required/>");
                    break;
                case "PSU":
                    Psu psu = (Psu) p;
                    out.println("" +
                                    "                <td>Watts</td>" +
                                    "                <input type=\"text\" name=\"watt\" id=\"watt\" value=\"" + psu.getN_Watt() + "\" form=\"modProd\" required/>");
                    break;
                case "MOBO":
                    Mobo mobo = (Mobo) p;
                    out.println("" +
                                    "                <td>Form factor</td>" +
                                    "                <input type=\"number\" name=\"forma\" id=\"forma\" value=\"" + mobo.getForma() + "\" max=\"2\" min=\"0\" form=\"modProd\" required/>" +
                                    "                <td>Numero di banchi ram</td>" +
                                    "                <input type=\"text\" name=\"nRam\" id=\"nRam\" value=\"" + mobo.getN_RAM() + "\" form=\"modProd\" required/>" +
                                    "                <td>Numero USB</td>" +
                                    "                <input type=\"text\" name=\"nUsb\" id=\"nUsb\" value=\"" + mobo.getN_USB() + "\" form=\"modProd\" required/>" +
                                    "                <td>Numero PCI</td>" +
                                    "                <input type=\"text\" name=\"nPci\" id=\"nPci\" value=\"" + mobo.getN_PCI() + "\" form=\"modProd\" required/>");
                    break;
                case "RAM":
                    Ram ram = (Ram) p;
                    out.println("" +
                                    "                <td>Frequenza</td>" +
                                    "                <input type=\"text\" name=\"frequenza\" id=\"frequenza\" value=\"" + ram.getFrequenza() + "\" form=\"modProd\" required/>");
                    break;
                case "HDD":
                    Hdd hdd = (Hdd) p;
                    out.println("" +
                                    "                <td>Velocità in Mb/s </td>" +
                                    "                <input type=\"text\" name=\"mbs\" id=\"mbs\" class=\"memoriaFisica\" value=\"" + hdd.getMBs() + "\" form=\"modProd\" required/>");
                    break;
                case "SSD":
                    Ssd ssd = (Ssd) p;
                    out.println("" +
                                    "                <td>Velocità in Mb/s </td>" +
                                    "                <input type=\"text\" name=\"mbs\" id=\"mbs\" class=\"memoriaFisica\" value=\"" + ssd.getMBs() + "\" form=\"modProd\" required/>");
                    break;
                case "GPU":
                    Gpu gpu = (Gpu) p;
                    out.println("" +
                                    "                <td>Wattaggio</td>" +
                                    "                <input type=\"text\" name=\"watt\" id=\"watt\" value=\"" + gpu.getWattaggio() + "\" form=\"modProd\" required/>" +
                                    "                <td>Frequenza</td>" +
                                    "                <input type=\"text\" name=\"frequenza\" id=\"frequenza\" value=\"" + gpu.getFrequenza() + "\" form=\"modProd\" required/>" +
                                    "                <td>Vram</td>" +
                                    "                <input type=\"text\" name=\"Vram\" id=\"Vram\" value=\"" + gpu.getVRam() + "\" form=\"modProd\" required/>");
                    break;
                default:
                    out.println("Errore");
            }
            out.println("" +
                                    "               <td>Immagine</td>" +
                                    "               <input type=\"file\" name=\"image\" id=\"url\"/>");

        %>
        <input type="submit" value="Applica modifiche">
    </form>
</div>
</body>
</html>
