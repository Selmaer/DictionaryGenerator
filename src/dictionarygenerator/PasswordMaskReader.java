package dictionarygenerator;

import java.util.ArrayList;

public class PasswordMaskReader {
    private final String PASSWORD_MASK;
    public ArrayList<Symbol> symbolList;
    
    public PasswordMaskReader(String passwordMask) {
        this.PASSWORD_MASK = passwordMask;
        String[] maskArray = maskReader();
        symbolList = new ArrayList<>();
        for(String mask : maskArray) {
            Symbol s = new Symbol(mask);
            symbolList.add(s);
        }
    }
    
    private String[] maskReader() {
        String[] maskArray = PASSWORD_MASK.split("|");
        return maskArray;
    }

}