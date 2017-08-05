package local.skynet.controls;

import java.io.IOException;

public class InstallDB implements InstallationStep {
    @Override
    public void perform() {
        try {

            Runtime.getRuntime().exec("cmd /c \"cd C:\\Staging\\1. SQL Server\\ & SQLSERVER2012_INSTALL_SCRIPT.bat\"");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
