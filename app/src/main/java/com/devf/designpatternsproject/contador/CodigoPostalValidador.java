package com.devf.designpatternsproject.contador;


import com.devf.designpatternsproject.excepciones.CodigoPostalExcepcion;
import com.devf.designpatternsproject.models.CodigoPostal;

public class CodigoPostalValidador implements Validar {

    @Override
    public boolean valida(CodigoPostal codigoPostal) throws CodigoPostalExcepcion {
        if (codigoPostal == null) {
            throw new CodigoPostalExcepcion("Entidad no registrada");
        }
        if (codigoPostal.getRegExpr() == null) {
            throw new CodigoPostalExcepcion("Entidad no tiene codigo postal asosiada");
        }
        if (codigoPostal.getCodigoPostal() == null) {
            throw new CodigoPostalExcepcion("Codigo postal no proporcionado por el usuario");
        } else {
            String expresionRegular = codigoPostal.getRegExpr().toUpperCase();
            String codigo = codigoPostal.getCodigoPostal().trim().toUpperCase();
            if (!(expresionRegular.length() == codigo.length())) {
                return false;
            }
            for (int i = 0, j = 0; i < codigo.length(); i++, j++) {
                char caracterActualCodigo = codigo.charAt(i);
                char caracterActualExpresionRegular = expresionRegular.charAt(i);
                if (!caracterValido(caracterActualCodigo, caracterActualExpresionRegular)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Funcion que revisa si un caracter cumple con las regla de la expresion
     * regular Reglas: $ simboliza caracter # simboliza digito [a..z] representa
     * la letra que se desea [0..9] representan los numeros que puede tomar
     *
     * @param caracterCodigoPostal
     * @param caracterExpresionRegular
     * @return true en caso de que sean iguales los dos caracteres o simbolicen
     * el mismo tipo de dato
     */
    public boolean caracterValido(char caracterCodigoPostal, char caracterExpresionRegular) {
        if (caracterExpresionRegular == '#') {
            return Character.isDigit(caracterCodigoPostal);
        } else {
            if (caracterExpresionRegular == '$') {
                return Character.isLetter(caracterCodigoPostal);
            } else {
                if (caracterExpresionRegular == caracterCodigoPostal) {
                    return true;
                }
            }
        }
        return false;
    }


}
