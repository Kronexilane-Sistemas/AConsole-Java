package org.kronexilane.AConsole.commandline;

import java.util.HashMap;
import java.util.List;

/***
 * Clase Abstracta que define un flujo alternativo
 * de ejecución para una opción de línea de comandos.
 */
public abstract class CLineAction {

    private boolean executed=false;

    /**
     * Implementa el método de acción al invocar el parámetro en la línea de comandos
     * @param parameter Devuelve el parámetro de linea de comandos que ha activado la acción
     * @param subParams Lista que contiene subparámetros si acompañan a la acción,
     *                  por ejemplo: Lista de archivos a procesar
     */
    public abstract void DoIt(String parameter,List<String> subParams);

    /**
     * Establece el estado de la ejecución de la opción en TRUE
     * para evitar que sea ejecutado más veces si aparece dicha
     * opción en la línea de comando
     */
    protected void Done(){
        this.executed=true;
    }

    /**
     * Estable el estado de la ejecución de la opción en FALSE
     */
    protected void ClearDone(){
        this.executed=false;
    }

    /**
     * Este método de clase ABSTRACTA es heredado y evita que se ejecuten
     * opciones más de una sola vez. Para ello debe establecerse la variable
     * protegida en el método DoIt a TRUE mediante: super.executed=true;
     * @return True/False si se ejecutó ya la acción
     */
    public boolean isExecuted() {
        return executed;
    }

}
