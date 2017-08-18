package local.skynet.controls;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import javax.swing.*;
import java.io.IOException;

public class InstallComMa32 implements InstallationStep{
    //Install ComMa 32 with the help of install.bat located in the target Workstation

    @Override
    public void perform() {

        String command = "cmd.exe /wait /c \"C:\\Staging\\5. Comma32\\install.bat\"";

        CommandLine cmd = CommandLine.parse(command);
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        defaultExecutor.setExitValue(0);

        try {
            int exitValue = defaultExecutor.execute(cmd);

            if(exitValue == 0){
                JOptionPane.showMessageDialog(null,"ComMa32 installation successfully completed!! REMEMBER TO FINALIZE CONFIGURATION");
            }else{
                JOptionPane.showMessageDialog(null,"ComMa32 has not been installed!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
