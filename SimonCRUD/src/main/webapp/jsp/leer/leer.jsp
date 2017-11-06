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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()+"/css/estilo.css"%>">
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            ArrayList<Ave> aves;
            aves = (ArrayList) sesion.getAttribute("aves");

        %>

        <div id="contenedor">           
            <div id="mostrar">
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

                <a href="<%= request.getContextPath()+"/realizar"%>">Menú inicial</a>
            </div>
        </div>
    </body>
</html>
