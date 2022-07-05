<%@ page import="Model.ProdottoDAO" %>
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
<%@ page import="Model.GPU_.Gpu" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 7/4/22
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Pezzo</title>
</head>
<body>

    <%
        String id = request.getParameter("Id");
        Catalogo catalogo = (Catalogo) request.getSession().getAttribute("catalogo");
        Prodotto p = catalogo.doRetriveById(Integer.parseInt(id));
    %>

    <form id="modProd" method="post" action="aggiornaProdotto">
        <table>
            <tr>
                <td>Marca</td>
                <td><input type="text" name="marca" id="marca" value="<%=p.getMarca()%>"/></td>
                </tr>
            <tr>
                <td>Modello</td>
                <td><input type="text" name="modello" id="modello" value="<%=p.getModello()%>"/></td>
            </tr>
            <tr>
                <td>Prezzo</td>
                <td><input type="text" name="prezzo" id="prezzo" value="<%=p.getPrezzo()%>"/></td>
            </tr>
            <tr>
                <td>Quantità</td>
                <td><input type="text" name="quantita" id="quantita" value="<%=p.getQuantità()%>"/></td>
            </tr>
            <tr>
                <td>Descrizione</td>
                <td><input type="text" name="descrizione" id="descrizione" value="<%=p.getDescrizione()%>"/></td>
            </tr>
            <tr>
                <td>Url</td>
                <td><input type="text" name="url" id="url" value="<%=p.getUrl()%>"/></td>
            </tr>
            <tr>
                <td>Disponibilità</td>
                <td><input type="text" name="disponibilita" id="disponibilita" value="<%=p.getQuantità()%>"/></td>
            </tr>


    <%
        switch (p.getTipo()) {
            case "CPU":
                Cpu cpu = (Cpu) p;
                out.println("" +
                        "<tr>" +
                        "                <td>Numero di core</td>" +
                        "                <td><input type=\"text\" name=\"numCore\" id=\"ncore\" value=\" "+cpu.getN_Core()+" \" form=\"modProd\"/></td>" +
                        "            </tr>");
                break;
            case "CASE":
                Case case_ = (Case) p;
                out.println("" +
                        "<tr>" +
                        "                <td>Forma mobo</td>" +
                        "                <td><input type=\"text\" name=\"formaMobo\" id=\"formaMobo\" value=\""+case_.getFormaMobo()+"\" form=\"modProd\" /></td>" +
                        "            </tr>");
                break;
            case "DISSIPATORE":
                Dissipatore dissipatore = (Dissipatore) p;
                out.println(
                        "<tr>" +
                                "                <td>Watt CPU</td>" +
                                "                <td><input type=\"text\" name=\"wCpu\" id=\"wCpu\" value=\""+dissipatore.getW_Cpu()+"\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
                break;
            case "PSU":
                Psu psu = (Psu) p;
                out.println(
                        "<tr>" +
                                "                <td>Watts</td>" +
                                "                <td><input type=\"text\" name=\"watt\" id=\"watt\" value=\""+psu.getN_Watt()+"\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
                break;
            case "MOBO":
                Mobo mobo = (Mobo) p;
                out.println(
                        "<tr>" +
                                "                <td>Form factor</td>" +
                                "                <td><input type=\"text\" name=\"forma\" id=\"forma\" value=\""+mobo.getForma()+"\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero di banchi ram</td>" +
                                "                <td><input type=\"text\" name=\"nRam\" id=\"nRam\" value=\""+mobo.getN_RAM()+"\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero USB</td>" +
                                "                <td><input type=\"text\" name=\"nUsb\" id=\"nUsb\" value=\""+mobo.getN_USB()+"\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero PCI</td>" +
                                "                <td><input type=\"text\" name=\"nPci\" id=\"nPci\" value=\""+mobo.getN_PCI()+"\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
                break;
            case "RAM":
                Ram ram = (Ram) p;
                out.println(
                        "<tr>" +
                                "                <td>Frequenza</td>" +
                                "                <td><input type=\"text\" name=\"frequenza\" id=\"frequenza\" value=\""+ram.getFrequenza()+"\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
                break;
            case "HDD":
                Hdd hdd = (Hdd) p;
                out.println(
                        "<tr>" +
                                "                <td>Velocità in Mb/s </td>" +
                                "                <td><input type=\"text\" name=\"mbs\" id=\"mbs\" value=\""+hdd.getMBs()+"\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
                break;
            case "SSD":
                Ssd ssd = (Ssd) p;
                out.println(
                        "<tr>" +
                                "                <td>Velocità in Mb/s </td>" +
                                "                <td><input type=\"text\" name=\"mbs\" id=\"mbs\" value=\""+ssd.getMBs()+"\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
                break;
            case "GPU":
                Gpu gpu = (Gpu) p;
                out.println(
                        "<tr>" +
                                "                <td>Wattaggio</td>" +
                                "                <td><input type=\"text\" name=\"wGpu\" id=\"wGpu\" value=\""+gpu.getWattaggio()+"\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Frequenza</td>" +
                                "                <td><input type=\"text\" name=\"freqGpu\" id=\"freqGpu\" value=\""+gpu.getFrequenza()+"\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Vram</td>" +
                                "                <td><input type=\"text\" name=\"vRam\" id=\"vRam\" value=\""+gpu.getVRam()+"\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
                break;
            default:
                out.println("Errore");

        }

    %>
        </table>
    </form>
</body>
</html>
