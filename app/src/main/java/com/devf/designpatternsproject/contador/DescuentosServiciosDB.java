package com.devf.designpatternsproject.contador;


import android.content.Context;

import com.devf.designpatternsproject.excepciones.ServicioInexistenteExcepcion;
import com.devf.designpatternsproject.models.DescuentosServicios;

import java.util.List;

public interface DescuentosServiciosDB {
    /**
     * Metodo que regresa una lista de todos los descuentos registrados en la Base de Datos descuentos en servicios
     *
     * @return Lista de descuentos
     */
    public List<DescuentosServicios> leerDescuentos(Context context) throws ServicioInexistenteExcepcion;


}
