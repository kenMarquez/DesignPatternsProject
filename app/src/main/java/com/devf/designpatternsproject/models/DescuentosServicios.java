package com.devf.designpatternsproject.models;

public abstract class DescuentosServicios{
    private String categoria;
    private Servicio servicio;
    private int descuento;

    public DescuentosServicios(String categoria, Servicio servicio, int descuento) {
        this.categoria = categoria;
        this.servicio = servicio;
        this.descuento = descuento;
    }

    public DescuentosServicios() {
        
    }
    /**
     * Precio final del servicio
     * @return precio final double
     */
    public abstract double precioFinal();
    
    public double precioOriginal(){
        return servicio.getCosto();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    
    
}
