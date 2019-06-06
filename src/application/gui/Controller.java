package application.gui;

import application.Main;
import application.dictionarygenerator.PropertiesFile;
import application.gui.Dialogs.InformationDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField pass_mask_field;

    @FXML
    private TextField dict_name_field;

    @FXML
    private Button dict_browse_button;

    @FXML
    private ChoiceBox dictExt;

    @FXML
    private TextField dict_path_field;

    @FXML
    private Button hint_button;

    @FXML
    private Button stop_button;

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
            String dictionaryPath = BrowseButton.choosePath(Main.getStage());
            PropertiesFile.setDirectoryPath(dictionaryPath);
            dict_path_field.setText(PropertiesFile.getDirectoryPath());
            dict_path_field.selectPositionCaret(dict_path_field.getLength());
        });

        hint_button.setOnAction(event -> InformationDialog.show("I have a great message for you!"));

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

        generate_button.setOnAction(event -> {
            if (!dict_name_field.getText().isEmpty()) {
                PropertiesFile.setFileName(dict_name_field.getText());
            }

            if (!dict_path_field.getText().isEmpty()) {
                PropertiesFile.setDirectoryPath(dict_path_field.getText());
            } else if(PropertiesFile.getDirectoryPath().equals("")){
                String dictionaryPath = BrowseButton.choosePath(Main.getStage());
                PropertiesFile.setDirectoryPath(dictionaryPath);
                dict_path_field.setText(PropertiesFile.getDirectoryPath());
                dict_path_field.selectPositionCaret(dict_path_field.getLength());
            }
            if (!PropertiesFile.getDirectoryPath().isEmpty()) {
                String passwordMask = pass_mask_field.getText();
                GenerateButton.setButtons(progress_bar, generate_button, stop_button);
                GenerateButton.generate(passwordMask);
            }
        });

        dictExt.setOnAction(event ->
                PropertiesFile.setFileExtension(dictExt.getValue().toString()));
    }
}