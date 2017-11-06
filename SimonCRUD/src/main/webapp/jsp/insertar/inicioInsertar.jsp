<%-- 
    Document   : inicioInsertar
    Created on : 03-nov-2017, 17:16:32
    Author     : Simon
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page import="java.lang.StringBuilder"%>
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
                    StringBuilder error = (StringBuilder) sesion.getAttribute("insertarError");
                    if (error != null){
                    if (error.charAt(error.length()-1) == ','){
                        error.deleteCharAt(error.length()-1);
                    }
                    }
                    String cadena = "";
                    String anilla = "";
                    String especie = "";
                    String lugar = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                    String fecha = "";
                    Ave ave = (Ave) sesion.getAttribute("insertar");

                    if (ave != null) {
                        anilla = ave.getAnilla();
                        if (anilla == null) {
                            anilla = "";
                        }
                        especie = ave.getEspecie();
                        if (especie == null ){
                            especie = "";
                        }
                        lugar = ave.getLugar();
                        if (lugar == null){
                            lugar = "";
                        }
                        if (ave.getFecha() == null) {
                            fecha = "";
                        } else {
                            fecha = sdf.format(ave.getFecha());
                        }
                    }
                    if (error != null ) {
                        cadena = error.toString();
                    }
                %>

                <h2>Insertar registro de ave</h2>
                <em><%= cadena%></em>
                <form action="<%=request.getContextPath() + "/realizar"%>" method="post">
                    <p>
                        <label for = "anilla"> Anilla </label>
                        <input type="text" name="anilla" id="anilla" value="<%=anilla%>" placeholder="AAA">
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
                        <input type="text" name = "fecha" id = "fecha" value="<%=fecha%>" placeholder="DD-M-AAAA">
                    </p>
                    <div id="boton">
                        <input type="submit" name="enviar" value="Enviar">                       
                    </div>
                    <input type="submit" name="cancelar" value="Cancelar">
                </form>
            </div>
        </div>
</html>
