package com.gokhanaliccii.citylist.util;

public class StringUtil {

    public static final String SPACE = " ";
    public static final String NON_SPACE = "";

    private StringUtil() {
    }

    public static String trim(String input) {
        if (isEmpty(input) || !input.contains(SPACE)) return input;

        return input.replaceAll(SPACE, NON_SPACE);
    }

    public static boolean isEmpty(String text) {
        return (text == null || text.isEmpty());
    }

    public static boolean isStartWith(String word, String prefix) {
        if (isEmpty(word) || isEmpty(prefix)) return false;

        return word.startsWith(prefix);
    }

    public static boolean isStartWithWithoutSensitivity(String word, String prefix) {
        if (isEmpty(word) || isEmpty(prefix)) return false;

        return word.toLowerCase().startsWith(prefix.toLowerCase());
    }
}
