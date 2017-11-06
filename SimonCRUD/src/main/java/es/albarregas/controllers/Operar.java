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
import java.util.ArrayList;
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
@WebServlet(name = "Operar", urlPatterns = {"/operar"})
public class Operar extends HttpServlet {

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
        String url = null;
        Statement sentencia;
        ResultSet resultado;
        String sql = null;
        Connection conexion = Conexion.getConexion();
        Ave ave;
        ArrayList<Ave> aves = null;
        if (request.getParameter("enviar") != null) {
            sql = "SELECT * FROM pajaros";
            try {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(sql);
                aves = new ArrayList<>();
                String opcion = request.getParameter("opcion");
            /**
             * @param opcion contiene el valor elegido en el formulario del index.jsp
             * Con un Switch controlamos la dirección que a la que se va a redirigir dependiendo del valor que
             * devuelva el request.getParameter("opcion");
             */
                switch (opcion) {
                    case "leer":
                        url = "jsp/leer/leer.jsp";
                        break;
                    case "insertar":
                        url = "jsp/insertar/inicioInsertar.jsp";
                        break;
                    case "actualizar":
                        url = "jsp/actualizar/inicioActualizar.jsp";
                        break;
                    case "eliminar":
                        url = "jsp/eliminar/inicioEliminar.jsp";
                        break;
                    default:
                        url = request.getContextPath();
                        break;
                }
                /**
                 * Comprobamos si la consulta a la base de datos devuelve algún valor, en caso afirmativo
                 * se asignan los valores a un objeto Ave, sino comprobamos que opcion sea distinto de "insertar"
                 * si es distinto lanza una SQLException 
                 */
                if (resultado.last()) {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        ave = new Ave();
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString("especie"));
                        ave.setLugar(resultado.getString("lugar"));
                        ave.setFecha(resultado.getDate("fecha"));
                        aves.add(ave);
                    }
                } else {
                    if (!"insertar".equals(opcion)) {
                        throw new SQLException();
                    }
                }
            } catch (SQLException ex) {
                url = request.getContextPath() + "?error=1";
            } catch (NullPointerException ex){
                url = request.getContextPath() + "?error=2";
            }
        }
        sesion.setAttribute("aves", aves);
        Conexion.closeConnection();
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
