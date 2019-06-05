package gui;

import dictionarygenerator.Dictionary;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

public class Main extends Application {
    private static Stage primaryStage;
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));

        this.primaryStage = primaryStage;
        this.controller = loader.getController();

        Parent root = loader.load();

//        primaryStage.setIconified(true);
        Image image = new Image("file:icon.png");


        primaryStage.getIcons().add(image);

//        setStageIcon(primaryStage);
        primaryStage.setTitle("Dictionary Generator");
        Scene scene = new Scene(root, 700, 310);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);


//        System.out.
        primaryStage.show();
    }
    public static final String ICON_IMAGE_LOC = "nobomb.png";
    public static void setStageIcon(Stage stage) {

        stage.getIcons().add(new javafx.scene.image.Image(ICON_IMAGE_LOC));
        //import javafx.scene.image.Image;
    }

    public static Stage getStage() {
        return primaryStage;
    }
    public static Controller getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
