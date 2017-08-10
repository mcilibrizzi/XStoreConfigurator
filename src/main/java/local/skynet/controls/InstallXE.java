package local.skynet.controls;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class InstallXE implements InstallationStep {
    private String till;

    private String tillNumber;
    private String primaryHostName;
    private boolean primary;
    private String jdaCode;
    private String hostName;

    @Override
    public void perform() {
        try {
            Path systemProp;
            Process p = Runtime.getRuntime().exec("cmd /wait /c \"C:\\Staging\\2. Environment\\EMEAI_environment_no_delay_1.2.1.exe\"");

            p.waitFor();
            // Now we can begin with configuration

            if(primary){
                //Modify LEAD
                systemProp = Paths.get("C:\\environment\\LEAD.system.properties");
                till="1";
                primaryHostName = hostName;

            }else{
                //Modify NON LEAD
                systemProp = Paths.get("C:\\environment\\NONLEAD.system.properties");
                till = tillNumber;
                primaryHostName = primaryHostName;
            }

            //rename File
            Files.move(systemProp,systemProp.resolveSibling("system.properties"), StandardCopyOption.REPLACE_EXISTING);
            //edit File
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("C:\\environment\\system.properties"), StandardCharsets.UTF_8));

            for (int i = 0;i<fileContent.size();i++){
                if(fileContent.get(i).equals("environment.regnum=")){
                    fileContent.set(i,String.format("environment.regnum=%s",till));
                }
                if(fileContent.get(i).equals("environment.lead.name=")){
                    fileContent.set(i,String.format("environment.lead.name=%s",primaryHostName));
                }
                if(fileContent.get(i).equals("environment.storenum=")){
                    fileContent.set(i,String.format("environment.storenum=%s",jdaCode));
                }
            }

            //write File changes
            Files.write(Paths.get("C:\\environment\\system.properties"),fileContent,StandardCharsets.UTF_8);
            JOptionPane.showMessageDialog(null, "Environment has been installed and configured!!");

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void getVales(String pName,String tNumber, boolean pCheck,String JDA,String hName){
        this.primaryHostName = pName;
        this.tillNumber = tNumber;
        this.primary = pCheck;
        this.jdaCode = JDA;
        this.hostName = hName;
    }
}
