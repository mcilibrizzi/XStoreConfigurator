package local.skynet.controls;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FetchFiles implements  InstallationStep {

    private final String ROOT_DIR = "Z:/POS Software/";
    private final String DEST_DIR = "C:/Staging/";

    @Override
    public void perform() {
        try {
            FileUtils.copyDirectory(new File(ROOT_DIR), new File(DEST_DIR));
            JOptionPane.showMessageDialog(null, "All the files have been retrieved");

        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }
}
