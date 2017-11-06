/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Ave;
import es.albarregas.persistencia.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon
 */
@WebServlet(name = "Realizar", urlPatterns = {"/realizar"})
public class Realizar extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String ruta = "";
        String url = null;
        Ave ave;
        ArrayList<Ave> aves;
        ruta = request.getHeader("Referer");
        /**
         * Comprobamos que el valor del parametro "cancelar" sea distinto de
         * null si se cumple la condición volvemos al index.jsp y cerramos la
         * sesión
         */
        if (request.getParameter("cancelar") != null) {
            url = request.getContextPath();
            sesion.invalidate();
        } else {
            /*
            Con request.getHeader("Referer") obtenemos la ruta por la que llego la petición comprobamos con
            ruta.lastIndexOf("leer/insertar/actualizar/eliminar") que sea distinto de -1, en cada caso entrará
            en esa condición y realizará las distintas funciones
             */
            if (ruta.lastIndexOf("leer") != -1) {
                sesion.invalidate();
                url = request.getContextPath();

            } else /**
             *  Obtenemos los datos del formulario y comprobamos que son correctos, si los campos no son
             * correctos volvemos a inicioInsertar.jsp e indicamos que campos son incorrectos.
             */
            if (ruta.lastIndexOf("insertar") != -1) {
                String anilla;
                String especie;
                String lugar;
                String fecha;
                StringBuilder error = new StringBuilder();
                url = request.getContextPath() + "/jsp/insertar/insertar.jsp";
                ave = new Ave();

                anilla = request.getParameter("anilla");
                especie = request.getParameter("especie");
                lugar = request.getParameter("lugar");
                fecha = request.getParameter("fecha");

                if (anilla != null && anilla.length() == 3) {
                    ave.setAnilla(anilla);
                } else {
                    error.append("anilla,");
                }
                if (!"".equals(especie) && especie.length() <= 20) {
                    ave.setEspecie(especie);
                } else {
                    error.append("especie,");
                }
                if (!"".equals(lugar) && lugar.length() <= 50) {
                    ave.setLugar(lugar);
                } else {
                    error.append("lugar,");
                }
                if (fecha != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                    Date date = null;
                    try {
                        date = sdf.parse(fecha);
                    } catch (ParseException ex) {
                        error.append("fecha");
                    }
                    ave.setFecha(date);
                } else {
                    error.append("fecha");
                }
                if (error.length() > 0) {
                    sesion.setAttribute("insertarError", error);
                    url = request.getContextPath() + "/jsp/insertar/inicioInsertar.jsp";
                }
                sesion.setAttribute("insertar", ave);
            } else if (ruta.lastIndexOf("actualizar") != -1) {
                ave = new Ave();
                url = request.getContextPath() + "/jsp/actualizar/actualizar.jsp";
                String anilla;
                anilla = request.getParameter("actualizar");
                if (anilla != null) {
                    Connection conexion = Conexion.getConexion();
                    Statement sentencia;
                    ResultSet resultado;
                    try {
                        sentencia = conexion.createStatement();
                        String sql;
                        sql = "SELECT * FROM pajaros where anilla = '" + anilla + "'";
                        resultado = sentencia.executeQuery(sql);
                        resultado.next();
                        ave = new Ave();
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString("especie"));
                        ave.setLugar(resultado.getString("lugar"));
                        ave.setFecha(resultado.getDate("fecha"));
                        conexion.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                    sesion.setAttribute("actualizar", ave);
                } else {
                    url = request.getContextPath() + "/jsp/actualizar/finActualizar.jsp?cambio=1";
                }

            } else /**
             * En eliminar realizamos una consulta que nos devuelva un ArrayList
             * con los objetos Ave que queremos eliminar. También comprobamos
             * que no se haya seleccionado ningún campo
             */
            if (ruta.lastIndexOf("eliminar") != -1) {
                url = request.getContextPath() + "/jsp/eliminar/eliminar.jsp";
                Connection conexion = Conexion.getConexion();
                String[] anillas;
                anillas = request.getParameterValues("eliminar");

                Statement sentencia;
                ResultSet resultado;
                aves = new ArrayList<>();
                try {
                    for (int i = 0; i < anillas.length; i++) {
                        try {
                            sentencia = conexion.createStatement();
                            String sql;
                            sql = "SELECT * FROM pajaros where anilla = '" + anillas[i] + "'";
                            resultado = sentencia.executeQuery(sql);
                            while (resultado.next()) {
                                ave = new Ave();
                                ave.setAnilla(resultado.getString("anilla"));
                                ave.setEspecie(resultado.getString("especie"));
                                ave.setLugar(resultado.getString("lugar"));
                                ave.setFecha(resultado.getDate("fecha"));
                                aves.add(ave);
                            }
                        } catch (SQLException ex) {

                        }
                    }
                    sesion.setAttribute("eliminar", aves);
                } catch (NullPointerException ex) {
                    url = request.getContextPath() + "/jsp/eliminar/eliminar.jsp?error=1";
                }
            }
        }
        response.sendRedirect(url);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
