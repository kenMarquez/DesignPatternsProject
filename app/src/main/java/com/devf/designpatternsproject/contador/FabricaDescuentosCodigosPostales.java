package com.devf.designpatternsproject.contador;


import android.content.Context;

import com.devf.designpatternsproject.excepciones.ServicioInexistenteExcepcion;
import com.devf.designpatternsproject.models.DescuentoAdicional;
import com.devf.designpatternsproject.models.DescuentoFinal;
import com.devf.designpatternsproject.models.DescuentosServicios;
import com.devf.designpatternsproject.models.Servicio;

public class FabricaDescuentosCodigosPostales {

    private Context context;

    public FabricaDescuentosCodigosPostales(Context context) {
        this.context = context;
    }

    /**
     * Fabrica que busca y crea los descuentos y regresa si un descuento tiene un descuento adicional o si
     * unicamente tiene un unico descuento
     *
     * @param codigoPostal    Codigo Postal
     * @param identificador   Nombre del servicio
     * @param establecimiento Nombre del establecimiento
     * @return Objeto de la clase Descuentos servicios
     * @throws ServicioInexistenteExcepcion
     */
    public DescuentosServicios creaDescuento(String codigoPostal, String identificador, String establecimiento) throws ServicioInexistenteExcepcion {
        DescuentosServiciosDBImplementacionArchivos desc = new DescuentosServiciosDBImplementacionArchivos();
        Servicio servicio = desc.buscaServicio(context,identificador, establecimiento);
        DescuentoFinalCodigoPostal descuentoInicial = new DescuentoFinalCodigoPostal(context);
        DescuentoFinal descuentoActual = descuentoInicial.regresaDescuentoFinal(servicio);
        if (DescuentosAdicionalesCodigoPostal.tieneDescuentoAdiccional(context, identificador, servicio)) {
            DescuentosAdicionalesCodigoPostal descuentoA = new DescuentosAdicionalesCodigoPostal();
            DescuentoAdicional descuento = descuentoA.regresaDescuentoAdicional(context,codigoPostal, servicio, descuentoActual);
            return descuento;
        } else {
            return descuentoActual;
        }
    }
}
