package com.devf.designpatternsproject.contador;


import android.content.Context;

import com.devf.designpatternsproject.R;
import com.devf.designpatternsproject.excepciones.ServicioInexistenteExcepcion;
import com.devf.designpatternsproject.models.DescuentoFinal;
import com.devf.designpatternsproject.models.Servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DescuentoFinalCodigoPostal {

    private Context context;

    public DescuentoFinalCodigoPostal(Context context) {
        this.context = context;
    }

    /**
     * Metodo que regresa el descuento inicial (y muchas veces final) del servicio en cuestion.
     *
     * @param servicio Servicio al que aplica el descuento
     * @return descuento final
     * @throws ServicioInexistenteExcepcion
     */
    public DescuentoFinal regresaDescuentoFinal(Servicio servicio) throws ServicioInexistenteExcepcion {
        FileReader lectorArchivo = null;
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.descuento);
//            String nombreArchivo = "./BaseDatos/descuento/descuento.txt";
//            File archivo = new File(nombreArchivo);
//            lectorArchivo = new FileReader(archivo);
//            BufferedReader lector = new BufferedReader(lectorArchivo);
            BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
            String linea;
            DescuentoFinal descuento = new DescuentoFinal();
            String nServicio = "";
            String nEstablecimiento = "";
            String categoria = "";
            int descuentoA;
            boolean condicion = false;
            while ((linea = lector.readLine()) != null) {
                if (condicion && linea.contains("servicio")) {
                    nServicio = linea.trim();
                }
                if (condicion && linea.contains("categoria")) {
                    categoria = linea.trim();
                }
                if (condicion && linea.contains("descuento")) {
                    descuentoA = Integer.parseInt(linea.trim());
                    if (nServicio.trim().equalsIgnoreCase(servicio.getNombreServicio().trim())
                            && nEstablecimiento.trim().equalsIgnoreCase(servicio.getEstablecimiento().trim())) {
                        descuento.setServicio(servicio);
                        descuento.setCategoria(categoria);
                        descuento.setDescuento(descuentoA);
                    }
                }
                if (condicion && linea.contains("establecimiento")) {
                    nEstablecimiento = linea.trim();
                }

            }
            return descuento;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DescuentosServiciosDBImplementacionArchivos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DescuentosServiciosDBImplementacionArchivos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return null;
    }
}
