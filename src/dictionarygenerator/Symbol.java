package dictionarygenerator;

import static dictionarygenerator.Symbol.SymbolKind.FIXED;
import static dictionarygenerator.Symbol.SymbolKind.PARAMETRED;

public class Symbol {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    
    private final String COMBINATION;
    private final SymbolKind KIND;
    
    private int charCount = 0;
    
    enum SymbolKind {
        FIXED, PARAMETRED;
    }

    public Symbol(String mask) {
        String comb = "";
        if (mask.contains("^D")) {
            comb = comb + DIGITS;
        } if (mask.contains("^L")) {
            comb = comb + LOWER;
        } if (mask.contains("^U")) {
            comb = comb + UPPER;
        } if (mask.contains("^P")) {
            comb = comb + PUNCTUATION;
        } 
        if (comb.isEmpty()) {
            KIND = FIXED;
            COMBINATION = mask;
        } else {
            KIND = PARAMETRED;
            COMBINATION = comb;
        }

    }
    public String getNext() {
        if (KIND == FIXED) {
            return COMBINATION;
        } else {
            return String.valueOf(COMBINATION.charAt(charCount++));
        }
    }
    public boolean hasNext() {
        if (KIND == FIXED) {
            return (charCount++ %2 == 0);
        } else if (COMBINATION.length() > charCount) {
            return true;
        } else {
            charCount = 0;
            return false;
        }
    }
    public int getLength() {
        return COMBINATION.length();
    }
}