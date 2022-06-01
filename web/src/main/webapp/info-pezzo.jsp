<%@ page import="Model.CPU_.Cpu" %>
<%@ page import="Model.ProdottoDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.CASE_.Case" %>
<%@ page import="Model.DISSIPATORE_.Dissipatore" %>
<%@ page import="Model.GPU_.Gpu" %><%--
  Created by IntelliJ IDEA.
  User: mattiacavaliere
  Date: 30/05/22
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
</head>
<body>
<div>
    <%
        Integer id = Integer.parseInt(request.getParameter("Id"));
        try{
            if(ProdottoDAO.doRetriveById(id) instanceof Cpu){
                Cpu cpu = new Cpu();
                cpu = (Cpu) ProdottoDAO.doRetriveById(id);
                out.println("<div><ul>" +
                        "<li>Marca: " + cpu.getMarca() + "</li>" +
                        "<li>Modello: " + cpu.getModello() + "</li>" +
                        "</ul>" +
                        "<form action=\"addCart\">" +
                        "<input type=\"hidden\" name=\"Id\" value=" + cpu.getID() + ">" +
                        "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                        "</div>");
            }
            else if(ProdottoDAO.doRetriveById(id) instanceof Case){
                Case case_ = new Case();
                case_ = (Case) ProdottoDAO.doRetriveById(id);
                out.println("<div><ul>" +
                        "<li>Marca: " + case_.getMarca() + "</li>" +
                        "<li>Modello: " + case_.getModello() + "</li>" +
                        "</ul>" +
                        "<form action=\"addCart\">" +
                        "<input type=\"hidden\" name=\"Id\" value=" + case_.getID() + ">" +
                        "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                        "</div>");
            }
            else if(ProdottoDAO.doRetriveById(id) instanceof Gpu){
                Gpu gpu= new Gpu();
                gpu = (Gpu) ProdottoDAO.doRetriveById(id);
                out.println("<div><ul>" +
                        "<li>Marca: " + gpu.getMarca() + "</li>" +
                        "<li>Modello: " + gpu.getModello() + "</li>" +
                        "</ul>" +
                        "<form action=\"addCart\">" +
                        "<input type=\"hidden\" name=\"Id\" value=" + gpu.getID() + ">" +
                        "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                        "</div>");
            }
            else if(ProdottoDAO.doRetriveById(id) instanceof Dissipatore){
                Dissipatore dissipatore = new Dissipatore();
                dissipatore = (Dissipatore) ProdottoDAO.doRetriveById(id);
                out.println("<div><ul>" +
                        "<li>Marca: " + dissipatore.getMarca() + "</li>" +
                        "<li>Modello: " + dissipatore.getModello() + "</li>" +
                        "</ul>" +
                        "<form action=\"addCart\">" +
                        "<input type=\"hidden\" name=\"Id\" value=" + dissipatore.getID() + ">" +
                        "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                        "</div>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try{
            Case case_ = new Case();
            case_ = (Case) ProdottoDAO.doRetriveById(id);
            out.println("<div><ul>" +
                    "<li>Marca: " + case_.getMarca() + "</li>" +
                    "<li>Modello: " + case_.getModello() + "</li>" +
                    "</ul>" +
                    "<form action=\"addCart.java\"" +
                    "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                    "</div>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            Dissipatore dissipatore = new Dissipatore();
            dissipatore = (Dissipatore) ProdottoDAO.doRetriveById(id);
            out.println("<div><ul>" +
                    "<li>Marca: " + dissipatore.getMarca() + "</li>" +
                    "<li>Modello: " + dissipatore.getModello() + "</li>" +
                    "</ul>" +
                    "<form action=\"addCart.java\"" +
                    "<input type=\"submit\" id=\"submit\" value=\"Aggiungi al carrello\"></form>" +
                    "</div>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            Gpu gpu = new Gpu();
            gpu = (Gpu) ProdottoDAO.doRetriveById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    %>
</div>
</body>
</html>
