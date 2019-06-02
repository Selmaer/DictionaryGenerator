package dictionarygenerator;

import java.util.ArrayList;

public class Generator {
    private final ArrayList<Symbol> LIST;
    public Password password;
    private final int LENGTH;
    
    public Generator(ArrayList<Symbol> list) {
        password = new Password();
        LIST = list;
        LENGTH = LIST.size();
    }
    
    public void passwordFiller() {
        generate(0);
    }
    
    public void generate (int i) {
        if (i < LENGTH - 1) {
            while (LIST.get(i).hasNext()) {
                password.add(LIST.get(i).getNext());
                generate(i + 1);
            }
            password.removeLast();

        } else if (i == LENGTH - 1) {
            while (LIST.get(i).hasNext()) {
                String s = LIST.get(i).getNext();
                System.out.println(password + s);
            }
            password.removeLast();
        }
    }
    
    
}
