package org.kronexilane.AConsole.optionList;

import java.util.function.Supplier;

/**
 * Clase Abstracta que deriva de OptionListItem para crear una opci&oacute;n de item de lista
 * en una clase separada en vez de usar la interfaz CallBack OptionSelected.
 * De esta clase se debe derivar para separar la accion a ejecutar del OptionListItem
 * del programa principal y evitar usar la interface CALLBACK OptionSelected.
 * Obliga a implementar el metodo Action() que es el que ejecuta la opci&oacute;n del
 * men&uacute; seleccionado en la lista.
 * @see OptionListItem
 * @see OptionList
 * @see OptionSelected
 */

public abstract class AbstractOptionListItem extends OptionListItem{

    /**
     * Construye un item de OptionList
     *
     * @param Index &iacute;ndice o clave de selecci&oacute;n.
     * @param Title t&iacute;tulo de la opci&oacute;n que se va a mostrar.
     */
    public AbstractOptionListItem(String Index , String Title) {
        super(Index , Title);
    }

    /**
     * M&eacute;todo que ejecuta la acci&oacute;n del item de OptionList.
     */
    protected abstract void Action();
}
