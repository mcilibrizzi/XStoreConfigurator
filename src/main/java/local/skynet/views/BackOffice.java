package local.skynet.views;

import local.skynet.controls.UninstallOffice;

import javax.swing.*;

public class BackOffice extends JPanel {
     private JLabel officeLabel;
     private JButton officeBtn;


     public BackOffice(){
         officeLabel = new JLabel("Uninstall Office 2007");
         officeBtn = new JButton("Uninstall");

         //Set Panel layout
         GroupLayout groupLayout = new GroupLayout(this);

         setLayout(groupLayout);

         groupLayout.setAutoCreateGaps(true);
         groupLayout.setAutoCreateContainerGaps(true);

         groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
            .addComponent(officeLabel)
            .addComponent(officeBtn)
         );

         groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(officeLabel)
                .addComponent(officeBtn)
            )
         );

         officeBtn.addActionListener((e)-> new UninstallOffice().perform());


     }
}
