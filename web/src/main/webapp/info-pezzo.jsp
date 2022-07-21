<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.GPU_.Gpu" %>
<%@ page import="Model.CATALOGO_.Catalogo" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="static com.sun.tools.javac.jvm.ByteCodes.illegal" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="Model.MOBO_.Mobo" %>
<%@ page import="Model.PSU_.Psu" %>
<%@ page import="Model.RAM_.Ram" %>
<%@ page import="Model.Archiviazione_.HDD_.Hdd" %>
<%@ page import="Model.Archiviazione_.SDD_.Ssd" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Info</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/info-pezzo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/d757446473.js" crossorigin="anonymous"></script>
    <script src="js/navbar.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<div><%
    Cliente c = (Cliente) session.getAttribute("cliente");
%>
    <div class="header">
        <div class="flex-container topnav" id="topnav">
            <div class="flex-left-item logo">
                <a href="index.jsp"><img src="Images/PCBuilder-logo.png" id="header-logo"></a>
            </div>
            <a href="javascript:void(0);" class="right-buttons burger" onclick="dropDownBurger()">&#9776;</a>
            <div class="nav flex-right-item" id="nav-list">
                <ul class="flex-container">
                    <li><a href="index.jsp" class="active">Home</a></li>
                    <li><a href="Catalogo.jsp">Catalogo</a></li>
                    <li><a href="#">Chi siamo</a></li>
                    <li class="empty-flex-field" id="emptyFlexField"></li>
                    <li class="right-buttons"><a href="carrello.jsp" class="carrello-link"><i
                            class="fa-solid fa-cart-shopping"></i></a></li>
                    <%
                        if (c != null) {
                            out.println("<li class=\"right-buttons\">" +
                                    "<div class=\"dropdown\">" +
                                    "<button class=\"dropbtn\" onclick=\"dropdownMenu()\">" +
                                    "<i class=\"fa-solid fa-circle-user\"></i>Profilo\n" +
                                    "</button>" +
                                    "<div class=\"dropdown-content\" id=\"myDropdown\">\n" +
                                    "<a href=\"modInfoCliente\">Il mio profilo</a>\n" +
                                    "<a href=\"storicoOrdini\">I miei ordini</a>\n" +
                                    "<a href=\"Logout\" class=\"logout-link\">LogOut</a>\n" +
                                    "</div>" +
                                    "</div>" +
                                    "</li>");
                        }
                    %>
                    <%
                        if (c == null) {
                            out.println("<li class=\"right-buttons\"><a href=\"login.jsp\">Login</a></li>");
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
    <!--<%
        int id = Integer.parseInt(request.getParameter("Id"));
        String type = request.getParameter("type");
        Catalogo catalogo = (Catalogo) session.getAttribute("catalogo");
        Prodotto p = catalogo.doRetriveById(id);

        if(p != null) {
            out.println("<div><ul>" +
                    "<li>Marca: " + p.getMarca() + "</li>" +
                    "<li>Modello: " + p.getModello() + "</li>" +
                    "</ul>" +
                    "<form action=\"addCart\" id=\"buy\">" +
                    "<input type=\"hidden\" name=\"Id\" value=" + p.getID() + ">");
                    if(p.getQuantita() > 0) {
                        out.println(
                                "<input type=\"number\" id=\"quantity\" name=\"quantity\" min=\"1\" max=\"" + p.getQuantita() + "\">" +
                                        "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                                        "</div>");
                    }
                    else{
                        out.println("Ci dispiace, questo prodotto non è più disponibile.</form></div>");
                    }
        }
        else{
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request,response);
        }
    %>-->

    <div class="main">
        <div class="first-row flex-container">
            <div class="product-header flex-container">
                <h2><%out.print(p.getMarca() + " " + p.getModello());%></h2><br>
            </div>
            <div class="flex-left-item image-box">
                <div class="img-container flex-container">
                    <!--<img src="<%out.print(p.getUrl() + "/1.png");%>">-->
                    <img src="Images/logo.png" class="product-image">
                </div>
            </div>
            <div class="flex-right-item info-box">
                <div class="info-container flex-container">
                    <h3>Specifiche</h3>
                    <div class="spec">
                        <ul>
                            <li>
                                <%out.println("Marca: " + p.getMarca());%>
                            </li>
                            <li>
                                <%out.println("Modello: " + p.getModello());%>
                            </li>
                            <%
                                switch (type) {
                                    case "CPU":
                                        Cpu cpu = (Cpu) p;
                                        out.println("<li>Core: " + cpu.getN_Core() + "</li>" +
                                                "<li>Frequenza: " + cpu.getFrequenza() + "</li>" +
                                                "<li>Wattaggio: " + cpu.getWattaggio() + "</li>");
                                        break;
                                    case "Case":
                                        Case case_ = (Case) p;
                                        String formaMobo = "";
                                        if(case_.getFormaMobo()==0){
                                            formaMobo = "Mini ATX";
                                        } else if (case_.getFormaMobo() == 1){
                                            formaMobo = "ATX";
                                        } else {
                                            formaMobo = "ETX";
                                        }
                                        out.println("<li>Core: " + formaMobo + "</li>");
                                        break;
                                    case "Dissipatore":
                                        Dissipatore dissipatore = (Dissipatore) p;
                                        out.println("<li>Massimo wattaggio della cpu: " + dissipatore.getW_Cpu() + "</li>");
                                        break;
                                    case "GPU":
                                        Gpu gpu = (Gpu) p;
                                        out.println("<li>Wattaggio: " + gpu.getWattaggio() + "</li>" +
                                                "<li>Frequenza: " + gpu.getFrequenza() + "</li>" +
                                                "<li>VRAM: " + gpu.getVRam() + "</li>");
                                        break;
                                    case "Mobo":
                                        Mobo mobo = (Mobo) p;
                                        String forma = "";
                                        if(mobo.getForma()==0){
                                            forma = "Mini ATX";
                                        } else if (mobo.getForma() == 1){
                                            forma = "ATX";
                                        } else {
                                            forma = "ETX";
                                        }
                                        out.println("<li>Formato: " + forma + "</li>" +
                                                "<li>Numero di slot per RAM: " + mobo.getN_RAM() + "</li>" +
                                                "<li>Numero di porte USB: " + mobo.getN_USB() + "</li>" +
                                                "<li>Numero di porte PCI: " + mobo.getN_PCI() + "</li>");
                                        break;
                                    case "Psu":
                                        Psu psu = (Psu) p;
                                        out.println("<li>Wattaggio: " + psu.getN_Watt() + "</li>");
                                        break;
                                    case "Ram":
                                        Ram ram = (Ram) p;
                                        out.println("<li>Frequenza: " + ram.getFrequenza() + "</li>");
                                        break;
                                    case "Hdd":
                                        Hdd hdd = (Hdd) p;
                                        out.println("<li>MB per secondo: " + hdd.getMBs() + "</li>");
                                        break;
                                    case "Sss":
                                        Ssd ssd = (Ssd) p;
                                        out.println("<li>MB per secondo: " + ssd.getMBs() + "</li>");
                                        break;
                                }
                            %>
                            <li>
                                <div class="price">
                                    <%out.println(p.getPrezzo());%>
                                </div>
                            </li>
                            <li>
                                <div class="add-cart-button">
                                    <a href="<%out.print("addCart?Id=" + p.getID() + "&quantity=" + 1);%>"><i class="fa fa-shopping-cart"></i>Aggiungi al carrello</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="second-row flex-container">
            <div class="product-description-div flex-container">
                <p class="product-description"><%
                    out.print(p.getDescrizione());
                    %>/p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
