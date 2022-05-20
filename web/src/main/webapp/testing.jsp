<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.*" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 5/20/22
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>TESTING:</h1>

<%
    CpuDAO cDAO = new CpuDAO();
    try {
        ArrayList<Cpu> cpus = cDAO.doRetriveByType();
        for (Cpu cpu : cpus) {
            out.println("<p>CPU: " + cpu.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    MoboDAO mDAO = new MoboDAO();
    try {
        ArrayList<Mobo> mobos = mDAO.doRetriveByType();
        for (Mobo mobo : mobos) {
            out.println("<p>Mobo: " + mobo.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    RamDAO rDAO = new RamDAO();
    try {
        ArrayList<Ram> rams = rDAO.doRetriveByType();
        for (Ram ram : rams) {
            out.println("<p>Ram: " + ram.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    out.println("<br>");
    out.println("<br>");
    out.println("<br>");

    HddDAO hDAO = new HddDAO();
    try {
        ArrayList<Hdd> hdds = hDAO.doRetriveByType();
        for (Hdd hdd : hdds) {
            out.println("<p>Hdd: " + hdd.toString() + "</p>");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }




%>

</body>
</html>
