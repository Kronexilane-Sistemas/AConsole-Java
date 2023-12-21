package org.kronexilane.AConsole.datatable;

/**
 * Excepci&oacute;n que lanza la clase FormattedList si hay alg&uacute;n error.
 * Es de tipo RuntimeException, no obligatoria su tratamiento.
 * Generalmente se lanzar&aacute; cuando haya errores de mapeado incorrecto
 * de los campos mapeados desde la clase que implemente Listable, hacia
 * la cabecera de la tabla.
 * @see Listable
 * @see FormattedList
 */
public class FormattedListException extends RuntimeException{
    /**
     * Construye la excepci&oacute;n con el mensaje espec&iacute;ficado.
     * @param message Mensaje para la excepci&oacute;n de lista formateada.
     */
    public FormattedListException(String message) {
        super(message);
    }
}
