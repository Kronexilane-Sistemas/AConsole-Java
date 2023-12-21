package org.kronexilane.AConsole.datatable;

import org.kronexilane.AConsole.Input;
import org.kronexilane.AConsole.verificators.IntMinMaxVF;
import org.kronexilane.common.AString;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Clase gen&eacute;rica derivada de LinkedList con utilidades pr&aacute;cticas
 * para formateo de datos e impresi&oacute;n por consola de texto.
 * @param <T> Objeto que compondr&aacute; la lista, sus elementos contenidos deben
 *          implementar el interface Listable.
 * @see Listable
 */

public class FormattedList<T extends Listable> extends LinkedList<T> {

    private final HashMap <String, dtColumnFormat> Header=new LinkedHashMap<>();
    private final List<dtSummary> Summaries=new ArrayList<>();
    private String Title=null;
    private Long pageSize=null;
    private long actualPage=0;
    private boolean bNextPage=false;
    private boolean bsummary=true;

    public void setPageSize(Long elements){
        this.pageSize=elements;
    }

    public void deactivateSummary() {
        bsummary = false;
    }

    public void reactivateSummary() {
        bsummary = true;
    }
    /**
     * Agrega un Array de objetos dtColumnFormat a la
     * cabecera de la lista.
     * @param data Array de objetos dtColumnFormat.
     */
    public void addHeader(dtColumnFormat[] data){
        for (dtColumnFormat dtf : data) {
            Header.put(dtf.getDtColumnName() , dtf);
        }
    }
    /**
     * Agrega un objeto a la lista formateada.
     * @param t Objeto a agregar.
     * @return True , si la operaci&oacute;n fue correcta.
     */
    @Override public boolean add(T t) {
        return super.add(t);
    }

    /**
     * Visualiza la lista formateada por la consola de texto.
     * @param text T&iacute;tulo de la tabla de datos.
     */
    public void toConsole(String text){
        this.Title=text;
        toConsole();
    }
    /**
     * Visualiza la lista formateada por la consola de texto
     */
    public void toConsole() throws FormattedListException{

        // Cabecera y subrayado
        StringBuilder HeaderString=new StringBuilder();
        StringBuilder HeaderUnderline =new StringBuilder();

        StringBuilder TableTitle =new StringBuilder();
        StringBuilder SummaryTitle=new StringBuilder();

        if(this.isEmpty()) return; // Sale si no hay lista

        // Obtiene la interface Listable del primer elemento
        Listable first = this.get(0);

        /*
         * PASOS INICIALES
         */

        // 1º Obtiene las máximas longitudes de cadena de cada campo listable
        for(String dtName:Header.keySet()){
                int lonF=0;
                if(first.getData()==null) throw new FormattedListException("getData() from listable returns null.");
                if(!first.getData().containsKey(dtName))
                    throw new FormattedListException("Column '"+dtName+"' not found in mappable fields.");
                    OptionalInt lon= Arrays.stream(
                        this.stream().map(
                                          a->a.getData().get(dtName).toString()
                                      ).
                                    toArray(String[]::new)).
                                    mapToInt(String::length).
                                    max();
                    if(lon.isPresent()) {
                        lonF = Math.max(lon.getAsInt() , Header.get(dtName).getDtColumnTitle().length());
                    }
                    Header.get(dtName).setMaxLength(lonF);
                }

        // 2º CREA LA CABECERA
        for(String cn:Header.keySet()){
            if(!first.getData().containsKey(cn)) continue;
            String align=Header.get(cn).getColumnTitleAlignment()== dtColumnAlignment.Right?"":"-";
            String title=String.format("%"+align+Header.get(cn).getMaxLength().toString()+"s",Header.get(cn).getDtColumnTitle());
            String subr= AString.Repeat(title.length(),'-');
            HeaderString.append(title).append("  "); // Titulos
            HeaderUnderline.append(subr).append("  "); // Subrayados
        }
        HeaderUnderline.delete(HeaderUnderline.length() - 2 , HeaderString.length());


        /*
         * FIN PASOS INICIALES
         */

        // A) CREA EL TITULO Y LO IMPRIME.
        if(Title!=null) {
            TableTitle.append(AString.Repeat(HeaderUnderline.length(),'*'));
            int len = TableTitle.length() / 2;
            TableTitle.replace(len - Title.length() / 2 , len + Title.length() / 2 , Title);
            System.out.println("\n"+ TableTitle);
        }else System.out.println();

        // B) IMPRIME CABECERA DE COLUMNAS Y SUBRAYADO.
        System.out.println(HeaderString+"\n"+ HeaderUnderline);

        // C) PROCESA DATOS Y LOS PONE EN UNA LISTA DE CADENAS
        ArrayList <String> lineas=new ArrayList<>();
        for(Object o:this){
            StringBuilder data=new StringBuilder();
            Listable t=(Listable) o;
            for(String d:Header.keySet()){
                if(!t.getData().containsKey(d)) throw new FormattedListException("El campo Header '"+d+"' no esta mapeado como listable.");
                // Retorna el alineamiento del Header
                String align=Header.get(d).getColumnAlignment()== dtColumnAlignment.Right?"":"-";
                // Obtiene el tamaño del campo (Proporcional al texto del Header)
                String longi=Header.get(d).getMaxLength().toString();
                String maxD;
                // Obtiene el máximo número de digitos
                if(Header.get(d).getMaxDecimals()!=null) maxD = Header.get(d).getMaxDecimals().toString(); else maxD="0";
                // Obtiene el tipo de datos.
                String tipo=t.getData().get(d).getClass().getTypeName();
                // Contiene el texto a imprimir
                String texto;
                // Crea cada línea de datos preformateada para String.format.
                if(tipo.equalsIgnoreCase(Double.class.getTypeName())){
                    texto = String.format("%"+align+longi+"."+maxD+"f" , t.getData().get(d));
                }
                else {
                    texto = String.format("%" + align + longi + "s" , t.getData().get(d));
                }
                // Añade el campo a la linea de datos
                data.append(texto).append("  ");
            }
            lineas.add(data.toString());
        }

        // D) IMPRIME LOS DATOS TENIENDO EN CUENTA EL PAGINADO.
        if(this.pageSize!=null) {
            lineas = (ArrayList<String>)
                    lineas.stream().
                            skip(this.actualPage * this.pageSize).limit(this.pageSize).collect(Collectors.toList());
        }
        lineas.forEach(System.out::println);

        // E) PREPARA LOS RESUMENES DE DATOS Y LOS IMPRIME
        if(this.bsummary && !Summaries.isEmpty()) {
            String Title3 = "#RESUMENES DE DATOS#";
            SummaryTitle.append(AString.Repeat(HeaderUnderline.length() , '*'));
            int len = SummaryTitle.length() / 2;
            SummaryTitle.replace(len - Title3.length() / 2 , len + Title3.length() / 2 , Title3);
            System.out.println("\n" + SummaryTitle);

            String FooterLine = HeaderUnderline.toString().replaceAll(" " , "-");
            FooterLine = FooterLine.replaceAll("-" , "*");
            //if(!Summaries.isEmpty()) System.out.println();
            int lonFooter = FooterLine.length();

            for (dtSummary ds : Summaries) {
                StringBuilder CadenaSumario = new StringBuilder();
                /*
                 * Obtiene:
                 *  titulo
                 *  nombre
                 *  regla lambda a aplicar al sumario
                 */
                String title = ds.getTitle();
                if(title.length()>=lonFooter) title=title.substring(0,lonFooter/2).concat("(###)");
                String field = ds.getField();
                Function<Object[], Object> rule = ds.getFieldSummary();

                // Si el campo listable es erroneo (no esta en Listable) excepción.
                if ( !(get(0)).getData().containsKey(field) )
                    throw new FormattedListException("\nField '" + field + "' in the summary '" + title + "' does not exist in the listable class.");

                // Mapea el campo a resumir en un array de Object.
                Object[] datos = this.stream().
                        map(
                                o -> o.getData().get(field))
                        .toArray();

                // Aplica la regla de resumen especificada desde afuera.
                // en el objeto dtSummary sobre datos.
                // Object es el resultado numérico del resumen.
                Object result = rule.apply(datos);
                OptionalDouble ressume;
                String resStr;

                // Compone la cadena de sumario
                if ( result instanceof OptionalDouble ) {
                    Integer decimals = this.Header.get(field).getMaxDecimals();
                    ressume = ((OptionalDouble) result);
                    if ( ressume.isPresent() ) result = ressume.getAsDouble();
                    resStr = String.format("%." + (decimals != null ? decimals.toString() : "2") + "f" , result);
                } else {
                    resStr = result.toString();
                }
                CadenaSumario.append(title);
                int lenTitle = title.length();
                int c=lonFooter - lenTitle - resStr.length();
                String puntos = AString.Repeat(c<0?1:c, '.');
                CadenaSumario.append(puntos).append(resStr);
                System.out.println(CadenaSumario.toString());
            }
            System.out.println(FooterLine);
        } else System.out.println();

        // Petición de página para paginado
        if(this.pageSize!=null && this.size()>this.pageSize){
            long resto = this.size()%this.pageSize;
            long paginas= (long) this.size() /this.pageSize;
            int page;
            if(resto!=0) paginas+=1;
            if(!bNextPage) {
                String mensajeP;
                do {
                   mensajeP="Página "+(actualPage+1)+"/"+paginas+". Elija página (1-"+paginas+") (0=Termina):";

                    Input.fromConsole(mensajeP ,
                                      new IntMinMaxVF(
                                              0 ,
                                              (int) paginas ,
                                              "Página incorrecta.") ,
                                     true ,
                                     true);
                    page = Input.GetLastInteger();
                    if ( page != 0 ) {
                        this.actualPage = page - 1;
                        this.bNextPage = true;
                        toConsole(this.Title);
                    }
                } while (page != 0);
                System.out.println("Fín del listado.");
                this.bNextPage=false;
            }
        }
    }

    /**
     * A&ntilde;ade un resumen a la tabla
     * @param field Campo mapeado en la interface "Listable".
     * @param Title T&iacute;tulo de la columna
     * @param SummaryOperation Operaci&oacute;n Function lambda sobre el Array de Object que obtiene el dato resumen.
     */
    public void addSummary(String field, String Title, Function<Object[],Object> SummaryOperation){
        dtSummary newSummary=new dtSummary(field,Title,SummaryOperation);
        Summaries.add(newSummary);
    }
}
