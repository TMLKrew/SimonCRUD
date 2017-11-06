<%-- 
    Document   : finInsertar
    Created on : 03-nov-2017, 17:16:45
    Author     : Simon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avistamiento de aves</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/estilo.css"%>">
    </head>
    <body>
        <div id="contenedor">
            <div id="resultado">
                <%
                    HttpSession sesion = request.getSession();
                    int contador = Integer.parseInt(request.getParameter("contador"));
                    sesion.invalidate();
                %>
                <h1>Se han eliminado <%= contador %> registros.</h1>               
                <a href="<%=request.getContextPath()%>">Menú inicial</a>       
            </div>
        </div>
    </body>
</html>
