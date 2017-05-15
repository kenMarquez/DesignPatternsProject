package com.devf.designpatternsproject.contador;


import android.content.Context;

import com.devf.designpatternsproject.R;
import com.devf.designpatternsproject.models.CodigoPostal;
import com.devf.designpatternsproject.models.CodigoPostalCanada;
import com.devf.designpatternsproject.models.CodigoPostalChina;
import com.devf.designpatternsproject.models.CodigoPostalEstadosUnidos;
import com.devf.designpatternsproject.models.CodigoPostalJapon;
import com.devf.designpatternsproject.models.CodigoPostalMexico;
import com.devf.designpatternsproject.models.Colonia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Clase encargada de regresar la colonia a la que pertenece el codigo postal
 * Implementacion de la clase por archivos de texto
 *
 * @author Branch Code
 * @version 2.1
 */
public class ColoniaCodigoPostalImplementacionArchivos implements ColoniaCodigoPostal {

    public static final String MEXICO = "Mexico";
    public static final String ESTADOS_UNIDOS = "Estados Unidos";
    public static final String CANADA = "Canada";
    public static final String CHINA = "China";
    public static final String JAPON = "Japon";

    private Context context;

    public ColoniaCodigoPostalImplementacionArchivos(Context context) {
        this.context = context;
    }

    @Override
    public Colonia colonia(CodigoPostal codigoPostal) {
        String pais = "";
        try {
            if (codigoPostal instanceof CodigoPostalMexico) {
                pais = "Mexico";
                return obtenerColoniaDadoPais(pais, codigoPostal);
            }
            if (codigoPostal instanceof CodigoPostalEstadosUnidos) {
                pais = "Estados Unidos";
                return obtenerColoniaDadoPais(pais, codigoPostal);
            }
            if (codigoPostal instanceof CodigoPostalCanada) {
                pais = "Canada";
                return obtenerColoniaDadoPais(pais, codigoPostal);
            }
            if (codigoPostal instanceof CodigoPostalChina) {
                pais = "China";
                return obtenerColoniaDadoPais(pais, codigoPostal);
            }
            if (codigoPostal instanceof CodigoPostalJapon) {
                pais = "Japon";
                return obtenerColoniaDadoPais(pais, codigoPostal);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

    /**
     * Metodo auxiliar que sirve para poder regresar el nombre de la colonia que
     * tiene dicho codigo postal
     *
     * @param pais         Candena que representa el pais del codigo ya validado.
     * @param codigoPostal Objeto de la calse codigo postal.
     * @return Cadena con el nombre de la colonia a la que pertenece el codigo
     * postal.
     * @throws IOException
     */
    private Colonia obtenerColoniaDadoPais(String pais, CodigoPostal codigoPostal) throws IOException {
        //  String nombreArchivo = "C:\\Users\\Renoir\\Documents\\NetBeansProjects\\test\\src\\colonias\\codigoPostalColonias" + pais + ".txt";
//        String nombreArchivo = "./BaseDatos/colonias/codigoPostalColonias" + pais + ".txt";
//        File archivo = new File(nombreArchivo);

        InputStream stream = context.getResources().openRawResource(getRawFileFromCountry(pais));

        //File archivo = new File(getClass().getResource("../colonias/" + pais + ".txt").getFile());
//        FileReader lectorArchivo = new FileReader(archivo);
//        BufferedReader lector = new BufferedReader(lectorArchivo);
        BufferedReader lector = new BufferedReader(new InputStreamReader(stream));
        String linea;
        while ((linea = lector.readLine()) != null) {
            if (linea.contains(codigoPostal.getCodigoPostal())) {
                Colonia colonia = new Colonia(linea.substring(codigoPostal.getCodigoPostal().length() + 1), codigoPostal);
                colonia.setPais(pais);
                return colonia;
            }
        }
        return null;
    }

    public int getRawFileFromCountry(String country) {
        switch (country) {
            case MEXICO:
                return R.raw.codigo_postal_colonias_mexico;

            case ESTADOS_UNIDOS:
                return R.raw.codigo_postal_colonias_estados_unidos;

            case CANADA:
                return R.raw.codigo_postal_colonias_canada;

            case CHINA:
                return R.raw.codigo_postal_colonias_china;

            case JAPON:
                return R.raw.codigo_postal_colonias_japon;

            default:
                return -1;
        }
    }
}
