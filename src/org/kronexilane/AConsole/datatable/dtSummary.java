package org.kronexilane.AConsole.datatable;


import java.util.function.Function;

/**
 * Objeto que específica un Sumario de datos de una columna
 */
public class dtSummary {
    private String field;
    private String title;
    private Function<Object[],Object> fieldSummary;

    /**
     * Crea un objeto data Table Summary
     * @param field Campo mapeado en la interface 'Listable'.
     * @param title T&iacute;tulo del sumario.
     * @param fieldSummary Funci&oacute;n lambda que c&aacute;lcula el sumario a realizar,
     *                     se lleva a cabo mediante el array de Object[] que contendr&aacute;
     *                     la lista de valores mapeados de 'field'.
     */
    public dtSummary(String field , String title , Function<Object[], Object> fieldSummary) {
        this.field = field;
        this.title = title;
        this.fieldSummary = fieldSummary;
    }

    public String getField() {
        return field;
    }

    public String getTitle() {
        return title;
    }

    public Function<Object[], Object> getFieldSummary() {
        return fieldSummary;
    }
}
