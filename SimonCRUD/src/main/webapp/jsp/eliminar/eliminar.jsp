<%-- 
    Document   : leer
    Created on : 03-nov-2017, 17:17:01
    Author     : Simon
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avistamiento de aves</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/css/estilo.css"%>">
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            ArrayList<Ave> aves;
            aves = (ArrayList) sesion.getAttribute("eliminar");


        %>

        <div id="contenedor">           
            <div id="mostrar">
                <% if (request.getParameter("error") != null) {
                %>
                <h1>No se ha seleccionado ningun registro</h1>
                <a href="<%=request.getContextPath()%>">Menú inicial</a>
                <%
                } else {
                %>
                <h1>Listado de avistamiento de aves</h1>
                <% for (Ave a : aves) {
                %>
                <p>Anilla : <%= a.getAnilla()%></p>
                <ul>
                    <li>Especie : <%= a.getEspecie()%></li>
                    <li>Lugar : <%= a.getLugar()%></li>
                    <li>Fecha : <%= a.getFecha()%></li>
                </ul>
                <%
                    }
                %>
                <p>¿Desea eliminar los siguientes registros?</p>
                <form action="<%=request.getContextPath() + "/concluir"%>" method="post">
                    <input type="submit" name="enviar" value="Enviar">                       
                    <input type="submit" name="cancelar" value="Cancelar">
                </form>
                <%
                    }
                %>

            </div>
        </div>
    </body>
</html>
