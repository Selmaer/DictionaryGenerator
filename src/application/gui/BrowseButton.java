package application.gui;

import application.dictionarygenerator.PropertiesFile;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

class BrowseButton {
    static String choosePath (Stage primaryStage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Browse your dictionary");
        if (!PropertiesFile.getDirectoryPath().equals("")) {
            File defaultDirectory = new File(PropertiesFile.getDirectoryPath());
            chooser.setInitialDirectory(defaultDirectory);
        }
        File selectedDirectory = chooser.showDialog(primaryStage);
        if (selectedDirectory != null) {
            return selectedDirectory.getPath();
        } else {
            return "";
        }
    }

}
