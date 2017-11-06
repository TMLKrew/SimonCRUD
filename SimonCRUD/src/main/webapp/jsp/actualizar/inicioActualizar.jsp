<%-- 
    Document   : inicioEliminar
    Created on : 03-nov-2017, 17:16:06
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
            aves = (ArrayList) sesion.getAttribute("aves");

        %>
        <div id="contenedor">           
            <div id="mostrar">
                <form action="<%=request.getContextPath() + "/realizar"%>" method="post">
                    <h1>Listado de avistamiento de aves</h1>
                    <% for (Ave a : aves) {
                    %>
                    <p>Anilla : <%= a.getAnilla()%></p>
                    <ul>
                        <li>Especie : <%= a.getEspecie()%></li>
                        <li>Lugar : <%= a.getLugar()%></li>
                        <li>Fecha : <%= a.getFecha()%></li>                      
                        <input type="radio" name="actualizar" value="<%=a.getAnilla()%>" id="actualizar">
                        <label for="actualizar">Marca para modificar registro</label>
                    </ul>
                    <%
                        }
                    %>
                    <input type="submit" name="enviar" value="Enviar">                       
                    <input type="submit" name="cancelar" value="Cancelar">
                </form>
            </div>
        </div>
    </body>
</html>
