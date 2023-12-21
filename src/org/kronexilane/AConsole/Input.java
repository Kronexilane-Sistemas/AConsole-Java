package org.kronexilane.AConsole;

import org.kronexilane.AConsole.verificators.StringListVF;
import org.kronexilane.AConsole.verificators.Verificator;
import org.kronexilane.AConsole.optionList.OptionListItem;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Entrada avanzada de datos por consola.
 */
public class Input {
    private static Object getValueFromConsole=null;
    private static String getLastTypeFromConsole=null;

    /**
     * Entrada de datos avanzada por consola.
     * @param msg Mensaje de petici&oacute;n de datos.
     * @param verify Verificador que comprueba la entrada de datos.
     * @param repeatInput TRUE: para repetir la petici&oacute;n ante una entrada incorrecta.
     * @param EmptyError TRUE: para considerar la entrada vacia como no v&aacute;lida.
     * @return Dato filtrado verificado. NULL si fue incorrecto o vac&iacute;o.
     */
    public static Object fromConsole(String msg,
                                     Verificator verify,
                                     boolean repeatInput,
                                     boolean EmptyError){
        // Transforma el verificador al de List de valores
        // Para comprobar si es una OptionList o menu de opciones
        try {
            StringListVF vOl = (StringListVF) verify;
            // En caso de serlo y haberse especificado una lista de OptionListItem
            // Visualizarla
            if(vOl.getTitleOption()!=null) {
                char[] sub = new char[vOl.getTitleOption().length()];
                Arrays.fill(sub , '-');
                String suB = new String(sub);
                System.out.println("\n" + vOl.getTitleOption());
                System.out.println(suB);
                if ( vOl.isOptionList() ) {
                    for (OptionListItem i : vOl.getListOp()) {
                        System.out.println(i.getIndex().concat(" - ").concat(i.getTitle()));
                    }
                }
                System.out.println();
            }
        }
        catch(ClassCastException ex){
            // Si Hay un error por que no es un StringListV
            // Obviarlo aqui.
        }
        // Lanzar mensaje de peticion de datos y entrada
        System.out.print(msg);
        Scanner myScan=new Scanner(System.in);
        String Data=myScan.nextLine();
        //EmptyError (ErrorSiCadenaVacia) = TRUE Si la cadena vacia se considera error
        if(Data.equalsIgnoreCase("") && !EmptyError){
            if ( repeatInput ) return fromConsole(msg , verify , true , false);
        }
        // Verifica el dato de acuerdo a la clase verificadora y emite
        // el dato y vuelve a pedirlo si asi se ha establecido
        if (!verify.IsValid(Data)) {
            System.out.println(verify.getInvalidMsg());
            if (repeatInput) return fromConsole(msg , verify , true , EmptyError);
        }
        getValueFromConsole=Data;
        getLastTypeFromConsole=verify.getJavaTypeName();
        return Data;
    }

    /**
     * Pregunta de tipo SI/NO.
     * @param msg Mensaje de la pregunta.
     * @param yes opci&oacute;n para la afirmaci&oacute;n.
     * @param no opci&oacute;n para la negaci&oacute;n.
     * @return TRUE: Si se ha seleccionado la afirmaci&oacute;n.
     */
    public static boolean Question(String msg,String yes,String no){
        String[] response=new String[2];
        response[0]=yes;
        response[1]=no;
        Object res=fromConsole(msg,new StringListVF(response),true,false);
        String r=res.toString();
        return(r.equalsIgnoreCase(yes));
    }

    /**
     * Obtiene el &uacute;ltimo entero obtenido bajo la ultima ejecuci&oacute; del m&eacute;todo fromConsole.
     * @return Entero introducido.
     */
    public static int GetLastInteger(){
        int dato=0;
        if(getLastTypeFromConsole!=null && getLastTypeFromConsole.equalsIgnoreCase(Integer.class.getTypeName())){
            dato=Integer.parseInt(getValueFromConsole.toString());
        }
        return dato;
    }

    /**
     * Obtiene la &uacute;ltima cadena bajo la &uacute;ltima ejecuci&oacute;n del m&eacute;todo fromConsole.
     * @return Cadena introducida.
     */
    public static String GetLastString(){
        String dato=null;
        if(getLastTypeFromConsole!=null && getLastTypeFromConsole.equalsIgnoreCase(String.class.getTypeName())){
            dato=getValueFromConsole.toString();
        }
        return dato;
    }

    /**
     * Obtiene el &uacute;ltimo double le&iacute;do bajo la &uacute;ltima ejecuci&oacute;n del m&eacute;todo fromConsole.
     * @return Double
     */
    public static Double GetLastDouble(){
        Double dato=null;
        if(getLastTypeFromConsole!=null && getLastTypeFromConsole.equalsIgnoreCase(Double.class.getTypeName())){
            dato=Double.parseDouble(getValueFromConsole.toString());
        }
        return dato;
    }
}
