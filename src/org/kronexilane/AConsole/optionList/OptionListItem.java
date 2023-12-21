package org.kronexilane.AConsole.optionList;

/**
 * Item simple de opci&oacute;n de men&uacute; para OptionList.
 *   @see OptionList
 *   @see OptionSelected
 */
public class OptionListItem {
    private String Index;
    private String Title;

    /**
     * Construye un item de OptionList.
     * @param Index &iacute;ndice o clave de selecci&oacute;n.
     * @param Title texto de la opci&oacute;n que se mostrar&aacute;.
     */
    public OptionListItem(String Index,String Title){
        this.Index=Index;
        this.Title=Title;
    }
    public String getIndex() {
        return Index;
    }
    public String getTitle() {
        return Title;
    }
}
