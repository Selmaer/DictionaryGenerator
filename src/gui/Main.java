package gui;

import dictionarygenerator.Dictionary;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
//        Controller.dictExt.setValue(FXCollections.observableArrayList(".txt", ".dic"));
        primaryStage.setTitle("Dictionary Generator");
        Scene scene = new Scene(root, 700, 370);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        Controller.setPrimaryStage(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
