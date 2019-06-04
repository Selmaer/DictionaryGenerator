package gui;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class BrowseButton {
    public static String choosePath (Stage primaryStage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Browse your dictionary");
//        File defaultDirectory = new File(PropertiesFile.getDirectoryPath());
//        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(primaryStage);
        if (selectedDirectory != null) {
            return selectedDirectory.getPath();
        } else {
            return "";
        }
    }

}