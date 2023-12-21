package org.kronexilane.AConsole.verificators;

/**
 * Verificador para Integer con intervalo de m&iacute;nimo y m&aacute;ximo.
 */

public class IntMinMaxVF extends IntVF {

    private int min;
    private int max;

    /**
     * Constructor para verificador de enteros con m&aacute;ximo y m&iacute;nimo.
     * @param min m&iacute;nimo del intervalo.
     * @param max m&aacute;ximo del intervalo.
     * @param invalidMsg Mensaje de error si el intervalo no se cumple.
     */
    public IntMinMaxVF(int min, int max, String invalidMsg) {
        super(invalidMsg);
        this.min=min;
        this.max=max;
    }

    @Override
    public boolean IsValid(String data) {
        boolean validData=super.IsValid(data);
        if(validData){
            int value=Integer.parseInt((data));
            validData=(value>=this.min && value<=this.max);
        }
        return validData;
    }
}
