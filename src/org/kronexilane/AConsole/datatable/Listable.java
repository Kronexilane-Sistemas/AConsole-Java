package org.kronexilane.AConsole.datatable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Interface que debe implementar un objeto que use la lista formateada (FormattedList)
 */
public interface Listable {
    /**
     * HashMap con los datos contenidos en el objeto base para la lista formateada.
     * Este es el m&eacute;todo que debe implementar las clases "listables" por FormattedList
     * @return HashMap con los pares columna/valor.
     */
    LinkedHashMap<String,Object> getData();

    /**
     * M&eacute;todo est&aacute;tico que construye mediante un array de ListableField
     * el objeto LinkedHashMap para las clases "listables"
     * @param data Array de objetos ListableField.
     * @return HashMap con los campos mapeados.
     */
    static LinkedHashMap<String,Object> AddElements(ListableField[] data){
        // Sustituir valores NULL por N/A (No aplicable)
        Arrays.stream(data).forEach(e->{
            if(e.getValue()==null){
                e.setObjData("N/A");
            }
        });
        return Arrays.stream(data).
            collect(
                Collectors.toMap(
                        ListableField::getKey,
                    ListableField::getValue ,
                    (a , b) -> b ,
                    LinkedHashMap::new
                )
            );
    }
}
