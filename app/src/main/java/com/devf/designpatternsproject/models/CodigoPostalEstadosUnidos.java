package com.devf.designpatternsproject.models;

public class CodigoPostalEstadosUnidos extends CodigoPostal {

    public CodigoPostalEstadosUnidos(String codigopostal) {
        super(codigopostal, "#####");
    }

    @Override
    public String getRegExpr() {
        return super.getRegExpr(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCodigoPostal(String codigoPostal) {
        super.setCodigoPostal(codigoPostal); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoPostal() {
        return super.getCodigoPostal(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
