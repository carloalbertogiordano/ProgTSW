<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info personali</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script type = "text/javascript">
        function testCap() {
            const cerca = /[1-9][0-9]{4}/;
            console.log("cap"+cerca.test($("#cap").val()));
            return cerca.test($("#cap").val());
        }

        function testProv() {
            const cerca = /[A-Z]{2}/;
            console.log("provincia"+cerca.test($("#provincia").val()));
            return cerca.test($("#provincia").val());
        }

        function testVia() {
            const cerca = /[a-zA-Z ]{1,100}[0-9]{1,3}/;
            console.log("via"+cerca.test($("#via").val()));
            return cerca.test($("#via").val());
        }

        function testCitta() {
            const cerca = /[a-zA-Z]{1,100}/;
            console.log("citta"+cerca.test($("#citta").val()));
            return cerca.test($("#citta").val());
        }

        function validateInfoSped(){
            return testCap() && testProv() && testVia() && testCitta();
        }

        function validatePassword() {
            const cerca = /[a-zA-Z0-9]{1,130}/;
            console.log("password"+cerca.test($("#password").val()));
            return cerca.test($("#password").val());
        }

        $(document).ready(function() {
            $('#submitModInfoCliente').click(function () {
                let nick = $('#nick').val();
                let tel = $('#tel').val();
                $.ajax({
                    url: 'updateInfoCliente',
                    type: 'POST',
                    data: {
                        input_nick: nick, input_tel: tel
                    },
                });
            });
            $('#submitModInfoSped').click(function(){
                if(validateInfoSped()){
                    let via = $('#via').val();
                    let provincia = $('#provincia').val();
                    let citta = $('#citta').val();
                    let cap = $('#cap').val();
                    $.ajax({
                        url: 'modInfoSpedizione',
                        type: 'POST',
                        data: {
                            input_via: via, input_provincia: provincia, input_citta: citta, input_cap: cap
                        },
                    });
                }
            });
            $('#submitModPass').click(function() {
                if(validatePassword()){
                    let pass = $('#pass').val();
                    $.ajax({
                        url: 'updatePassword',
                        type: 'POST',
                        data: {
                            input_pass: pass
                        },
                    });
                }
            });
        });
    </script>
</head>
<body>
    <%
        if(request.getAttribute("queryUpdateInfoCliente") != null){
            if (!(boolean)request.getAttribute("queryUpdateInfoCliente")) {
                out.println("<div class=\"alert\">");
                out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
                out.println("<strong>Attenzione: </strong> Hai insaerito delle informazioni sbagliate o duplicate." +
                        " Controlla che il nick non sia duplicato" +
                        "corrette.");
                out.println("</div>");
            }
            else{
                out.println("<div class=\"alert\">");
                out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
                out.println("<strong>Aggiornamento avvenuto correttamente</strong>");
                out.println("</div>");
            }
            request.setAttribute("queryUpdateInfoCliente", true);
        }
        if(request.getAttribute("queryUpdatePassword") != null){
            if (!(boolean)request.getAttribute("queryUpdatePassword")) {
                out.println("<div class=\"alert\">");
                out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
                out.println("<strong>Attenzione: </strong> L'aggiornamento della password non è andato a buon fine");
                out.println("</div>");
            }
            else{
                out.println("<div class=\"alert\">");
                out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
                out.println("<strong>L'aggiornamento della password è andato a buon fine</strong>");
                out.println("</div>");
            }
            request.setAttribute("queryUpdatePassword", true);
        }

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        out.println("<h2>Info personali</h2>");
        out.println("<table>"+
            "<tr>"+
            "<td> NickName: </td>"+
            "<td><input type=\"text\" name=\"nick\" id=\"nick\" value=\""+ cliente.getNickname() +"\" > </td>"+
            "</tr>"+
            "<tr>"+
            "<td> Telefono: </td>"+
            "<td><input type=\"text\" name=\"tel\" id=\"tel\" value=\""+ cliente.getTel() +"\" > </td>"+
            "</tr>"+
            "<input type=\"submit\" value=\"Modifica info. personali\" id=\"submitModInfoCliente\">" +
            "</table>");
        out.println("<br>");

        out.println("<h2>Password</h2>");
        out.println("<table>"+
                "<tr>" +
                "<td><input type=\"password\" value=\"**********\" id=\"pass\"/></td>" +
                "</tr>" +
                "<tr>" +
                "<td><input type=\"submit\" value=\"Modifica password\" id=\"submitModPass\"/></td>" +
                "</tr>" +
                "</table>"+
                "</form>");

        out.println("<br><h2>Info spedizione</h2>");
        out.println("<table>" +
                "<div id=\"modIndirizzo\">" +
                "<tr>"+
                "<td> Via: </td>"+
                "<td><input type=\"text\" name=\"via\" id=\"via\" value=\""+ cliente.getVia() +"\" > </td>" +
                "</tr>"+
                "<tr>"+
                "<td> Provincia: </td>"+
                "<td><input type=\"text\" name=\"provincia\" id=\"provincia\" value=\""+ cliente.getProvincia() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> Città: </td>"+
                "<td><input type=\"text\" name=\"citta\" id=\"citta\" value=\""+ cliente.getCitta() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> CAP: </td>"+
                "<td><input type=\"text\" name=\"cap\" id=\"cap\" value=\""+ cliente.getCap() +"\" > </td>"+
                "</tr>"+
                "<input type=\"submit\" value=\"Modifica info. spedizione\" id=\"submitModInfoSped\">" +
                "</table>"+
                "</div>");


    %>


</body>
</html>
