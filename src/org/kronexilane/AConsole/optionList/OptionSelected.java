package org.kronexilane.AConsole.optionList;

/**
 * Interfaz funcional (CALLBACK) que recoge la opci&oacute;n
 * seleccionada de una lista de opciones OptionList.
 *  @see OptionListItem
 *  @see OptionList
 */
public interface OptionSelected {
    /**
     * Recoge la opci&oacute;n seleccionada
     * @param index &iacute;ndice o Clave de la opci&oacute;n de la lista de opciones.
     * @see OptionSelected
     */
    public void Selected(String index);
}
