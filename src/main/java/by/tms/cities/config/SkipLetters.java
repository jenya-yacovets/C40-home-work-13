package by.tms.cities.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkipLetters {
    public static final List<String> letter = new ArrayList<>(Arrays.asList("ь", "ъ", "ы", "й"));

    public static boolean check(String l) {
        return letter.contains(l);
    }
}
