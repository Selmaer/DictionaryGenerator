package application.gui.Dialogs;

import application.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class CompletedSuccessfullyDialog {
    public static void show (String text, File file) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(text);

        ButtonType open = new ButtonType("Open");
        ButtonType close = new ButtonType("Close");

        alert.getButtonTypes().setAll(open, close);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon/icon.png")));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == open) {
            try {
                Runtime.getRuntime().exec("explorer.exe /select, " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
