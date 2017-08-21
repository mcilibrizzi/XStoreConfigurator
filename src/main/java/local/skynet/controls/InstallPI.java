package local.skynet.controls;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import javax.swing.*;
import java.io.IOException;

public class InstallPI implements InstallationStep {
    @Override
    public void perform() {

        String command = "cmd.exe /wait /c \"C:\\Staging\\4. PI Offline\\install.bat\"";

        CommandLine cmd = CommandLine.parse(command);
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        defaultExecutor.setExitValue(0);

        try {
            int exitValue;
            exitValue = defaultExecutor.execute(cmd);

            if(exitValue == 0){
                JOptionPane.showMessageDialog(null,"PI Offline has been installed!!");
            }else{
                JOptionPane.showMessageDialog(null,"PI Offile has NOT been installed!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
