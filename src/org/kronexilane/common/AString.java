package org.kronexilane.common;

import javax.script.CompiledScript;
import java.text.DecimalFormat;

/**
 * Funciones avanzadas de tratamiento de cadenas
 */
public class AString {
    /**
     * Devuelve una cadena compuesta de 'n' car&aacute;cteres 'car'.
     * @param n Cantidad de caracteres
     * @param car Caracter que se usará.
     * @return Cadena resultante.
     */
    public static String Repeat(int n,char car) {
         return new String(new char[n]).replace('\0' , car);
    }

    /**
     * Uso de DecimalFormat para el formateo seg&uacute;n un patr&oacute;n de
     * un n&uacute;mero entero o d&eacute;cimal.
     * @param pattern Patr&oacute;n de formateo seg&uacute;n el est&aacute;ndar de la clase DecimalFormat
     * @param data Objeto a formatear.
     * @return Cadena formateada.
     */
    public static String Format(String pattern,Object data){
        DecimalFormat formatea = new DecimalFormat(pattern);
        return formatea.format(data);
    }
}
