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
    <script src="js/modificaPezzo.js"/>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
    <%
        String id = request.getParameter("Id");
        Catalogo catalogo = (Catalogo) request.getSession().getAttribute("catalogo");
        Prodotto p = catalogo.doRetriveById(Integer.parseInt(id));
        Cliente c = (Cliente) request.getSession().getAttribute("cliente");

        System.out.println(p.toString());

        //Se il cliente non è un amministratore non potrebbe essere qua
        if(!c.isAdministrator())
            request.getRequestDispatcher("index.jsp").forward(request, response);

    %>

    <form id="modProd" method="post" action="Aggiorna" onsubmit="return validateProductUpdate()">
        <table>
            <input type="hidden" name="tipo" id="tipo" value="<%=p.getTipo()%>"/>
            <input type="hidden" name="ID" id="ID" value="<%=p.getID()%>"/>
            <tr>
                <td>Marca</td>
                <td><input type="text" name="marca" id="marca" value="<%=p.getMarca()%>" required/></td>
                </tr>
            <tr>
                <td>Modello</td>
                <td><input type="text" name="modello" id="modello" value="<%=p.getModello()%>" required/></td>
            </tr>
            <tr>
                <td>Prezzo</td>
                <td><input type="text" name="prezzo" id="prezzo" value="<%=p.getPrezzo()%>" required/></td>
            </tr>
            <tr>
                <td>Quantità</td>
                <td><input type="text" name="quantita" id="quantita" value="<%=p.getQuantita()%>" required/></td>
            </tr>
            <tr>
                <td>Descrizione</td>
                <td><input type="text" name="desc" id="desc" value="<%=p.getDescrizione()%>" required/></td>
            </tr>
            <tr>
                <td>Url</td>
                <td><input type="text" name="url" id="url" value="<%=p.getUrl()%>" required/></td>
            </tr>
    <%
        switch (p.getTipo()) {
            case "CPU" :
                Cpu cpu = (Cpu) p;
                out.println("" +
                        "<tr>" +
                        "                <td>Numero di core</td>" +
                        "                <td><input type=\"text\" name=\"numCore\" id=\"ncore\" value=\"" + cpu.getN_Core() + "\" form=\"modProd\" required/></td>" +
                        "            </tr>");
            break;
            case "CASE" :
                Case case_ = (Case) p;
                out.println("" +
                        "<tr>" +
                        "                <td>Forma mobo</td>" +
                        "                <td><input type=\"number\" name=\"formaMobo\" id=\"formaMobo\" value=\""+ case_.getFormaMobo() +"\" max=\"2\" min=\"0\" form=\"modProd\" required/></td>" +
                        "            </tr>");
                break;
            case "DISSIPATORE" :
                Dissipatore dissipatore = (Dissipatore) p;
                out.println(
                        "<tr>" +
                                "                <td>Watt CPU</td>" +
                                "                <td><input type=\"text\" name=\"wCpu\" id=\"wCpu\" value=\"" + dissipatore.getW_Cpu() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>"
                );
                break;
            case "PSU" :
                Psu psu = (Psu) p;
                out.println(
                        "<tr>" +
                                "                <td>Watts</td>" +
                                "                <td><input type=\"text\" name=\"watt\" id=\"watt\" value=\"" + psu.getN_Watt() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>"
                );
                break;
            case "MOBO" :
                Mobo mobo = (Mobo) p;
                out.println(
                        "<tr>" +
                                "                <td>Form factor</td>" +
                                "                <td><input type=\"number\" name=\"forma\" id=\"forma\" value=\"" + mobo.getForma() + "\" max=\"2\" min=\"0\" form=\"modProd\" required/></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero di banchi ram</td>" +
                                "                <td><input type=\"text\" name=\"nRam\" id=\"nRam\" value=\"" + mobo.getN_RAM() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero USB</td>" +
                                "                <td><input type=\"text\" name=\"nUsb\" id=\"nUsb\" value=\"" + mobo.getN_USB() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero PCI</td>" +
                                "                <td><input type=\"text\" name=\"nPci\" id=\"nPci\" value=\"" + mobo.getN_PCI() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>"
                );
                break;
            case "RAM" :
                Ram ram = (Ram) p;
                out.println(
                        "<tr>" +
                                "                <td>Frequenza</td>" +
                                "                <td><input type=\"text\" name=\"frequenza\" id=\"frequenza\" value=\"" + ram.getFrequenza() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>"
                );
                break;
            case "HDD" :
                Hdd hdd = (Hdd) p;
                out.println(
                        "<tr>" +
                                "                <td>Velocità in Mb/s </td>" +
                                "                <td><input type=\"text\" name=\"mbs\" id=\"mbs\" class=\"memoriaFisica\" value=\"" + hdd.getMBs() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>"
                );
                break;
            case "SSD" :
                Ssd ssd = (Ssd) p;
                out.println(
                        "<tr>" +
                                "                <td>Velocità in Mb/s </td>" +
                                "                <td><input type=\"text\" name=\"mbs\" id=\"mbs\" class=\"memoriaFisica\" value=\"" + ssd.getMBs() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>"
                );
                break;
            case "GPU" :
                Gpu gpu = (Gpu) p;
                out.println(
                        "<tr>" +
                                "                <td>Wattaggio</td>" +
                                "                <td><input type=\"text\" name=\"wattaggio\" id=\"wGpu\" value=\"" + gpu.getWattaggio() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Frequenza</td>" +
                                "                <td><input type=\"text\" name=\"freqGpu\" id=\"freqGpu\" value=\"" + gpu.getFrequenza() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Vram</td>" +
                                "                <td><input type=\"text\" name=\"vRam\" id=\"vRam\" value=\"" + gpu.getVRam() + "\" form=\"modProd\" required/></td>" +
                                "            </tr>"
                );
                break;
            default : out.println("Errore");
        }

    %>
            <tr>
                <td><input type="submit" value="Applica modifiche" ></td>
            </tr>
        </table>
    </form>
</body>
</html>
