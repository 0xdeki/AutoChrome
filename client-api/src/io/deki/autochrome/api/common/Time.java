package io.deki.autochrome.api.common;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BooleanSupplier;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class Time {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int min, int max) {
        sleep(ThreadLocalRandom.current().nextInt(min, max));
    }

    public static boolean waitFor(BooleanSupplier supplier, int timeout, int delay) {
        int i = 0;
        while (i < timeout / delay) {
            if (supplier.getAsBoolean()) {
                return true;
            }
            Time.sleep(delay);
            i++;
        }
        return false;
    }

    public static boolean waitFor(BooleanSupplier supplier, int timeout) {
        return waitFor(supplier, timeout, 20);
    }

}
