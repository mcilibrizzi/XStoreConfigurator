package local.skynet.controls;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.environment.EnvironmentUtils;

import javax.swing.*;
import java.io.IOException;

public class InstallXS implements InstallationStep {
    @Override
    public void perform() {
        CommandLine cmd = CommandLine.parse("cmd.exe /wait /c 'C:\\Staging\\3. Xstore\\install.bat'");

        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(0);
        try {
            System.out.println(EnvironmentUtils.getProcEnvironment().toString());
            int exitValue = executor.execute(cmd, EnvironmentUtils.getProcEnvironment());
            System.out.println(exitValue);
            if(exitValue == 0){
                JOptionPane.showMessageDialog(null, "XSTORE Has been installed, REMEMBER TO DELETE XSTORE FOLDER");
            }else{
                JOptionPane.showMessageDialog(null, "XSTORE INSTALLATION FAILED");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

