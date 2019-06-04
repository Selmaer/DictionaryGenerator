package gui;

import dictionarygenerator.*;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public class GenerateButton {

    public static void generate (String passwordMask) {
        if(passwordMask.isEmpty()) {
            WarningDialog.show("Password mask is empty.");
        } else {
            try {
                long timeStart = System.currentTimeMillis();
                PasswordCombination pc = new PasswordCombination(passwordMask);
                long combNumber = pc.getCombinationsNumber();

                String combNumberReadable = Calculator.splitOnThousands(combNumber);
                String confMessage = "There will be " + combNumberReadable + " passwords in your dictionary.\n" +
                        "It will take approximately " + (Calculator.calculateApproxTime(combNumber)) + " to create it.\n" +
                        "Do you want to continue?";
                if (ConfirmationDialog.show(confMessage)) {
                    Dictionary dict = new Dictionary();
                    Generator gen = new Generator(pc, dict.getDictionary());
                    gen.generate();

                    long timeFinish = System.currentTimeMillis();

                    long timeSpentMs = timeFinish - timeStart;
                    InformationDialog.show("Dictionary created successfully in " +
                            (Calculator.msToNormalTime(timeSpentMs)));
                    PropertiesFile.setPasswordsPerMillisecond(pc.getCombinationsNumber() / timeSpentMs);
                }
            } catch (InvalidPathException e) {
                WarningDialog.show("Dictionary path is invalid.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
