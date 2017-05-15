package com.devf.designpatternsproject.contador;


import android.content.Context;

import com.devf.designpatternsproject.R;
import com.devf.designpatternsproject.excepciones.ServicioInexistenteExcepcion;
import com.devf.designpatternsproject.models.DescuentoFinal;
import com.devf.designpatternsproject.models.DescuentosServicios;
import com.devf.designpatternsproject.models.Servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la interfaz de DescuentosServiciosDB que se encarga de
 * inplementar la interfaz tomando como base de datos en un archivo de texto
 */
public class DescuentosServiciosDBImplementacionArchivos implements DescuentosServiciosDB {

    private Context context;

    @Override
    public List<DescuentosServicios> leerDescuentos(Context context) throws ServicioInexistenteExcepcion {
//        FileReader lectorArchivo = null;
        try {
//            String nombreArchivo = "./BaseDatos/descuento/descuento.txt";

//            File archivo = new File(nombreArchivo);
//            lectorArchivo = new FileReader(archivo);
//            BufferedReader lector = new BufferedReader(lectorArchivo);
            BufferedReader lector = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.descuento)));
            String linea;
            DescuentosServiciosDBImplementacionArchivos buscaServicios = new DescuentosServiciosDBImplementacionArchivos();
            String nServicio = "";
            String nEstablecimiento = "";
            List<DescuentosServicios> descuentos = new ArrayList<>();
            DescuentosServicios descuento = new DescuentoFinal();
            while ((linea = lector.readLine()) != null) {
                if (linea.contains("categoria")) {
                    linea = linea.trim().substring(linea.indexOf(":") + 1).trim();
                    descuento.setCategoria(linea.trim());
                }
                if (linea.contains("servicio")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    nServicio = linea.trim();
                }
                if (linea.contains("establecimiento")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    nEstablecimiento = linea.trim();
                    descuento.setServicio(buscaServicio(context, nServicio, nEstablecimiento));

                }
                if (linea.contains("descuento")) {
                    linea = linea.substring(linea.indexOf(":") + 1).trim();
                    descuento.setDescuento(Integer.parseInt(linea));
                    //descuento.setDescuento(0);
                    descuentos.add(descuento);
                    descuento = new DescuentoFinal();
                }

            }

            return descuentos;
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DescuentosServiciosDBImplementacionArchivos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
//            Logger.getLogger(DescuentosServiciosDBImplementacionArchivos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo que ayuda a encontrar un Servicio en la base de datos tomando como parametros el nombre dek servicio y
     * el nombre del establecimiento
     *
     * @param nombreServicio  Cadena que especifica el nombre del servicio.
     * @param Establecimiento Cadena que especifica el nombre del establecimiento
     * @return Servicio
     * @throws ServicioInexistenteExcepcion
     */

    public Servicio buscaServicio(Context context, String nombreServicio, String Establecimiento) throws ServicioInexistenteExcepcion {
        FileReader lectorArchivo = null;
        try {
//            String nombreArchivo = "./BaseDatos/establecimientos/servicios.txt";
//            File archivo = new File(nombreArchivo);
//            lectorArchivo = new FileReader(archivo);
//            BufferedReader lector = new BufferedReader(lectorArchivo);
            BufferedReader lector = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.servicios)));
            String linea;
            Servicio servicio = new Servicio();

            while ((linea = lector.readLine()) != null) {

                if (linea.contains("nombreServicio")) {
                    servicio.setNombreServicio(linea.substring(linea.indexOf(":") + 1));
                }
                if (linea.contains("costo")) {
                    servicio.setCosto(Double.parseDouble(linea.substring(linea.indexOf(":") + 1).trim()));
                }
                if (linea.contains("establecimiento")) {
                    String nombre = (linea.substring(linea.indexOf(":") + 1)).trim();
                    servicio.setEstablecimiento(nombre);
                    String nombreEstablecimiento = Establecimiento.trim();
                    if (nombre.equalsIgnoreCase(nombreEstablecimiento)) {

                        return servicio;
                    }
                    servicio = new Servicio();
                }
            }
            throw new ServicioInexistenteExcepcion("servicios inexistentes");
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
