package de.silentesc.simpletpa.utils;

public class JavaUtils {
    /*
     * A class for normal Java Utils
     * This could be random numbers, string checks etc.
     */

    // Returns true if string is digit-only
    public boolean stringIsDigit(String str) {
        boolean isDigit = true;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                isDigit = false;
                break;
            }
        }
        return isDigit;
    }

    // Checks if the maxValue etc. is correct and corrects it if not
    public int getConfigIntFromStr(final String str, final int minValue, final int maxValue, final int defaultValue) {
        int value;

        if (String.valueOf(maxValue).length() < str.length()) return defaultValue;
        if (!this.stringIsDigit(str)) return defaultValue;

        value = Integer.parseInt(str);

        if (value > maxValue) return defaultValue;
        if (value < minValue) return defaultValue;

        return value;
    }
}
