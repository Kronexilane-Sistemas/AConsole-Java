package Test.CommandLine;

import org.kronexilane.AConsole.Output;
import org.kronexilane.AConsole.commandline.CLineAction;
import org.kronexilane.AConsole.msgboxStyles.mbARROBAS;

import java.util.List;

public class cmdOption1 extends CLineAction{
    @Override
    public void DoIt(String parameter, List<String> subParams) {
        System.out.println("Está es la opción 1");
        super.executed=true;
    }
}
