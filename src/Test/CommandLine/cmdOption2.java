package Test.CommandLine;

import org.kronexilane.AConsole.Output;
import org.kronexilane.AConsole.commandline.CLineAction;
import org.kronexilane.AConsole.msgboxStyles.mbARROBAS;

import java.util.List;

public class cmdOption2 extends CLineAction{
    @Override
    public void DoIt(String parameter, List<String> subParams) {
        String msg="Opción 2 con subparámetros";
        Output.setBox(new mbARROBAS());
        Output.msgBox(msg);
        if(subParams!=null){
            System.out.println("Subparámetros de '"+parameter+"':");
            subParams.forEach(System.out::println);
        }
        super.Done();
    }
}
