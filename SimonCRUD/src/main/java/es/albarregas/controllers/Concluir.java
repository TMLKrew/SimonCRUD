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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "Concluir", urlPatterns = {"/concluir"})
public class Concluir extends HttpServlet {

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
        Ave ave = null;
        ArrayList<Ave> aves;
        ruta = request.getHeader("Referer");
        if (request.getParameter("cancelar") != null) {
            url = request.getContextPath();
            sesion.invalidate();
        } else {
            /**
             * Al igual que en el Controlador Realizar utilizamos el
             * request.getHeader() y el lastIndexOf() para controlar el flujo de
             * la aplicación. Realizaremos las distintas consultas a la base de
             * datos en este controlador.
             */
            if (ruta.lastIndexOf("insertar") != -1) {

                Connection conexion = Conexion.getConexion();
                Statement stm = null;
                ave = (Ave) sesion.getAttribute("insertar");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                String fecha = sdf.format(ave.getFecha());
                String sql = "INSERT INTO `pajaros`(`anilla`, `especie`, `lugar`, `fecha`) VALUES ('" + ave.getAnilla() + "','" + ave.getEspecie() + "','" + ave.getLugar() + "','" + fecha + "')";
                try {
                    stm = conexion.createStatement();
                    int contador = stm.executeUpdate(sql);
                    url = request.getContextPath() + "/jsp/insertar/finInsertar.jsp";
                    conexion.close();
                } catch (SQLException ex) {
                    url = request.getContextPath() + "/jsp/insertar/finInsertar.jsp?error=1";
                    System.out.println(ex);
                }
            } else /**
             * Comprobamos si los datos han cambiando, si los datos no han
             * cambiando se mostrara en finActualizar.jsp una cadena de texto u
             * otra. Si al modificar los datos algún campo produce un error
             * aparecerá otra cadena en finActualizar.jsp indicando que ha
             * ocurrido un error
             */
            if (ruta.lastIndexOf("actualizar") != -1) {
                Ave nuevaAve = new Ave();
                ave = (Ave) sesion.getAttribute("actualizar");
                nuevaAve.setAnilla(ave.getAnilla());
                nuevaAve.setEspecie(request.getParameter("especie"));
                nuevaAve.setLugar(request.getParameter("lugar"));
                String fecha = request.getParameter("fecha");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                String sql = null;
                Date date = null;
                try {
                    date = sdf.parse(fecha);
                    nuevaAve.setFecha(date);
                } catch (ParseException ex) {
                    url = request.getContextPath() + "/jsp/actualizar/finActualizar.jsp?error=1";
                }

                if (ave.equals(nuevaAve)) {
                    url = request.getContextPath() + "/jsp/actualizar/finActualizar.jsp?cambio=1";
                } else {
                    Connection conexion = Conexion.getConexion();
                    Statement stm = null;
                    ave = (Ave) sesion.getAttribute("actualizar");
                    sdf = new SimpleDateFormat("yyyy-M-dd");
                    try {
                        fecha = sdf.format(nuevaAve.getFecha());
                        sql = "UPDATE `pajaros` SET `especie`='" + nuevaAve.getEspecie() + "',`lugar`='" + nuevaAve.getLugar() + "',`fecha`='" + fecha + "' WHERE anilla = '" + ave.getAnilla() + "'";
                    } catch (NullPointerException ex) {
                        url = request.getContextPath() + "/jsp/actualizar/finActualizar.jsp?error=1";
                    }
                    try {
                        stm = conexion.createStatement();
                        int contador = stm.executeUpdate(sql);
                        url = request.getContextPath() + "/jsp/actualizar/finActualizar.jsp";
                        conexion.close();
                    } catch (SQLException ex) {
                        url = request.getContextPath() + "/jsp/actualizar/finActualizar.jsp?error=1";
                    }

                }

            } else if (ruta.lastIndexOf("eliminar") != -1) {
                int contador = 0;
                Connection conexion = Conexion.getConexion();
                Statement stm = null;
                aves = (ArrayList) sesion.getAttribute("eliminar");
                String sql;
                for (Ave a : aves) {
                    sql = "DELETE FROM `pajaros` WHERE anilla ='" + a.getAnilla() + "'";
                    try {
                        stm = conexion.createStatement();
                        contador += stm.executeUpdate(sql);

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
                }
                url = request.getContextPath() + "/jsp/eliminar/finEliminar.jsp?contador=" + contador;
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
