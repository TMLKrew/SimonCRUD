<%-- 
    Document   : insertar
    Created on : 03-nov-2017, 17:16:39
    Author     : Simon
--%>

<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avistamiento de aves</title>
        <link rel="stylesheet" type="text/css" href=<%= request.getContextPath() + "/css/estilo.css"%>>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            Ave ave = (Ave) sesion.getAttribute("insertar");
        %>
        <div id="contenedor">           
            <div id="cuestionario">
                <h2>Los datos ingresados son:</h2>
                <ul>
                    <li>Anilla : <%=ave.getAnilla()%></li>
                    <li>Especie : <%=ave.getEspecie()%></li>
                    <li>Lugar : <%=ave.getLugar()%></li>
                    <li>Fecha : <%=ave.getFecha()%></li>
                </ul>
                <form action="<%=request.getContextPath() + "/concluir"%>">
                    <div id="boton">
                        <input type="submit" name="enviar" value="Enviar">      
                    </div>                
                    <input type="submit" name="cancelar" value="Cancelar">
                </form>
            </div>
        </div>
    </body>
</html>
