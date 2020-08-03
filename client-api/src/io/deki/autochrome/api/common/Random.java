package io.deki.autochrome.api.common;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Deki on 03.08.2020
 * @project AutoChrome
 **/
public class Random {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String nextAlphaNumeric(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder password = new StringBuilder(length);
        java.util.Random random = new java.util.Random(System.nanoTime());

        for (int i = 0; i < length; i++) {
            int position = random.nextInt(CHARS.length());
            password.append(CHARS.charAt(position));
        }
        return new String(password);
    }

}
