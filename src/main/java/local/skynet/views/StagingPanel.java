package local.skynet.views;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;

public class StagingPanel extends JPanel {

    private final String ROOT_DIR = "Z:/POS Software/";
    private final String DEST_DIR = "C:/Staging/";
    private String till;
    private String primaryHostName;



    public StagingPanel() {

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);


        //Create needed components
        JLabel lblHostname = new JLabel("Hostname");
        JLabel lblJDACode = new JLabel("JDA Code");
        JLabel lblPrimaryCheck = new JLabel("Primary Till ?");
        JLabel lblPrimaryHostName = new JLabel("Primary Till Hostname");
        JLabel lblTillNumber = new JLabel("Till Number");

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
            new Thread(()->{
                CommandLine cmd = CommandLine.parse("cmd.exe /wait /c 'C:\\Staging\\3. Xstore\\install.bat'");

                DefaultExecutor executor = new DefaultExecutor();
                executor.setExitValue(0);
                try {
                    int exitValue = executor.execute(cmd);
                    System.out.println(exitValue);
                    if(exitValue == 0){
                        JOptionPane.showMessageDialog(this, "XSTORE Has been installed, REMEMBER TO DELETE XSTORE FOLDER");
                    }else{
                        JOptionPane.showMessageDialog(this, "XSTORE INSTALLATION FAILED");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }).start();

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



        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblHostname)
                        .addComponent(lblJDACode)
                        .addComponent(lblPrimaryCheck)
                        .addComponent(lblPrimaryHostName)
                        .addComponent(lblTillNumber)
                        .addComponent(fetchFiles)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(hostname)
                        .addComponent(jdaCode)
                        .addComponent(primary)
                        .addComponent(primaryHost)
                        .addComponent(tillNumber)
                        .addComponent(installDB)
                )
                .addComponent(installXE)
                .addComponent(installXS)
        );

        layout.linkSize(fetchFiles,installDB,installXE,installXS);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHostname)
                        .addComponent(hostname)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblJDACode)
                        .addComponent(jdaCode)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPrimaryCheck)
                        .addComponent(primary)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPrimaryHostName)
                        .addComponent(primaryHost)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTillNumber)
                        .addComponent(tillNumber)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fetchFiles)
                        .addComponent(installDB)
                        .addComponent(installXE)
                        .addComponent(installXS)
                )
        );

    }

}
