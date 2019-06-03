package dictionarygenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Generator {
    private final ArrayList<Symbol> LIST;
    private Password password;
    private final int LENGTH;
    private final BufferedWriter WRITER;

    public Generator(PasswordCombination passwordCombination, BufferedWriter bufferedWriter) {
        password = new Password();
        LIST = passwordCombination.getSymbolList();
        LENGTH = LIST.size();
        WRITER = bufferedWriter;
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
                /////////////////////////////////
                try {
                    WRITER.write(password + s +"\n");
                    WRITER.newLine();
                    WRITER.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
//                System.out.println(password + s);
                /////////////////////////////////
            }
            password.removeLastSymbol();
        }
    }
    
    
}
