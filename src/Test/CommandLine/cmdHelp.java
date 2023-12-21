package Test.CommandLine;

import org.kronexilane.AConsole.Output;
import org.kronexilane.AConsole.commandline.CLineAction;
import org.kronexilane.AConsole.msgboxStyles.mbARROBAS;

import java.util.List;

public class cmdHelp extends CLineAction{
    @Override
    public void DoIt(String parameter, List<String> subParams) {
        String msg="Esto es un programa de prueba del paquete\ncommandline para gestionar\nparámetros de línea de comandos.";
        Output.setBox(new mbARROBAS());
        Output.msgBox(msg);
        System.out.println("-h Muestra este texto de ayuda");
        System.out.println("-1 Opción número uno");
        System.out.println("-2 Opción número dos con subparámetros");
        //super.executed=true;
    }
}
