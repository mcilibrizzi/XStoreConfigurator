package local.skynet.views;

import local.skynet.tools.Copy;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class StagingPanel extends JPanel {

    private final String ROOT_DIR = "Z:/POS Software/";
    private final String DEST_DIR = "C:/Staging/";

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
                   FileUtils.copyDirectory(new File(ROOT_DIR),new File(DEST_DIR));
                   JOptionPane.showMessageDialog(this,"All the files have been retrieved");

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
