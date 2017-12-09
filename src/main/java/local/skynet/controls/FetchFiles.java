package local.skynet.controls;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FetchFiles implements  InstallationStep {

    private final String ROOT_DIR = "Z:/POS Software/";
    private final String DEST_DIR = "C:/Staging/";

    private final String BACKOFFICE_SOURCE  =   "Z:/BO Software/Office 2007";
    private final String BACKOFFICE_DEST    =   "C:/POS-Software-Sources/Office 2007";

    @Override
    public void perform() {
        try {
            FileUtils.copyDirectory(new File(ROOT_DIR), new File(DEST_DIR));
            //Once copied all front office files we need to copy back office tools
            FileUtils.copyDirectory(new File(BACKOFFICE_SOURCE),new File(BACKOFFICE_DEST));
            JOptionPane.showMessageDialog(null, "All the files have been retrieved");

        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }
}
