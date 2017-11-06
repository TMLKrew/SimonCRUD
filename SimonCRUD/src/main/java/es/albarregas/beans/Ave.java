/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Simon
 */
public class Ave implements Serializable{
    private String anilla;
    private String especie;
    private String lugar;
    private Date fecha;

    public String getAnilla() {
        return anilla;
    }

    public void setAnilla(String anilla) {
        this.anilla = anilla;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.anilla);
        hash = 83 * hash + Objects.hashCode(this.especie);
        hash = 83 * hash + Objects.hashCode(this.lugar);
        hash = 83 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ave other = (Ave) obj;
        if (!Objects.equals(this.anilla, other.anilla)) {
            return false;
        }
        if (!Objects.equals(this.especie, other.especie)) {
            return false;
        }
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }
    
}
