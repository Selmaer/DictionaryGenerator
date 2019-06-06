package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));

        this.primaryStage = primaryStage;

        Parent root = loader.load();

        primaryStage.getIcons().add(new Image ("file:res/img/icon.png"));
//        primaryStage.getIcons().add(new Image (Main.class.getResourceAsStream("file:res/img/icon.png")));

        primaryStage.setTitle("Dictionary Generator");
        Scene scene = new Scene(root, 700, 310);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    static Stage getStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
