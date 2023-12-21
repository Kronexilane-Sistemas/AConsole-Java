package org.kronexilane.AConsole.verificators;

/**
 * Interface funcional verificadora
 * para la funcion Input.fromConsole
 */
public interface Verificator {
    /**
     * Verifica si el dato de entrada data, es v&aacute;lido para el contexto
     * de la clase que implemente la verificaci&oacute;n.
     * @param data Dato de entrada String.
     * @return True-False Si cumple o no con la validaci&oacute;n.
     */
    boolean IsValid(String data);

    /**
     * Devuelve el mensaje de error de verificaci&oacute;n.
     * @return El mensaje del verificador que implemente la interface.
     */
    String getInvalidMsg();

    /**
     * Devuelve el nombre de tipo de dato java.
     * @return Devuelve el nombre de tipo de dato java o de la clase que lo implementa.
     */
    String getJavaTypeName();
}
