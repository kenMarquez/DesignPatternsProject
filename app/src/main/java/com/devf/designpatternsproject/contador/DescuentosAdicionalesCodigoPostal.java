package com.devf.designpatternsproject.contador;


import android.content.Context;

import com.devf.designpatternsproject.R;
import com.devf.designpatternsproject.excepciones.ServicioInexistenteExcepcion;
import com.devf.designpatternsproject.models.DescuentoAdicional;
import com.devf.designpatternsproject.models.DescuentosServicios;
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

public class DescuentosAdicionalesCodigoPostal {


    /**
     * Metodo que regresa un atributo boolean que indica si existe un descuento adicional al descuento ya  proporcionado
     *
     * @param codigoPostal Cadena que representa el codigo postal de una colonia
     * @param servicio     Servicio que tiene el descuento
     * @return true si existe un  descuento aparte del descuento ya encontrado false en otro caso
     */
    public static boolean tieneDescuentoAdiccional(Context context, String codigoPostal, Servicio servicio) {
        FileReader lectorArchivo = null;
        try {
//            String nombreArchivo = "./BaseDatos/descuento/descuento_adicionall.txt";
//            File archivo = new File(nombreArchivo);
//            lectorArchivo = new FileReader(archivo);

//            InputStream stream = context.getResources().openRawResource(R.raw.descuento_adicional);
            InputStream inputStream = context.getResources().openRawResource(R.raw.descuento_adicional);

//            BufferedReader lector = new BufferedReader(lectorArchivo);
            BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
            String linea;
            String nServicio = "";
            String nEstablecimiento = "";
            boolean condicion = false;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains(codigoPostal)) {
                    condicion = true;
                }
                if (condicion && linea.contains("servicio")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    nServicio = linea.trim();
                }
                if (condicion && linea.contains("establecimiento")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    nEstablecimiento = linea.trim();
                    if (nServicio.trim().equalsIgnoreCase(servicio.getNombreServicio().trim())
                            && nEstablecimiento.trim().equalsIgnoreCase(servicio.getEstablecimiento().trim())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DescuentosServiciosDBImplementacionArchivos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DescuentosServiciosDBImplementacionArchivos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Metodo que regresa el descuento adicional al servicio, tomando como paramentro el codigo postal el servicio y el descuento previo que ya tenia el servicio
     *
     * @param codigoPostal     Cadena que representa al codigo postal de una Colonia
     * @param servicio         Servicio en el cual se efectua la promocion
     * @param descuentoInicial descuento anterior que ya tenia presente el Servicio
     * @return Objeto que contiene el descuento adicional del Servicio
     * @throws ServicioInexistenteExcepcion Arroja la excepcion en el caso de no encotrar  Servicio solicitado
     */
    public DescuentoAdicional regresaDescuentoAdicional(Context context, String codigoPostal, Servicio servicio, DescuentosServicios descuentoInicial) throws ServicioInexistenteExcepcion {
        FileReader lectorArchivo = null;
        try {
//            String nombreArchivo = "./BaseDatos/descuento/descuento_adicional.txtt";
//            File archivo = new File(nombreArchivo);
//            lectorArchivo = new FileReader(archivo);
//            BufferedReader lector = new BufferedReader(lectorArchivo);
            InputStream stream = context.getResources().openRawResource(R.raw.descuento_adicional);
            BufferedReader lector = new BufferedReader(new InputStreamReader(stream));
            String linea;
            DescuentoAdicional descuento = new DescuentoAdicional();
            String nServicio = "";
            String nEstablecimiento = "";
            String categoria = "";
            int descuentoA;
            boolean condicion = false;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains(codigoPostal)) {
                    condicion = true;
                }
                if (condicion && linea.contains("servicio")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    nServicio = linea.trim();
                }
                if (condicion && linea.contains("categoria")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    categoria = linea.trim();
                }
                if (condicion && linea.contains("descuentoadicional")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    descuentoA = Integer.parseInt(linea.trim());

                    if (nServicio.trim().equalsIgnoreCase(servicio.getNombreServicio().trim())
                            && nEstablecimiento.trim().equalsIgnoreCase(servicio.getEstablecimiento().trim())) {
                        descuento.setServicio(servicio);
                        descuento.setCategoria(categoria);
                        descuento.setDescuentoAdiccional(descuentoA);
                        descuento.setDescuento(descuentoInicial.getDescuento());
                        return descuento;
                    }
                }
                if (condicion && linea.contains("establecimiento")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();

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
