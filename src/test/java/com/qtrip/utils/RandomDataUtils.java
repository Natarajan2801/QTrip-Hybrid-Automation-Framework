package com.qtrip.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Utility class for generating random test data.
 * Useful for creating unique test data to avoid conflicts.
 *
 * @author Natarajan M
 */
public final class RandomDataUtils {

    private static final Random random = new Random();
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHA_NUMERIC = "abcdefghijklmnopqrstuvwxyz0123456789";

    private RandomDataUtils() {}

    /**
     * Generate random email address.
     */
    public static String getRandomEmail() {
        return "test" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
    }

    /**
     * Generate random email with custom domain.
     */
    public static String getRandomEmail(String domain) {
        return "test" + UUID.randomUUID().toString().substring(0, 8) + "@" + domain;
    }

    /**
     * Generate random string of specified length.
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHA.charAt(random.nextInt(ALPHA.length())));
        }
        return sb.toString();
    }

    /**
     * Generate random alphanumeric string.
     */
    public static String getRandomAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHA_NUMERIC.charAt(random.nextInt(ALPHA_NUMERIC.length())));
        }
        return sb.toString();
    }

    /**
     * Generate random number within range.
     */
    public static int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generate random phone number.
     */
    public static String getRandomPhoneNumber() {
        return "9" + String.format("%09d", random.nextInt(1000000000));
    }

    /**
     * Generate random name (First Name).
     */
    public static String getRandomFirstName() {
        String[] names = {"John", "Jane", "Mike", "Sarah", "David", "Emma", "Chris", "Anna", "Tom", "Lisa"};
        return names[random.nextInt(names.length)] + getRandomString(3);
    }

    /**
     * Generate random name (Last Name).
     */
    public static String getRandomLastName() {
        String[] names = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Davis", "Miller", "Wilson"};
        return names[random.nextInt(names.length)];
    }

    /**
     * Generate unique ID based on timestamp.
     */
    public static String getUniqueId() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + getRandomAlphaNumeric(4);
    }

    /**
     * Generate future date (days from today).
     */
    public static String getFutureDate(int daysFromToday, String format) {
        Date date = new Date(System.currentTimeMillis() + (long) daysFromToday * 24 * 60 * 60 * 1000);
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Generate random password meeting common requirements.
     */
    public static String getRandomPassword() {
        return "Test@" + getRandomAlphaNumeric(6) + "!";
    }
}

