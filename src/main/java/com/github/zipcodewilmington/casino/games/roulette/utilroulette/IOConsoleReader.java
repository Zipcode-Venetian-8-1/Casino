package com.github.zipcodewilmington.casino.games.roulette.utilroulette;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class IOConsoleReader {

    //this will read input from the user
    private static Scanner in = new Scanner(new InputStreamReader(System.in));

    //tells user to input an integer value
    public static String promptString (String prompt) {
        System.out.print(prompt);
        return in.next();
    }

    public static int promptInt (String prompt) {
        int result = Integer.MIN_VALUE;
        do {
            try {
                result = Integer.parseInt(promptString(prompt));
            }
            catch (NumberFormatException e) {
            }
        }
        while (result == Integer.MIN_VALUE);
        return result;
    }

    public static double promptDouble (String prompt) {
        double result = Double.MIN_VALUE;
        do {
            try {
                result = Double.parseDouble(promptString(prompt));
            } catch (NumberFormatException e) {
            }
        }
        while (result == Double.MIN_VALUE);
        return result;
    }

    public static int promptRange (String prompt, int low, int hi) {
        final String FULL_PROMPT = String.format("%s between %d and %d? ", prompt, low, hi);
        int result;
        do {
            result = promptInt(FULL_PROMPT);
        }
        while (low > result || result > hi);
        return result;
    }

    public static String promptOneOf (String prompt, String ...choices) {
        final String FULL_PROMPT = String.format("%s one of %s? ", prompt, Arrays.toString(choices));
        Set<String> set = new TreeSet<String>(Arrays.asList(choices));
        String result;

        do {
            result = promptString(FULL_PROMPT);
        }
        while (!set.contains(result));
        return result;
    }
}
