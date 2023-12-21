package org.kronexilane.AConsole.verificators;

/**
 * Verificador para fromConsole de tipo de dato Double
 */
public class DoubleVF implements Verificator{
    private String invalidMsg="No es un dato de tipo DOUBLE.";
    public DoubleVF(){}
    public DoubleVF(String invalidMsg){ this.invalidMsg=invalidMsg; }

    @Override
    public boolean IsValid(String data) {
        boolean dev=true;
        try {
            Double ret = Double.parseDouble(data);
        }catch(NumberFormatException ex){
            dev=false;
        }
        return dev;
    }

    @Override
    public String getInvalidMsg() {
        return this.invalidMsg;
    }

    @Override
    public String getJavaTypeName() {
        return Double.class.getTypeName();
    }
}
