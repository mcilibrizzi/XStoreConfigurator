package local.skynet.views;

import javax.swing.*;

import local.skynet.controls.*;


public class StagingPanel extends JPanel {

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
        JButton installCM32 = new JButton("Install ComMa32");
        JButton installFintrax = new JButton("Install PI Offline");

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

        fetchFiles.addActionListener((e) -> new FetchFiles().perform());
        installDB.addActionListener((e)  -> new InstallDB().perform());
        installXS.addActionListener((e) -> {
            new Thread(()->new InstallXS().perform()).start();
        });
        installXE.addActionListener((e) -> {
            InstallXE installXE1 = new InstallXE();

            installXE1.getVales(primaryHost.getText(),tillNumber.getText(),primary.isSelected(),jdaCode.getText(),hostname.getText());
            installXE1.perform();
        });
        installCM32.addActionListener((e)-> new InstallComMa32().perform());
        installFintrax.addActionListener((e -> System.out.println("FINTRAX")));


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
                        .addComponent(installCM32)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(hostname)
                        .addComponent(jdaCode)
                        .addComponent(primary)
                        .addComponent(primaryHost)
                        .addComponent(tillNumber)
                        .addComponent(installDB)
                        .addComponent(installFintrax)
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(installCM32)
                        .addComponent(installFintrax)
                )
        );

    }

}