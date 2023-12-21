package Test.CommandLine;

import org.kronexilane.AConsole.Output;
import org.kronexilane.AConsole.commandline.CLineAction;
import org.kronexilane.AConsole.msgboxStyles.mbARROBAS;
import org.kronexilane.AConsole.msgboxStyles.mbELEGANT;

import java.util.List;

public class cmdDefault extends CLineAction{
    @Override
    public void DoIt(String parameter, List<String> subParams) {
        String msg="Esta opción se ejecuta siempre sin ningún parámetro (DEFECTO)";
        Output.setBox(new mbELEGANT());
        Output.msgBox(msg);
        super.executed=true;
    }
}
