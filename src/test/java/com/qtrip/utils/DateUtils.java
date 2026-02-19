package com.qtrip.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Date utilities for test data generation and validation.
 *
 * @author Natarajan M
 */
public final class DateUtils {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String US_FORMAT = "MM/dd/yyyy";
    public static final String UK_FORMAT = "dd/MM/yyyy";

    private DateUtils() {}

    // ==================== CURRENT DATE/TIME ====================

    /**
     * Get current date in default format.
     */
    public static String getCurrentDate() {
        return getCurrentDate(DEFAULT_FORMAT);
    }

    /**
     * Get current date in specified format.
     */
    public static String getCurrentDate(String format) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Get current datetime.
     */
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    /**
     * Get current timestamp for unique IDs.
     */
    public static String getTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    // ==================== FUTURE DATES ====================

    /**
     * Get date X days from today.
     */
    public static String getFutureDate(int daysFromToday) {
        return getFutureDate(daysFromToday, DEFAULT_FORMAT);
    }

    /**
     * Get date X days from today in specified format.
     */
    public static String getFutureDate(int daysFromToday, String format) {
        return LocalDate.now().plusDays(daysFromToday)
            .format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Get date X weeks from today.
     */
    public static String getFutureDateByWeeks(int weeksFromToday) {
        return LocalDate.now().plusWeeks(weeksFromToday)
            .format(DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
    }

    /**
     * Get date X months from today.
     */
    public static String getFutureDateByMonths(int monthsFromToday) {
        return LocalDate.now().plusMonths(monthsFromToday)
            .format(DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
    }

    // ==================== PAST DATES ====================

    /**
     * Get date X days ago.
     */
    public static String getPastDate(int daysAgo) {
        return getPastDate(daysAgo, DEFAULT_FORMAT);
    }

    /**
     * Get date X days ago in specified format.
     */
    public static String getPastDate(int daysAgo, String format) {
        return LocalDate.now().minusDays(daysAgo)
            .format(DateTimeFormatter.ofPattern(format));
    }

    // ==================== DATE CALCULATIONS ====================

    /**
     * Calculate days between two dates.
     */
    public static long daysBetween(String startDate, String endDate) {
        return daysBetween(startDate, endDate, DEFAULT_FORMAT);
    }

    /**
     * Calculate days between two dates with format.
     */
    public static long daysBetween(String startDate, String endDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Add days to a date.
     */
    public static String addDays(String date, int days) {
        return addDays(date, days, DEFAULT_FORMAT);
    }

    /**
     * Add days to a date with format.
     */
    public static String addDays(String date, int days, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.plusDays(days).format(formatter);
    }

    // ==================== DATE VALIDATION ====================

    /**
     * Check if date is valid.
     */
    public static boolean isValidDate(String date) {
        return isValidDate(date, DEFAULT_FORMAT);
    }

    /**
     * Check if date is valid with format.
     */
    public static boolean isValidDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Check if date is in the future.
     */
    public static boolean isFutureDate(String date) {
        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
        return inputDate.isAfter(LocalDate.now());
    }

    /**
     * Check if date is in the past.
     */
    public static boolean isPastDate(String date) {
        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
        return inputDate.isBefore(LocalDate.now());
    }

    // ==================== FORMAT CONVERSION ====================

    /**
     * Convert date from one format to another.
     */
    public static String convertFormat(String date, String fromFormat, String toFormat) {
        DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern(fromFormat);
        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern(toFormat);
        LocalDate localDate = LocalDate.parse(date, fromFormatter);
        return localDate.format(toFormatter);
    }

    /**
     * Get day of week for a date.
     */
    public static String getDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
        return localDate.getDayOfWeek().toString();
    }

    /**
     * Get first day of current month.
     */
    public static String getFirstDayOfMonth() {
        return LocalDate.now().withDayOfMonth(1).format(DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
    }

    /**
     * Get last day of current month.
     */
    public static String getLastDayOfMonth() {
        LocalDate now = LocalDate.now();
        return now.withDayOfMonth(now.lengthOfMonth())
            .format(DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
    }
}

