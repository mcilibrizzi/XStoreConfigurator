package local.skynet;

import local.skynet.views.BackOffice;
import local.skynet.views.StagingPanel;
import local.skynet.views.Tools;

import javax.swing.*;

public class MainPane extends JFrame {

    public MainPane(String title){
        super(title);
        setSize(500,350);
        setResizable(false);

        //Add the tabbed Pane
        JTabbedPane paneContainer = new JTabbedPane();

        paneContainer.addTab("Staging", new StagingPanel());
        paneContainer.addTab("On Site",new JPanel());
        paneContainer.addTab("Tools",new Tools());
        paneContainer.addTab("Back Office",new BackOffice());


        add(paneContainer);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
