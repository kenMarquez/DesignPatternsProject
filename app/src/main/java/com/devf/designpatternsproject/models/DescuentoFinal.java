
package com.devf.designpatternsproject.models;

public class DescuentoFinal extends DescuentosServicios{

    public DescuentoFinal() {
        super();
    }
  
    
    public DescuentoFinal(String categoria, Servicio servicio, int descuento) {
        super(categoria, servicio, descuento);
    }
    
    @Override
    public double precioFinal() {
        double descuentoFinal = (double)super.getDescuento() / 100;
        descuentoFinal *= super.getServicio().getCosto();
        return (super.getServicio().getCosto()) - descuentoFinal;
    }
    public String toString(){


        return "Categoria : "+ super.getCategoria() + "\nServicio : "+ super.getServicio().getNombreServicio() +
                "\nDescuentos : "+ super.getDescuento();
    }
}
