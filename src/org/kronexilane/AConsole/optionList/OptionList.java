package org.kronexilane.AConsole.optionList;

import org.kronexilane.AConsole.Input;
import org.kronexilane.AConsole.verificators.StringListVF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Muestra una lista de opciones en formato men&uacute;.
 *   @see OptionListItem
 *   @see AbstractOptionListItem
 *   @see OptionSelected
 */
public class OptionList{
    private List <OptionListItem> listO=new ArrayList<OptionListItem>();
    private String Title;
    private OptionListItem[] items;
    private OptionSelected select;

    /**
     * Establece el objeto OptionSelected (CALLBACK) que recibe el &iacute;ndice o
     * clave de la opci&oacute;n seleccionada
     * @param select Objeto OptionSelected que recoge y procesa las opciones.
     */
    public void setSelect(OptionSelected select) {
        this.select = select;
    }

    /**
     * Crea una nueva lista opciones.
     * @param title texto de la lista de opciones.
     */
    public OptionList(String title){
        this.Title=title;
    }

    /**
     * Construye la lista de opciones mediante un Array de OptionListItem.
     * @param Title texto de la la lista de opciones.
     * @param items Array de OptionListItem de la forma:
     *              OptionListItem[] items={
     *                  new OptionListItem(indice,titulo),
     *                  new OptionListItem(indice,titulo),
     *                  ...
     *              }
     */
    public OptionList(String Title,OptionListItem[] items){
        this.Title=Title;
        this.items=items;
        if(items!=null) listO.addAll(Arrays.asList(items));
    }

    /**
     * Agrega una instancia de un objeto OptionListItem a la lista de opciones.
     * @param item Objeto OptionListItem de la opci&oacute;n de men&uacute; representada.
     */
    public void add(OptionListItem item){
        Object[] e=listO.stream().filter(o->o.getIndex().equalsIgnoreCase(item.getIndex())).toArray();
        if( e.length==0) listO.add(item);
    }

    /**
     * Agrega de forma predeterminada una salida est&aacute;ndar.
     */
    public void addDefaultExit(){
        listO.add(new OptionListItemEnd());
    }


    /**
     * Muestra la lista de opciones en formato men&uacute; por la consola del sistema.
     */
    public void show(){
        java.io.StringWriter i;
        String[] indices;
        OptionListItem opSelected;
        // Obtiene el array de indices y lo muestra en un mensaje de eleccion de opcion
        indices= (String[]) listO.stream().map(x->x.getIndex()).toArray(String[]::new);
        String index=String.join(",",indices);
        String opS=Input.fromConsole("Escoja opcion ["+index+"]:",new StringListVF(listO,"Opcion invalida.",Title) ,true,true).toString();

        // Encuentra la opcion seleccionada
        opSelected=listO.stream().filter(x->x.getIndex().equalsIgnoreCase(opS)).findFirst().get();

        // Averigua si el OptionListItem es una derivacion de la clase abstracta OptionListItemFunctional
        Class FunctionalOption=opSelected.getClass().getSuperclass();
        if(FunctionalOption!=null && FunctionalOption.getName().equalsIgnoreCase(AbstractOptionListItem.class.getName())){
            AbstractOptionListItem ItemFunctional= (AbstractOptionListItem) opSelected;
            ItemFunctional.Action();
        }else if(this.select!=null) {
            this.select.Selected(opS.toUpperCase());
        }
        show();
    }
}


