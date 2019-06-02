package gui;

import java.net.URL;
import java.util.ResourceBundle;

import dictionarygenerator.Dictionary;
import dictionarygenerator.PropertiesFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    public static final ObservableList<String> strings = FXCollections.observableArrayList(".txt", ".dic");
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }


    @FXML
    private TextField pass_mask_field;

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField dict_name_field;

    @FXML
    private Button dict_browse_button;

    @FXML
    public static ChoiceBox dictExt;

    @FXML
    private URL location;

    @FXML
    private TextField dict_path_field;

    @FXML
    private Button hint_button;

    @FXML
    private Button digits_button;

    @FXML
    private Button punct_symbol_button;

    @FXML
    private Button uppercase_button;

    @FXML
    private Button lowercase_button;

    @FXML
    private Button splitter_button;

    @FXML
    private Button calculate_button;

    @FXML
    private ProgressBar progress_bar;

    @FXML
    private Button generate_button;

    @FXML
    void initialize() {
        dict_name_field.setOnMouseClicked(event -> {
            if (dict_name_field.getText().isEmpty()) {
                dict_name_field.setText(PropertiesFile.getFileName());
                dict_name_field.selectPositionCaret(dict_name_field.getLength());
            }
        });

        dict_path_field.setOnMouseClicked (event -> {
            if (dict_path_field.getText().isEmpty()) {
                dict_path_field.setText(PropertiesFile.getDirectoryPath());
                dict_path_field.selectPositionCaret(dict_path_field.getLength());
            }
        });

        dict_browse_button.setOnAction(event -> {
            String dictionaryPath = Dictionary.choosePath(primaryStage);
            PropertiesFile.setDirectoryPath(dictionaryPath);
            dict_path_field.setText(PropertiesFile.getDirectoryPath());
            dict_path_field.selectPositionCaret(dict_path_field.getLength());

        });

        hint_button.setOnAction(event -> {
            InformationDialog.showInformation("I have a great message for you!\nhjgjhfcghcghnb j\njhggdtffdxhggfjchgv\njhfdhgfxghckhjv\nhjvjhgvchv");
        });

        digits_button.setOnAction(event -> {
            pass_mask_field.setText(pass_mask_field.getText() + "^D");
            pass_mask_field.positionCaret(pass_mask_field.getText().length());
            });

        punct_symbol_button.setOnAction(event -> {
            pass_mask_field.setText(pass_mask_field.getText() + "^P");
            pass_mask_field.positionCaret(pass_mask_field.getText().length());
            });

        uppercase_button.setOnAction(event -> {
            pass_mask_field.setText(pass_mask_field.getText() + "^U");
            pass_mask_field.positionCaret(pass_mask_field.getText().length());
            });

        lowercase_button.setOnAction(event -> {
            pass_mask_field.setText(pass_mask_field.getText() + "^L");
            pass_mask_field.positionCaret(pass_mask_field.getText().length());
            });

        splitter_button.setOnAction(event -> {
            pass_mask_field.setText(pass_mask_field.getText() + "|");
            pass_mask_field.positionCaret(pass_mask_field.getText().length());
            });

        calculate_button.setOnAction(event -> {
            if(pass_mask_field.getText().isEmpty()) {
                WarningDialog.showWarning("Password mask is empty.");
            }
        });

        generate_button.setOnAction(event -> {
            if(pass_mask_field.getText().isEmpty()) {
                WarningDialog.showWarning("Password mask is empty.");
            }
        });

//        progress_bar.
    }
}
