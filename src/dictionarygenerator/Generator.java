package dictionarygenerator;

import gui.CompletedSuccessfullyDialog;
import gui.InformationDialog;
import gui.WarningDialog;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generator extends Thread {
    private final ArrayList<Symbol> LIST;
    private final File DICTIONARY;
    private Password password;
    private final int LENGTH;
    private final BufferedWriter WRITER;
    private final long COMBINATIONS_NUMBER;

    private final long PASSWORDS_PER_PERCENT;
    private long passwordCounter = 0;

    private ProgressBar bar;
    private Button generateButton;
    private Button stopButton;

    private volatile boolean flag = true;

    public void stopRunning() {
        flag = false;
    }

    public Generator(PasswordCombination passwordCombination, File dictionary) throws IOException {
        DICTIONARY = dictionary;
        password = new Password();
        LIST = passwordCombination.getSymbolList();
        LENGTH = LIST.size();
        WRITER = new BufferedWriter(new FileWriter(dictionary));
        COMBINATIONS_NUMBER = passwordCombination.getCombinationsNumber();
        PASSWORDS_PER_PERCENT = COMBINATIONS_NUMBER / 100;
}

    public void run() {
        long timeStart = System.currentTimeMillis();

        generateButton.setVisible(false);
        stopButton.setVisible(true);

        generate(0);

        bar.setProgress(0);
        stopButton.setVisible(false);
        generateButton.setVisible(true);

        passwordCounter = 0;
        long timeFinish = System.currentTimeMillis();
        long timeSpentMs = timeFinish - timeStart;
        if(flag) {
            PropertiesFile.setPasswordsPerMillisecond(COMBINATIONS_NUMBER / timeSpentMs);
        }
        showDialog(timeSpentMs);
    }
    
    private void generate (int i) {
        if(flag) {
            if (i < LENGTH - 1) {
                while (LIST.get(i).hasNext()) {
                    password.addSymbol(LIST.get(i).getNext());
                    generate(i + 1);
                }
                password.removeLastSymbol();
            } else if (i == LENGTH - 1) {
                while (LIST.get(i).hasNext()) {
                    String s = LIST.get(i).getNext();
                    try {
                        WRITER.write(password + s);
                        WRITER.newLine();
                        WRITER.flush();
                        passwordCount();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                password.removeLastSymbol();
            }
        }
    }

    private void passwordCount() {
        passwordCounter++;
        if (passwordCounter >= PASSWORDS_PER_PERCENT) {
            bar.setProgress(bar.getProgress() + 0.01);
            passwordCounter = 0;
        }
    }

    private void showDialog(long timeSpentMs) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                if (flag) {
                    String text = "Dictionary created successfully in " +
                            (Calculator.msToNormalTime(timeSpentMs));
                    CompletedSuccessfullyDialog.show(text, DICTIONARY);
                } else {
                    WarningDialog.show("Dictionary creation was interrupted on " +
                            (Calculator.msToNormalTime(timeSpentMs)));
                    DICTIONARY.delete();
                }
            }
        });
    }

    public void setButtons(ProgressBar bar, Button generateButton, Button stopButton) {
        this.bar = bar;
        this.generateButton = generateButton;
        this.stopButton = stopButton;
    }
}
