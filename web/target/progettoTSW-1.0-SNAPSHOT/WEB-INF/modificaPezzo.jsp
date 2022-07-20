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
    <script>
        function validateMarcaAndModello() {
            let marcaAndModello = /[a-zA-Z0-9]{1,30}/;
            if(!(marcaAndModello.test($("#marca").val()))){
                console.error("Err: Marca non valida");
            }
            if(!(marcaAndModello.test($("#modello").val()))){
                console.error("Err: Modello non valido");
            }
            return marcaAndModello.test($("#marca").val()) && marcaAndModello.test($("#modello").val());
        }

        function validatePrezzo() {
            let prezzo = /^(?!0\d)\d*(\.\d+)?$/;
            console.log($("#prezzo").val());
            if(!(prezzo.test($("#prezzo").val()))){
                console.error("Err: Prezzo non valida");
            }
            return prezzo.test($("#prezzo").val());
        }

        function validateQuantita() {
            let quant = /[0-9]{0,3}/;
            if(!(quant.test($("#quantita").val()))){
                console.error("Err: Quantita non valida");
            }
            return quant.test($("#quantita").val());
        }

        function validateUrl() {
            let url = /[a-zA-Z0-9].{6,250}/;
            if(!(url.test($("#url").val()))){
                console.error("Err: Url non valida");
            }
            return url.test($("#url").val());
        }

        function validateDesc() {
            let desc = /[a-zA-Z0-9].{1,10000}/;
            if(!(desc.test($("#desc").val()))){
                console.error("Err: Descrizione non valida");
            }
            return desc.test($("#desc").val());
        }

        function validateCompulsoryProduct() {
            return (validateMarcaAndModello() && validatePrezzo() && validateQuantita() && validateUrl() && validateDesc());
        }

        function validateByTipo(tipo) {
            switch (tipo) {
                case "CPU" :
                    const ncore = new RegExp('[1-9][0-9]{0,1}');
                    let str = document.getElementById("ncore").value;
                    return ncore.test(str);
                    break;
                case "CASE" :
                    let formaMobo = /[0-2]$/;
                    let strFormaMobo = document.getElementById("formaMobo").value;
                    return formaMobo.test(strFormaMobo);
                    break;
                case "DISSIPATORE" :
                    let wCpu = /[1-9][0-9]{0,3}/;
                    let strwCpu = document.getElementById("wCpu").value;
                    return wCpu.test(strwCpu);
                    break;
                case "GPU" :
                    let wGpu = /[1-9][0-9]{0,3}/;
                    let freqGpu = /^(?!0\d)\d*(\.\d+)?$/mg; //Frequenza espressa in GHz
                    let vRam = /[1-9][0-9]{0,2}/;
                    let strwGpu = document.getElementById("wGpu").value;
                    let strFreqGpu = document.getElementById("freqGpu").value;
                    let strvRam = document.getElementById("vRam").value;
                    return (wGpu.test(strwGpu) && freqGpu.test(strFreqGpu) && vRam.test(strvRam));
                    break;
                case "MOBO" :
                    let forma = /[0-2]/;
                    let n = /[1-9][0-9]{0,2}/;
                    let strForma = document.getElementById("forma").value;
                    let strnRam = document.getElementById("nRam").value;
                    let strnPci = document.getElementById("nPci").value;
                    let strnUsb = document.getElementById("nUsb").value;
                    return (forma.test(strForma) && n.test(strnRam) && n.test(strnPci) && n.test(strnUsb));
                    break;
                case "PSU" :
                    let watt = /[1-9][0-9]{1,4}/;
                    let strWatt = document.getElementById("watt").value;
                    return watt.test($('#watt'));
                    break;
                case "RAM" :
                    let frequenza = /^(?!0\d)\d*(\.\d+)?$/mg;
                    let strFrequenza = document.getElementById("frequenza").value;
                    return frequenza.test(strFrequenza);
                    break;
                case "HDD":
                case "SSD":
                    let mbps = /\d{1,100}/;
                    let strMbps = document.getElementById("mbps").value;
                    return mbps.test(strMbps);
                    break;
                default :
                    return false;
            }


            return false;
        }

        function validateProductUpdate() {
            let tipo = $('#tipo').val();
            console.log(tipo);

            if(!(validateByTipo(tipo))){
                console.error("Err: Validazione su tipo non valid");
            }

            if(!(validateCompulsoryProduct()))
                console.error("ERR: Validazione prodotti obbligatori non valida");

            if (validateCompulsoryProduct() && validateByTipo(tipo))
                return true;
            console.error("Err: campi non compilati correttamente");
            return false;
        }
    </script>
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
