package org.kronexilane.AConsole.verificators;

/**
 * Verificador para tipo de dato Integer.
 */
public class IntVF implements Verificator {

    private String invalidMsg;


    /**
     * Constructor del verificador de enteros.
     * @param invalidMsg Mensaje si el dato no es de tipo entero.
     */
    public IntVF(String invalidMsg){
        this.invalidMsg=invalidMsg;
    }

    public boolean IsValid(String data) {
        boolean res=true;
        try{
            int convert=Integer.parseInt(data);
        }catch(NumberFormatException ex){
            res=false;
        }
        return(res);
    }

    @Override
    public String getInvalidMsg() {
        return this.invalidMsg;
    }

    @Override
    public String getJavaTypeName() {
        return Integer.class.getTypeName();
    }
}
