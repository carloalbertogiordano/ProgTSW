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
<%@ page import="Model.GPU_.Gpu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Pezzo</title>

    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script>
        function validateMarcaAndModello() {
            let marcaAndModello = /[a-zA-Z0-9]{1,30}/;
            return marcaAndModello.test($("#marca")) && marcaAndModello.test($("#modello"));
        }

        function validatePrezzo() {
            let prezzo = /[0-9]{1,10}/;
            return prezzo.test($("#prezzo"));
        }

        function validateQuantita() {
            let quant = /[0-9]{1,1000}/;
            return quant.test($("#quantita"));
        }

        function validateUrl() {
            let url = /[a-zA-Z0-9]{6,250}/;
            return url.test($("#url"));
        }

        function validateDesc() {
            let desc = /[a-zA-Z0-9]{1,10000}/;
            desc.test($("#desc"));
        }

        function validateCompulsoryProduct() {
            //marca modello prezzo quantità url descrizione
            return !!(validateMarcaAndModello() && validatePrezzo() && validateQuantita() && validateUrl() && validateDesc());
        }

        function validateByTipo(tipo) {
            switch (tipo) {
                case "CPU" :
                    let ncore = /[0-9]{1,3}/;
                    return ncore.test($('#ncore'));
                    break;
                case "CASE" :
                    let formaMobo = /[0-2]/;
                    return formaMobo.test($('#formaMobo'));
                    break;
                case "DISSIPATORE" :
                    let wCpu = /[0-9]{1,4}/;
                    return wCpu.test($('#wCpu'));
                    break;
                case "GPU" :
                    let wGpu = /[0-9]{1,4}/;
                    let freqGpu = /^(?!0\d)\d*(\.\d+)?$/mg; //Frequenza espressa in GHz
                    let vRam = /\d{1,3}/;
                    return (wGpu.test($('#wGpu')) && freqGpu.test($('#freqGpu')) && vRam.test($('#vRam')));
                    break;
                case "MOBO" :
                    let forma = /[0-2]/;
                    let n = /[0-9]{1,2}/;
                    return (forma.test($('#forma')) && n.test($('#nRam')) && n.test($('#nPci')) && n.test($('#nUsb')));
                    break;
                case "PSU" :
                    let watt = /[0-9]{2,4}/;
                    return watt.test($('#watt'));
                    break;
                case "RAM" :
                    let frequenza = /^(?!0\d)\d*(\.\d+)?$/mg;
                    return frequenza.test($('#frequenza'));
                    break;
                case "HDD":
                case "SSD":
                    let memoriaFisica = /\d{1,5}/;
                    return memoriaFisica.test($('.memoriaFisica'));
                    break;
                default :
                    return false;
            }


            return false;
        }

        function validateProductUpdate(tipo) {

            return !!(validateCompulsoryProduct() && validateByTipo(tipo));
        }
    </script>
</head>
<body>

    <%
        String id = request.getParameter("Id");
        Catalogo catalogo = (Catalogo) request.getSession().getAttribute("catalogo");
        Prodotto p = catalogo.doRetriveById(Integer.parseInt(id));
    %>

    <form id="modProd" method="post" action="aggiornaProdotto" onsubmit="return validateProductUpdate(<%=p.getTipo()%>)">
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
                <td><input type="text" name="descrizione" id="desc" value="<%=p.getDescrizione()%>"/></td>
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
            case "CPU" -> {
                Cpu cpu = (Cpu) p;
                out.println("" +
                        "<tr>" +
                        "                <td>Numero di core</td>" +
                        "                <td><input type=\"text\" name=\"numCore\" id=\"ncore\" value=\" " + cpu.getN_Core() + " \" form=\"modProd\"/></td>" +
                        "            </tr>");
            }
            case "CASE" -> {
                Case case_ = (Case) p;
                out.println("" +
                        "<tr>" +
                        "                <td>Forma mobo</td>" +
                        "                <td><input type=\"text\" name=\"formaMobo\" id=\"formaMobo\" value=\"" + case_.getFormaMobo() + "\" form=\"modProd\" /></td>" +
                        "            </tr>");
            }
            case "DISSIPATORE" -> {
                Dissipatore dissipatore = (Dissipatore) p;
                out.println(
                        "<tr>" +
                                "                <td>Watt CPU</td>" +
                                "                <td><input type=\"text\" name=\"wCpu\" id=\"wCpu\" value=\"" + dissipatore.getW_Cpu() + "\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
            }
            case "PSU" -> {
                Psu psu = (Psu) p;
                out.println(
                        "<tr>" +
                                "                <td>Watts</td>" +
                                "                <td><input type=\"text\" name=\"watt\" id=\"watt\" value=\"" + psu.getN_Watt() + "\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
            }
            case "MOBO" -> {
                Mobo mobo = (Mobo) p;
                out.println(
                        "<tr>" +
                                "                <td>Form factor</td>" +
                                "                <td><input type=\"text\" name=\"forma\" id=\"forma\" value=\"" + mobo.getForma() + "\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero di banchi ram</td>" +
                                "                <td><input type=\"text\" name=\"nRam\" id=\"nRam\" value=\"" + mobo.getN_RAM() + "\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero USB</td>" +
                                "                <td><input type=\"text\" name=\"nUsb\" id=\"nUsb\" value=\"" + mobo.getN_USB() + "\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Numero PCI</td>" +
                                "                <td><input type=\"text\" name=\"nPci\" id=\"nPci\" value=\"" + mobo.getN_PCI() + "\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
            }
            case "RAM" -> {
                Ram ram = (Ram) p;
                out.println(
                        "<tr>" +
                                "                <td>Frequenza</td>" +
                                "                <td><input type=\"text\" name=\"frequenza\" id=\"frequenza\" value=\"" + ram.getFrequenza() + "\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
            }
            case "HDD" -> {
                Hdd hdd = (Hdd) p;
                out.println(
                        "<tr>" +
                                "                <td>Velocità in Mb/s </td>" +
                                "                <td><input type=\"text\" name=\"mbs\" id=\"mbs\" class=\"memoriaFisica\" value=\"" + hdd.getMBs() + "\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
            }
            case "SSD" -> {
                Ssd ssd = (Ssd) p;
                out.println(
                        "<tr>" +
                                "                <td>Velocità in Mb/s </td>" +
                                "                <td><input type=\"text\" name=\"mbs\" id=\"mbs\" class=\"memoriaFisica\" value=\"" + ssd.getMBs() + "\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
            }
            case "GPU" -> {
                Gpu gpu = (Gpu) p;
                out.println(
                        "<tr>" +
                                "                <td>Wattaggio</td>" +
                                "                <td><input type=\"text\" name=\"wGpu\" id=\"wGpu\" value=\"" + gpu.getWattaggio() + "\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Frequenza</td>" +
                                "                <td><input type=\"text\" name=\"freqGpu\" id=\"freqGpu\" value=\"" + gpu.getFrequenza() + "\" form=\"modProd\" /></td>" +
                                "            </tr>" +
                                "<tr>" +
                                "                <td>Vram</td>" +
                                "                <td><input type=\"text\" name=\"vRam\" id=\"vRam\" value=\"" + gpu.getVRam() + "\" form=\"modProd\" /></td>" +
                                "            </tr>"
                );
            }
            default -> out.println("Errore");
        }

    %>
            <tr>
                <td><input type="submit" value="Applica modifiche" ></td>
            </tr>
        </table>
    </form>
</body>
</html>
