package local.skynet.controls;


import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WindowsTenFix implements InstallationStep {
    private File baseXStore;
    private File win10Path;

    public WindowsTenFix(){
        baseXStore = new File("C:/Staging/3. Xstore");
        win10Path = new File("C:/dtvinst");
    }

    @Override
    public void perform() {

        //create destination Folder if not already exists
        if(!win10Path.exists()){
            if(win10Path.mkdir()) JOptionPane.showMessageDialog(null, "Directory Created");
            else JOptionPane.showMessageDialog(null,"Directory NOT Created");
        }



        List<File> xSFile = (List<File> )FileUtils.listFiles(baseXStore,null,false);

        for (File file:xSFile) {
            System.out.println(file.getName());
            if((file.getName().equals("win10patch2.bat"))||(file.getName().equals("xstore-win10-patch2.exe"))){
                try {
                    FileUtils.copyFileToDirectory(file,win10Path);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,e.toString());
                    e.printStackTrace();
                }
            }

        }

        JOptionPane.showMessageDialog(null,"Files Copied To target directory!!");
    }
}
