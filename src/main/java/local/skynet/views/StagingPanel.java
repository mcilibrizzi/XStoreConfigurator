package local.skynet.views;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.io.FileUtils;

public class StagingPanel extends JPanel {

    private final String ROOT_DIR = "Z:/POS Software/";
    private final String DEST_DIR = "C:/Staging/";
    private String till;
    private String primaryHostName;



    public StagingPanel() {

        setLayout(new GridBagLayout());


        //Create needed components
        JTextField hostname = new JTextField(20);
        JTextField jdaCode = new JTextField(20);
        JTextField primaryHost = new JTextField(20);
        JTextField tillNumber = new JTextField(5);

        JButton fetchFiles = new JButton("Fetch Files");
        JButton installDB = new JButton("Install DB");
        JButton installXS = new JButton("Install XS");
        JButton installXE = new JButton("Install XE");

        JCheckBox primary = new JCheckBox();
        primary.setSelected(false);

        primary.addItemListener((i)->{
            if(primary.isSelected()){
                primaryHost.setEditable(false);
                tillNumber.setEditable(false);
            }else {
                primaryHost.setEditable(true);
                tillNumber.setEditable(true);
            }
        });




        //Add Button Functionality

        fetchFiles.addActionListener((e) -> {
            //copy needed Files under C:\Staging
            try {
                FileUtils.copyDirectory(new File(ROOT_DIR), new File(DEST_DIR));
                JOptionPane.showMessageDialog(this, "All the files have been retrieved");

            } catch (IOException IOE) {
                IOE.printStackTrace();
            }
        });
        installDB.addActionListener((e) -> {
            try {

                Runtime.getRuntime().exec("cmd start /c \"cd C:\\Staging\\1. SQL Server\\ & SQLSERVER2012_INSTALL_SCRIPT.bat\"");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        installXS.addActionListener((e) -> {
            Process p = null;
            try {
                p = Runtime.getRuntime().exec("cmd  start /wait /c \"cd C:\\Staging\\3. Xstore\\ & install.bat\"");
                p.waitFor();
                //
                JOptionPane.showMessageDialog(this, "XSTORE has been installed");
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        installXE.addActionListener((e) -> {
            try {
                Path systemProp;
                Process p = Runtime.getRuntime().exec("cmd  start /wait /c \"C:\\Staging\\2. Environment\\EMEAI_environment_no_delay_1.2.1.exe\"");

                p.waitFor();
                // Now we can begin with configuration

                if(primary.isSelected()){
                    //Modify LEAD
                    systemProp = Paths.get("C:\\environment\\LEAD.system.properties");
                    till="1";
                    primaryHostName = hostname.getText();

                }else{
                    //Modify NON LEAD
                    systemProp = Paths.get("C:\\environment\\NONLEAD.system.properties");
                    till = tillNumber.getText();
                    primaryHostName = primaryHost.getText();
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
                        fileContent.set(i,String.format("environment.storenum=%s",jdaCode.getText()));
                    }
                }

                //write File changes
                Files.write(Paths.get("C:\\environment\\system.properties"),fileContent,StandardCharsets.UTF_8);

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });


        //Laying out components on the screen
        GridBagConstraints c = new GridBagConstraints();

        c.weighty = 0.25;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("HOSTNAME"), c);
        c.gridy++;
        add(new JLabel("JDA Code"), c);
        c.gridy++;
        add(new JLabel("Primary Till"), c);


        c.gridx = 1;
        c.gridy = 0;
        add(hostname, c);
        c.gridy++;
        add(jdaCode, c);
        c.gridy++;
        add(primary, c);
        c.gridx++;
        add(primaryHost,c);
        c.gridx++;
        add(tillNumber,c);

        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 0.5;
        c.gridy = 3;
        c.gridx = 0;
        add(fetchFiles, c);
        c.gridx++;
        add(installDB, c);
        c.gridx++;
        add(installXS, c);
        c.gridx++;
        add(installXE, c);


    }

}
