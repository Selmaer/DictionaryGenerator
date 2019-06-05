package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == open) {
            try {
                Runtime.getRuntime().exec("explorer.exe /select, " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
        }
    }
}
