<%@ page import="Model.Cliente_.Cliente" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mattiacavaliere
  Date: 20/05/22
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrello</title>
</head>
<body>
<%
    List<Prodotto> list = (List<Prodotto>) session.getAttribute("carrello");
    out.println("<li>");
    for(int i = 0; i < list.size(); i++) {
        out.println("<ul>" + list.get(i).toString() + "</ul>");
    }
    out.println("</li>");
%>
%>
</body>
</html>
