package local.skynet.controls;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.environment.EnvironmentUtils;
import javax.swing.*;
import java.io.IOException;


public class UninstallOffice implements InstallationStep{
    @Override
    public void perform() {
        try {
                CommandLine cmd = CommandLine.parse("cmd.exe /wait /c \"c:\\POS-Software-Sources\\Office 2007\\uninstall.bat\"");
                DefaultExecutor executor = new DefaultExecutor();
                executor.setExitValue(0);

                int exitValue = executor.execute(cmd, EnvironmentUtils.getProcEnvironment());

                if(exitValue == 0){
                    JOptionPane.showMessageDialog(null, "Office 2007 has been successfully uninstalled!");
                }else {
                    JOptionPane.showMessageDialog(null, "Office 2007 has NOT been uninstalled!! Please check your configuration Path");
                }
        }catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
