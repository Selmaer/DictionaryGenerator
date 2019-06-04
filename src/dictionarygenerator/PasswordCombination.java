package dictionarygenerator;

import java.util.ArrayList;

public class PasswordCombination {
    private final String PASSWORD_MASK;
    private final ArrayList<Symbol> SYMBOL_LIST;
    private final long COMBINATIONS_NUMBER;

    public PasswordCombination (String passwordMask) {
        this.PASSWORD_MASK = passwordMask;
        this.SYMBOL_LIST = generateSymbolList(passwordMask);
        this.COMBINATIONS_NUMBER = combinationsNumberCounter();
    }

    public String getPasswordMask() {
        return PASSWORD_MASK;
    }
    public ArrayList<Symbol> getSymbolList() {
        return SYMBOL_LIST;
    }
    public long getCombinationsNumber() {
        return COMBINATIONS_NUMBER;
    }

    private long combinationsNumberCounter() {
        long comb = 1;
        for (Symbol s : SYMBOL_LIST) {
            comb *= s.getLength();
        }
        return comb;
    }
    private ArrayList<Symbol> generateSymbolList(String passwordMask) {
        String[] maskArray = passwordMask.split(" //| ");
        ArrayList<Symbol> symbolList = new ArrayList<>(maskArray.length);
        for(String mask : maskArray) {
            Symbol s = new Symbol(mask);
            symbolList.add(s);
        }
        return symbolList;
    }
}
