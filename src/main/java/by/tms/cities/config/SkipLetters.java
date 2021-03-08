package by.tms.cities.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkipLetters {
    public static final List<String> letter = new ArrayList<>(Arrays.asList("ь", "ъ", "ы", "й"));

    public static boolean check(String l) {
        if (letter.indexOf(l) == -1) {
            return false;
        } else {
            return true;
        }
    }
}
