package dictionarygenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {
    private final ArrayList<Symbol> LIST;
    private Password password;
    private final int LENGTH;
    private final BufferedWriter WRITER;
    private final long PASSWORDS_PER_PERCENT;
    private long passwordCounter = 0;

    public Generator(PasswordCombination passwordCombination, File dictionary) throws IOException {
        password = new Password();
        LIST = passwordCombination.getSymbolList();
        LENGTH = LIST.size();
        WRITER = new BufferedWriter(new FileWriter(dictionary));
        PASSWORDS_PER_PERCENT = passwordCombination.getCombinationsNumber() / 100;
}

    public void generate() {
        generate(0);
    }
    
    private void generate (int i) {
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

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            password.removeLastSymbol();
        }
    }
    private void passwordCount() {
        passwordCounter++;
        if (passwordCounter >= PASSWORDS_PER_PERCENT) {

        }

    }
    
}
