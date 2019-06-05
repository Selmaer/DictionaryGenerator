package dictionarygenerator;

import java.util.concurrent.TimeUnit;

public class Calculator {

    public static String splitOnThousands(long number) {
        StringBuilder s = new StringBuilder ();
        s.append(number);
        for (int i = s.length() - 3; i > 0; i-=3) {
            s.insert(i, " ");
        }
        return s.toString();
    }

    public static String msToNormalTime(long ms) {
        return String.format("%02d min, %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(ms),
                TimeUnit.MILLISECONDS.toSeconds(ms) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms))
        );
    }

    public static String calculateApproxTime (long combinationNumber) {
        String passPerMs = PropertiesFile.getPasswordsPerMillisecond();
        if (passPerMs.isEmpty()) {
            return "";
        } else {
            long milliseconds = combinationNumber / Long.valueOf(passPerMs);
        return msToNormalTime(milliseconds);
        }
    }
}
