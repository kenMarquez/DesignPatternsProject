
package com.devf.designpatternsproject.models;

public class DescuentoAdicional extends DescuentosServicios{
    private int descuentoAdiccional;

    public DescuentoAdicional() {
    }

    public DescuentoAdicional(String categoria, Servicio servicio, int descuento, int descuentoAdiccional) {
        super(categoria, servicio, descuento);
        this.descuentoAdiccional = descuentoAdiccional;
    }
    

    public DescuentoAdicional(int descuentoAdiccional) {
        this.descuentoAdiccional = descuentoAdiccional;
    }
    
    @Override
    public double precioFinal() {
        double descuentoInicial = (double)super.getDescuento() / 100;
        descuentoInicial *= super.getServicio().getCosto();
        descuentoInicial = (super.getServicio().getCosto()) - descuentoInicial;
        double descuentoFinal = (double)descuentoAdiccional / 100;
        descuentoFinal*= descuentoInicial;
        descuentoFinal = descuentoInicial - descuentoFinal;
        return descuentoFinal;
    }  

    public int getDescuentoAdiccional() {
        return descuentoAdiccional;
    }

    public void setDescuentoAdiccional(int descuentoAdiccional) {
        this.descuentoAdiccional = descuentoAdiccional;
    }
    
}
