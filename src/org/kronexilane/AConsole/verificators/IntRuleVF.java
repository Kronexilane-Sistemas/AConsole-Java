package org.kronexilane.AConsole.verificators;

import java.util.function.Predicate;

/**
 * Verificador para enteros (Integer) especificando una regla de comprobaci&oacute;n
 * mediante una expresi&oacute;n Lambda de tipo predicado simple.
 */
public class IntRuleVF extends IntVF {
    private Predicate <Integer> intRule;

    /**
     * Construye el objeto de verificaci&oacute;n con una expresi&oacute;n LAMBDA predicado.
     * @param rule expresi&oacute;n lambda de predicado que especifica
     *             la regla de verificaci&oacute;n.
     * @param msg Mensaje de error si no se cumple la regla de verificaci&oacute;n.
     */
    public IntRuleVF(Predicate<Integer> rule,String msg){
        super(msg);
        this.intRule=rule;
    }

    @Override
    public boolean IsValid(String data) {
        return super.IsValid(data) && this.intRule.test(Integer.parseInt(data));
    }

    @Override
    public String getInvalidMsg() {
        return super.getInvalidMsg();
    }
}
