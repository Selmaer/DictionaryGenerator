package application;

import application.dictionarygenerator.PropertiesFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    public static Stage getStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/mainWindow.fxml"));

        this.primaryStage = primaryStage;

        Parent root = loader.load();

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon/icon.png")));

        primaryStage.setTitle("Dictionary Generator");
        Scene scene = new Scene(root, 700, 310);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();


        primaryStage.setOnCloseRequest(event -> {
            PropertiesFile.setFileExtension(".txt");
        });
    }
}
