
package com.devf.designpatternsproject.models;


public class Servicio {
    private String nombreServicio;
    private double costo;
    private String establecimiento;

    public Servicio() {
    }

    public Servicio(String nombreServicio, double costo, String establecimiento) {
        this.nombreServicio = nombreServicio;
        this.costo = costo;
        this.establecimiento = establecimiento;
    }


    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    
    
    
}
