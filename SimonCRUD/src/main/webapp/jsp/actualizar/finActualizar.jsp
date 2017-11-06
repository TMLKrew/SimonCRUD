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
                    if (request.getParameter("error") != null) {
                %>
                <h1>Ha ocurrido un error al modificar los datos</h1>
                <%
                } else if (request.getParameter("cambio") != null) {
                %>
                <h1>El registro no ha sido modificado.</h1>
                <%
                } else {
                %>
                <h1>Los datos se han modificado correctamente.</h1>
                <%
                    }
                    sesion.invalidate();
                %>
                <a href="<%=request.getContextPath()%>">Menú inicial</a>       
            </div>
        </div>
    </body>
</html>
