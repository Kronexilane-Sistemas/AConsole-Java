package org.kronexilane.AConsole.datatable;

import org.kronexilane.AConsole.datatable.dtColumnAlignment;

/**
 * Especifica el formato de una columna de FormattedList.
 */
public class dtColumnFormat {
    private String dtColumnName;
    private String dtColumnTitle;

    final private dtColumnAlignment columnAlignment;
    final private dtColumnAlignment columnTitleAlignment;
    private Integer maxLength=null;
    final private Integer maxDecimals;

    /**
     * Crea un nuevo formato de columna
     * @param dtColumnName Nombre de la columna.
     * @param dtColumnTitle T&iacute;tulo de la columna.
     * @param columnAlignment Alineaci&oacute;n de la columna.
     * @param columnTitleAlignment Alineacion del t&iacute;tulo de la columna.
     */
    public dtColumnFormat(String dtColumnName ,
                          String dtColumnTitle ,
                          dtColumnAlignment columnAlignment,
                          dtColumnAlignment columnTitleAlignment) {
        this.dtColumnName = dtColumnName;
        this.dtColumnTitle = dtColumnTitle;
        this.columnAlignment = columnAlignment;
        this.columnTitleAlignment=columnTitleAlignment;
        maxDecimals = null;
    }

    /**
     * Crea un nuevo formato de columna
     *  @param dtColumnName Nombre de la columna.
     *  @param dtColumnTitle T&iacute;tulo de la columna.
     *  @param columnAlignment Alineaci&oacute;n de la columna.
     *  @param columnTitleAlignment Alineaci&oacute;n del t&iacute;tulo de la columna.
     * @param maxDecimals En caso de dato de tipo decimal, el n&uacute;mero de d&iacute;gitos decimales admitidos.
     */
    public dtColumnFormat(String dtColumnName,
                          String dtColumnTitle,
                          dtColumnAlignment columnAlignment,
                          dtColumnAlignment columnTitleAlignment,
                          Integer maxDecimals) {
        this.dtColumnName = dtColumnName;
        this.dtColumnTitle = dtColumnTitle;
        this.columnAlignment = columnAlignment;
        this.columnTitleAlignment=columnTitleAlignment;
        this.maxDecimals = maxDecimals;
    }

    public String getDtColumnName() {
        return dtColumnName;
    }

    public dtColumnAlignment getColumnAlignment() {
        return columnAlignment;
    }

    public dtColumnAlignment getColumnTitleAlignment() {
        return columnTitleAlignment;
    }

    public String getDtColumnTitle() {
        return dtColumnTitle;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getMaxDecimals() {
        return maxDecimals;
    }
}
