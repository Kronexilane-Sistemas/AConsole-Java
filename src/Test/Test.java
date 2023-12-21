package Test;

import Test.CommandLine.cmdDefault;
import Test.CommandLine.cmdHelp;
import Test.CommandLine.cmdOption1;
import Test.CommandLine.cmdOption2;
import org.kronexilane.AConsole.commandline.CLineInterpreter;


public class Test {
    public static void main(String[] args){
        // Crea los objetos del programa llamables mediante parámetros
        CLineInterpreter.Add("h",new cmdHelp());
        CLineInterpreter.Add("1",new cmdOption1());
        CLineInterpreter.Add("2",new cmdOption2());
        CLineInterpreter.Add("default",new cmdDefault());
        // Analiza la línea de comandos y lanza el o los objetos de los parámetros
        // de línea de comandos.
        CLineInterpreter.Interpreter(args,"-");
    }
}
