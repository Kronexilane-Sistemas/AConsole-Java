package org.kronexilane.AConsole.commandline;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase estática que gestiona la línea
 * de comandos de Java y clasifica los parámetros.
 */
public class CLineInterpreter {
    // Lista de parámetros
    private static HashMap<String,CLineAction> CMDLOptions;
    // Clase estática no se puede instanciar
    private CLineInterpreter(){}

    /**
     * Añade elementos al intérprete de línea de comandos
     * @param parameter Parámetro
     * @param action Objeto CLineAction que define la acción
     */
    public static void Add(String parameter, CLineAction action){
        if(CMDLOptions==null) CMDLOptions=new HashMap<String,CLineAction>();
        CMDLOptions.put(parameter,action);

    }

    /**
     * Interpretador de la línea de comandos
     * @param args Matriz de parámetros de la función main(String[] args)
     * @param SystemSeparator separador de sistema (-,/ o el que se decida)
     */
    public static void Interpreter(String[] args,String SystemSeparator) {
        try {
            // Si no hay argumentos tratamos de buscar el "default" y lo ejecutamos
            if(args.length==0){
                CMDLOptions.get("default").DoIt("default",null);
            }else {
                String Commands = String.join(" ", args);
                if(!Commands.contains(SystemSeparator)){
                    throw new IllegalArgumentException();
                }
                String[] argsNormalize = Commands.split(SystemSeparator);
                List<String> commands = Arrays.stream(argsNormalize).collect(Collectors.toList());
                // Quitamos el primero (espacio vacio)
                commands.remove(0);

                // Procesa los comandos
                commands.forEach(CLineInterpreter::process);
            }
        }catch (IndexOutOfBoundsException|IllegalArgumentException|NullPointerException ignored){
            System.out.println("Ningún parámetro específicado");
        }
    }

    // Procesa el comando
    private static void process(String e) {

        // Obtiene el comando y subcomandos del propio comando en curso
        String[] subCommands=e.split(" ");
        String command=subCommands[0];
        List<String> subParams= Arrays.stream(subCommands).collect(Collectors.toList());
        subParams.remove(0);
        try {
            CLineAction execute=CMDLOptions.get(command);
            if(!execute.isExecuted()) {
                CMDLOptions.get(command).DoIt(command,subParams);
            }
        }catch(NullPointerException ignored){
            System.out.println("parámetro '"+command+"' no existe.");

        }
    }
}
