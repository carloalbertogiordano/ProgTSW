<%@ page import="Model.Cliente_.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info personali</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
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
            if(testCap() && testProv() && testVia() && testCitta()){
                return true;
            }
            alert("Info spedizione non inserite correttamente. Assicurati che tutti i campi siano stati rispettati");
            return false;
        }

        function testNickname() {
            let reNick = /[a-zA-Z0-9]{1,70}/ ;
            console.log("nick"+reNick.test($("#nick").val()));
            return reNick.test($("#nick").val());
        }

        function testTel() {
            const cerca = /[0-9]{2}[0-9]{10}/;
            console.log("tel"+cerca.test($("#tel").val())+$("#tel").val());
            return cerca.test($("#tel").val());
        }

        function validateInfoPers(){
            if(testNickname() && testTel()){
                return true;
            }
            alert("Info presonali non inserite correttamente. assicurati di rispettare tutti i campi");
            return false;
        }

        function validatePassword() {
            const cerca = /[a-zA-Z0-9]{1,130}/;
            console.log("password"+cerca.test($("#pass").val()));
            if(cerca.test($("#pass").val())){
                return true;
            }
            alert("Nuova password non inserita correttamente, assicurati che a-zA-Z0-9]{1,130}")
            return false;
        }

        $(document).ready(function() {
            let btnInfoCliente = $('#submitModInfoCliente');
            let btnInfoSpedCliente = $('#submitModInfoSped');
            let btnPass = $('#submitModPass');

            btnInfoCliente.click(function () {
                let nick = $('#nick').val();
                let tel = $('#tel').val();
                $.ajax({
                    url: 'updateInfoCliente',
                    type: 'POST',
                    data: {
                        input_nick: nick, input_tel: tel
                    },
                    success: function (response){
                        alert("Informazioni inserite correttamente");
                        btnInfoCliente.attr('disabled', true);
                    }
                });
            });
            btnInfoSpedCliente.click(function(){
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
                        success: function (response){
                            alert("Informazioni inserite correttamente");
                            btnInfoSpedCliente.attr('disabled', true);
                        }
                    });
                }
            });
            btnPass.click(function() {
                if(validatePassword()){
                    let pass = $('#pass').val();
                    $.ajax({
                        url: 'updatePassword',
                        type: 'POST',
                        data: {
                            input_pass: pass
                        },
                        success: function (response){
                            alert("Informazioni inserite correttamente");
                            btnPass.attr('disabled', true);
                        }
                    });
                }
            });
            //Blur deli input non modificati
            btnInfoCliente.attr('disabled', true);
            btnInfoSpedCliente.attr('disabled', true);
            btnPass.attr('disabled', true);
            //Se tento di modificare uno dei campi rendo il bottone visibile
            $('.infoPers').keyup(function(){
                btnInfoCliente.attr('disabled', false);
            });
            $('.infoSped').keyup(function(){
                btnInfoSpedCliente.attr('disabled', false);
            });
            $('.pass').click(function(){
                $('#pass').val('');
                btnPass.attr('disabled', false);
            });
        });
    </script>
</head>
<body>
    <%

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        out.println("<h2>Info personali</h2>");
        out.println("<table>"+
            "<tr>"+
            "<td> NickName: </td>"+
            "<td><input type=\"text\" name=\"nick\" id=\"nick\" class=\"infoPers\" value=\""+ cliente.getNickname() +"\" > </td>"+
            "</tr>"+
            "<tr>"+
            "<td> Telefono: </td>"+
            "<td><input type=\"text\" name=\"tel\" id=\"tel\" class=\"infoPers\" value=\""+ cliente.getTel() +"\" > </td>"+
            "</tr>"+
            "<input type=\"submit\" value=\"Modifica info. personali\" id=\"submitModInfoCliente\">" +
            "</table>");
        out.println("<br>");

        out.println("<h2>Password</h2>");
        out.println("<table>"+
                "<tr>" +
                "<td><input type=\"password\" value=\"**********\" id=\"pass\" class=\"pass\" /></td>" +
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
                "<td><input type=\"text\" name=\"via\" id=\"via\" class=\"infoSped\" value=\""+ cliente.getVia() +"\" > </td>" +
                "</tr>"+
                "<tr>"+
                "<td> Provincia: </td>"+
                "<td><input type=\"text\" name=\"provincia\" id=\"provincia\" class=\"infoSped\" value=\""+ cliente.getProvincia() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> Città: </td>"+
                "<td><input type=\"text\" name=\"citta\" id=\"citta\" class=\"infoSped\" value=\""+ cliente.getCitta() +"\" > </td>"+
                "</tr>"+
                "<tr>"+
                "<td> CAP: </td>"+
                "<td><input type=\"text\" name=\"cap\" id=\"cap\" class=\"infoSped\" value=\""+ cliente.getCap() +"\" > </td>"+
                "</tr>"+
                "<input type=\"submit\" value=\"Modifica info. spedizione\" id=\"submitModInfoSped\">" +
                "</table>"+
                "</div>");


    %>


</body>
</html>
