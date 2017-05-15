package com.devf.designpatternsproject.contador;


import com.devf.designpatternsproject.models.CodigoPostal;

public interface Validar {

    /**
     * Metodo que recibe un objeto abstracto de la clase codigo postal y regresa un booleano que confirme si
     * ese codigo postal es valido para dicha entidad
     *
     * @param codigoPostal
     * @return true en caso de que el codigo postal pertenesca a la entidad descrita, false en caso contrario
     * @throws RuntimeException excepcion de codigo codigo postal
     */
    public boolean valida(CodigoPostal codigoPostal) throws RuntimeException;
}
