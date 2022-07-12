        <%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Carrello_.Carrello" %>
<%@ page import="Model.Carrello_.CarrelloDAO" %>
<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strorico ordini</title>
</head>
<body>
<h1>Ecco lo storico dei tuoi ordini: </h1>
<%
    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
    ArrayList<Carrello> ordini;
    try {
        ordini = CarrelloDAO.doRetriveStorico(cliente.getMail());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    if(ordini == null) out.println("Non hai ancora fatto acquisti");

    for (Carrello c : ordini) {
        out.println(c.toString()+"<br>");
    }

%>


</body>
</html>
