package org.kronexilane.AConsole.optionList;

import org.kronexilane.AConsole.Input;

/**
 * OptionListItemEnd salida predeterminada de un menú OptionList
 */
public class OptionListItemEnd extends AbstractOptionListItem{
    private Boolean Question=false;

    /**
     * Construye el OptionListItem de la salida del OptionList
     * @param Index índice usado.
     * @param Title texto mostrado.
     * @param Question TRUE/FALSE, pregunta si se desea salir, o no.
     */
    public OptionListItemEnd(String Index , String Title,Boolean Question) {
        super(Index , Title);
        this.Question=Question;
    }

    /**
     * Construye el OptionListItem de la salida del OptionList con
     * el &iacute;ndice 0, predeterminado y permite la pregunta de salida.
     * @param Question TRUE/FALSE para preguntar si se sale o no.
     */
    public OptionListItemEnd(Boolean Question){
        super("0","Terminar.");
        this.Question=Question;
    }

    /**
     * Construye el OptionListItem de la salida del OptionList
     * con un texto e &iacute;ndice predeterminado y salida directa.
     */
    public OptionListItemEnd(){
        super("0","Terminar.");
        this.Question=false;
    }

    @Override
    protected void Action() {
        if(this.Question) {
            if ( Input.Question("¿Terminar? (S/N):" , "S" , "N") ) System.exit(0);
        }else System.exit(0);

    }
}
