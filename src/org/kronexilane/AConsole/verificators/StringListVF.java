package org.kronexilane.AConsole.verificators;

import org.kronexilane.AConsole.optionList.OptionListItem;

import java.util.Arrays;
import java.util.List;

/**
 * Verificador para lista de cadenas.
 */
public class StringListVF implements Verificator {
    private String[] lista;

    public List<OptionListItem> getListOp() {
        return listOp;
    }
    private List<OptionListItem> listOp;

    private String invalidMsg;
    private String TitleOption;

    public boolean isOptionList() {
        return isOptionList;
    }

    private boolean isOptionList=false;

    /**
     * Construye la Lista de verificaci&oacute;n con una matriz de opciones.
     * @param lista Array de cadenas que ser&aacute;n las opciones v&aacute;lidas de verificaci&oacute;n.
     */
    public StringListVF(String lista[]){
        invalidMsg="El valor no es valido: ["+String.join(",",lista)+"]";
        this.lista=lista;
    }

    /**
     * Construye la lista de verificaci&oacute;n especificando un mensaje de error.
     * @param lista lista Array de Cadenas que ser&aacute;n las opciones v&aacute;lidas de verificaci&oacute;n.
     * @param InvalidMsg Mensaje de error si la verificaci&oacute;n no se cumple.
     */
    public StringListVF(String lista[], String InvalidMsg){
        this.invalidMsg=InvalidMsg;
        this.lista=lista;
    }

    public String getTitleOption() {
        return TitleOption;
    }

    /**
     * Construye la lista de verificación con una lista de objetos OptionListItem.
     * que permite crear listas de tipo menú de opciones.
     * @param listO Lista de objetos OptionListItem.
     * @param InvalidMsg Mensaje de error si no se cumple la verificación.
     * @param TitleOption texto de la lista o menú de opciones.
     */
    public StringListVF(List<OptionListItem> listO , String InvalidMsg, String TitleOption){
        this.listOp=listO;
        this.lista=listO.stream().map(s->s.getIndex()).toArray(String[]::new);
        this.isOptionList=listO instanceof List;
        this.invalidMsg=InvalidMsg;
        this.TitleOption=TitleOption;
    }

    @Override
    public boolean IsValid(String data) {
        return Arrays.
                stream(lista).
                    filter(s->s.equalsIgnoreCase(data)).findFirst().isPresent();
    }

    @Override
    public String getInvalidMsg() {
        return invalidMsg;
    }

    @Override
    public String getJavaTypeName() {
        return String.class.getTypeName();
    }
}
