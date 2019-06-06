package application.gui;

import application.dictionarygenerator.*;
import application.gui.Dialogs.ConfirmationDialog;
import application.gui.Dialogs.WarningDialog;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public class GenerateButton {
    private static ProgressBar bar;
    private static Button generateButton;
    private static Button stopButton;

    public static void setButtons(ProgressBar pb, Button gb, Button sb) {
        bar = pb;
        generateButton = gb;
        stopButton = sb;
    }

    public static void generate(String passwordMask) {
        try {
            PasswordCombination pc = new PasswordCombination(passwordMask);
            long combNumber = pc.getCombinationsNumber();

            String combNumberReadable = Calculator.splitOnThousands(combNumber);
            String confMessage = "There will be " + combNumberReadable + " passwords in your dictionary.\n" +
                    "It will take approximately " + (Calculator.calculateApproxTime(combNumber)) + " to create it.\n" +
                    "Do you want to continue?";
            if (ConfirmationDialog.show(confMessage)) {
                Dictionary dict = new Dictionary();
                Generator generator = new Generator(pc, dict.getDictionary());
                generator.setButtons(bar, generateButton, stopButton);
                generator.start();

                stopButton.setOnAction(event -> {
                    String message = "Are you sure you want to abort the process?";
                    if (ConfirmationDialog.show(message)) {
                        generator.stopRunning();
                    }
                });
            }

        } catch (InvalidPathException e) {
            WarningDialog.show("Dictionary path is invalid.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

