package by.tms.cities.util;

import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        if (scanner.hasNextInt()) {
            int result = scanner.nextInt();
            scanner.nextLine();
            return result;
        }
        scanner.nextLine();
        System.out.println("Вы ввели не целое число, повторите ввод:");
        return getInt();
    }

    public static int getInt(String message) {
        System.out.println(message);
        return getInt();
    }

    public static String getString() {
        return scanner.nextLine();
    }

    public static String getString(String message) {
        System.out.println(message);
        return getString();
    }
}
