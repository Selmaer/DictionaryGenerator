package dictionarygenerator;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Password {
    private final LinkedList<String> LIST;
    private final StringBuilder s;
    
    public Password () {
         LIST = new LinkedList<>();
         s = new StringBuilder();
    }
    public void add(String s) {
        LIST.add(s);
    }
    
    public void removeLast() {
        try {
            LIST.removeLast();
        } catch (NoSuchElementException e) {}
    }
    @Override
    public String toString() {
        s.setLength(0);
        for (String a : LIST) {
            s.append(a);
        }
        return s.toString();
    }
}   