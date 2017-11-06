<%-- 
    Document   : inicioInsertar
    Created on : 03-nov-2017, 17:16:32
    Author     : Simon
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avistamiento de aves</title>
        <link rel="stylesheet" text="text/css" href=<%=request.getContextPath() + "/css/estilo.css"%>>
    </head>
    <body>
        <div id="contenedor">           
            <div id="cuestionario">
                <%
                    HttpSession sesion = request.getSession();
                    Ave ave = (Ave) sesion.getAttribute("actualizar");
                    String cadena = "";
                    String anilla = "";
                    String especie = "";
                    String lugar = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                    String fecha = "";

                    anilla = ave.getAnilla();
                    especie = ave.getEspecie();
                    lugar = ave.getLugar();
                       if (ave.getFecha() == null) {
                            fecha = "";
                        } else {
                            fecha = sdf.format(ave.getFecha());
                        }


                %>

                <h2>Insertar registro de ave</h2>
                <em><%= cadena%></em>
                <form action="<%=request.getContextPath() + "/concluir"%>" method="post">
                    <p>
                        <label for = "anilla"> Anilla </label>
                        <input type="text" name="anilla" id="anilla" value="<%=anilla%>" disabled="disabled">
                    </p>
                    <p>
                        <label for = "especie"> Especie </label>
                        <input type="text" name="especie" id="especie" value="<%=especie%>">
                    </p>
                    <p>
                        <label for = "lugar"> Lugar </label>
                        <input type= "text" name="lugar" id="lugar" value="<%=lugar%>">
                    </p>
                    <p><label for = "fecha">Fecha</label>
                        <input type="text" name = "fecha" id = "fecha" value="<%=fecha%>">
                    </p>
                    <div id="boton">
                        <input type="submit" name="enviar" value="Enviar">                       
                    </div>
                    <input type="submit" name="cancelar" value="Cancelar">
                </form>
            </div>
        </div>
</html>
