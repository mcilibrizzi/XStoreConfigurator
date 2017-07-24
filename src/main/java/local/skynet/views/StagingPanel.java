package local.skynet.views;

import local.skynet.tools.Copy;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StagingPanel extends JPanel {

    private final String ROOT_DIR = "Z:\\POS Software\\";
    private final String DEST_DIR = "C:\\Staging\\";
    private final String TEST_DIR = "C:\\Test Environment\\";

    public StagingPanel() {


        //Files.createDirectory(Paths.get(TEST_DIR),null);
            setLayout(new GridBagLayout());


            //Create needed components
            JTextField hostname = new JTextField(20);
            JTextField jdaCode = new JTextField(20);

            JButton fetchFiles = new JButton("Fetch Files");
            JButton installDB = new JButton("Install DB");
            JButton installXS = new JButton("Install XS");
            JButton installXE = new JButton("Install XE");

            JCheckBox primary = new JCheckBox();
            primary.setSelected(false);


            //Add Button Functionality

            fetchFiles.addActionListener((e)->{
                //copy needed Files under C:\Staging
                try {
                    if(!Files.exists(Paths.get(TEST_DIR))) {
                        Files.createDirectories(Paths.get(TEST_DIR));
                    }
                    Copy.copy(ROOT_DIR+"1. SQL Server\\",DEST_DIR+"1. SQL Server\\");
                    Copy.copy(ROOT_DIR+"2. Environment\\",DEST_DIR+"2. Environment\\");
                    Copy.copy(ROOT_DIR+"3. Xstore\\",DEST_DIR+"3. Xstore\\");
                    Copy.copy(ROOT_DIR+"4. PI Offline\\",DEST_DIR+"4. PI Offline\\");
                    Copy.copy(ROOT_DIR+"5. Comma32\\",DEST_DIR+"5. Comma32\\");
                    Copy.copy(ROOT_DIR+"6. Driver DM-D110USB\\",DEST_DIR+"6. Driver DM-D110USB\\");

                } catch (IOException IOE) {
                    IOE.printStackTrace();
                }
            });
            installDB.addActionListener((e)->{
                System.out.println("Install SQL DB");
            });
            installXS.addActionListener((e)->{
                System.out.println("Install XSTORE");
            });
            installXE.addActionListener((e)->{
                System.out.println("Install XEnvironment");
            });





            //Laying out components on the screen
            GridBagConstraints c = new GridBagConstraints();

            c.weighty = 0.25;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 0;
            c.gridy = 0;
            add(new JLabel("HOSTNAME"),c);
            c.gridy++;
            add(new JLabel("JDA Code"),c);
            c.gridy++;
            add(new JLabel("Primary Till"),c);


            c.gridx = 1;
            c.gridy = 0;
            add(hostname,c);
            c.gridy++;
            add(jdaCode,c);
            c.gridy++;
            add(primary,c);

            c.anchor = GridBagConstraints.CENTER;
            c.weightx = 1;
            c.weighty = 0.5;
            c.gridy = 3;
            c.gridx=0;
            add(fetchFiles,c);
            c.gridx++;
            add(installDB,c);
            c.gridx++;
            add(installXS,c);
            c.gridx++;
            add(installXE,c);









    }
}
