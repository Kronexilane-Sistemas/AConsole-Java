package org.kronexilane.AConsole;


import org.kronexilane.AConsole.msgboxStyles.mbSTANDARD;
import org.kronexilane.AConsole.msgboxStyles.msgBoxStyle;

import java.util.Arrays;

import java.util.Comparator;
import java.util.Optional;


/**
 * Salida avanzada de datos por consola.
 */
public class Output {

    private static msgBoxStyle box;

    /**
     * Permite cambiar el estilo de la caja que rodea
     * al mensaje emitido por el método msgBox
     * @param box Objeto msgBoxStyle
     */
    public static void setBox(msgBoxStyle box) {
        Output.box = box;
    }

    /**
     * Recuadra un mensaje en la consola.
     * Si el mensaje se compone de varias l&iacute;neas, aparecer&aacute; centrado.
     * @param msg Mensaje a visualizar.
     */
    public static void msgBox(String msg){
        int maxLon=0;
        // Crea el objeto BoxStyle si no se ha establecido
        if(box==null) box = new mbSTANDARD();
        // 1º Separa las lineas y nos devuelve la de mayor longitud.
        String[] lines=msg.split("\n");
        Optional<String> maxString=Arrays.stream(lines).max((Comparator.comparingInt(String::length)));
        if(maxString.isPresent()) maxLon=maxString.get().length();

        // 2ª Prepara la linea superior e inferior y visualiza la superior
        char[] charup = new char[maxLon+4];
        char[] chardown = new char[maxLon+4];
        Arrays.fill(charup,box.getHorizontal());
        Arrays.fill(chardown,box.getHorizontal());
        charup[0]= box.getUpLeft();
        charup[maxLon+3]=box.getUpRight();
        chardown[0]= box.getDownLeft();
        chardown[maxLon+3]=box.getDownRight();
        String up=new String(charup);
        String down=new String(chardown);

        System.out.println(up);

        int MaxLon = maxLon;

        // 3º Visualiza las lineas haciendo correcciones oportunas
        Arrays.stream(lines).forEach((s)->{
            // Centra cada linea
            int med=(MaxLon-(s.length()))/2;
            char[] charsFill = new char[med];
            Arrays.fill(charsFill, ' ');
            String strF=new String(charsFill);
            System.out.format("%c ",box.getVertical());
            String fin=strF.concat(s).concat(strF);
            if(fin.length()<MaxLon){
                for(int c=0;c<1;c++){
                    fin=fin.concat(" ");
                }
            }
            // Visualiza las linea y la imprime
            System.out.print(fin);
            System.out.format(" %c\n",box.getVertical());
        });
        //5º Visualiza la parte final
        System.out.println(down);
    }



}
