package gui;

import javafx.scene.control.Alert;

public class WarningDialog {
    public static void show(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
