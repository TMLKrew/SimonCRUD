<%-- 
    Document   : index
    Created on : 03-nov-2017, 17:07:33
    Author     : Simon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avistamiento de aves</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css">
    </head>
    <body>
        <% String error = request.getParameter("error");
            String cadena = "";
            if (error != null) {
                cadena = "No se han encontrado registros";
            }
        %>
        <div id="contenedor">
            <div id="cabecera">
                <h1>Bienvenido a los registros de avistamientos de aves</h1>
            </div>
            <div id="formulario">
                <h2>¿Qué desea hacer?</h2>
                <em><%= cadena%></em>
                <form action="operar" method="post">
                    <ul type="none">
                        <li> <input type="radio" name="opcion" value="leer" checked="checked"> Leer todos los registros.</li>
                        <li> <input type="radio" name="opcion" value="insertar"> Insertar un registro.</li>
                        <li> <input type="radio" name="opcion" value="actualizar"> Actualizar un registro.</li>
                        <li> <input type="radio" name="opcion" value="eliminar"> Eliminar uno o varios registros.</li>
                    </ul>
                    <div id="boton">
                        <input type="submit" name="enviar" value="Enviar">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
