
package com.devf.designpatternsproject.models;




public class Colonia {
    private String nombre;
    private CodigoPostal codigoPostal; 
    private String Pais;
    
    public Colonia(String nombre, CodigoPostal codigoPostal) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CodigoPostal getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(CodigoPostal codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    @Override
    public String toString() {
        return "Nombre :"+this.nombre + "\nPais : "+this.Pais+"\nCodigoPostal :"+this.getCodigoPostal().getCodigoPostal();
    }
    
    
    
}
