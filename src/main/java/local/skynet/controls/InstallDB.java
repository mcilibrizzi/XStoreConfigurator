package local.skynet.controls;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.environment.EnvironmentUtils;

import javax.swing.*;
import java.io.IOException;

public class InstallDB implements InstallationStep {
    @Override
    public void perform() {
        try {

            CommandLine cmd = CommandLine.parse("cmd.exe /wait /c \"cd C:\\Staging\\1. SQL Server\\ & SQLSERVER2012_INSTALL_SCRIPT.bat\"");

            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(0);

            int exitValue = executor.execute(cmd, EnvironmentUtils.getProcEnvironment());

            if(exitValue == 0){
                JOptionPane.showMessageDialog(null, "Microsoft SQL Server Files has been installed!!");
            }else {
                JOptionPane.showMessageDialog(null, "Microsoft SQL Server Files has NOT been installed!!");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
