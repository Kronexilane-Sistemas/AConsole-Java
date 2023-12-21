package org.kronexilane.AConsole.datatable;

import java.util.Map;

/**
 * Clase Simple que especifica los campos Listables.
 */
public class ListableField{

    private String keyData;
    private Object objData;

    /**
     * Construye el objeto Listable.
     * @param key Nombre del campo.
     * @param obj Valor del campo listable.
     */
    public ListableField(String key , Object obj) {
        this.keyData = key;
        this.objData = obj;
    }

    public String getKey() {
        return keyData;
    }

    public Object getValue() {
        return objData;
    }

    public void setObjData(Object objData) {
        this.objData = objData;
    }

    @Override
    public String toString() {
        return "ListableField{" +
                "keyData='" + keyData + '\'' +
                ", objData=" + objData +
                '}';
    }
}
