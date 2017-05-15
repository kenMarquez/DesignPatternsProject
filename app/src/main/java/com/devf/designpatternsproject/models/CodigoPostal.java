package com.devf.designpatternsproject.models;

public abstract class CodigoPostal {
    /**
     * codigoPostal 
     * regExp Expresion regular que se simboliza el formato de un codigo postal 
     * los valores que puede tomar son los siguientes
     * #,$,[a-z,A-Z],[0-9] siendo estos numero, cualquier letra del abecedario,una letra del abecedario en el rango 
     * especificado y un numero en el rengo especificado respectivamente.
     * 
     */
    private String codigoPostal;
    private String regExpr;
    /**
     * Metodo constructor  de la clase abastracta codigo postal.
     * @param codigoPostal  cadena que el usuario ingresa para verificar si pertenece a una entidad.
     */
    public CodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    /**
     * Constructor para las clases que heredan comportamiento de esta clase. 
     * @param codigoPostal
     * @param regExpr 
     */
    public CodigoPostal(String codigoPostal, String regExpr) {
        this.codigoPostal = codigoPostal;
        this.regExpr = regExpr;
    }
    /**
     * Metodo que regresa un codigo postal que haya sido verificado anteriormente.
     * @return un codigo postal valido
     */
    
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    /**
     * Metodo que regresa el patron en forma de expresion regular que conforma el codigo postal de una entidad en cuestion
     * @return formato de codigo postal de una entidad en formato de expresion regular 
     */
    public String getRegExpr() {
        return regExpr;
    }
    
}
