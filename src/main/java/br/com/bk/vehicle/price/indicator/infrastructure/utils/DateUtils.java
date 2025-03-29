package br.com.bk.vehicle.price.indicator.infrastructure.utils;

public class DateUtils {

    private DateUtils() {
    }

    private static final String DATE_PATTERN = "^(0[1-9]|1[0-2])\\/\\d{4}$";

    public static boolean validateDate(String date) {
        return date.matches(DATE_PATTERN);
    }

    public static boolean isValidDate(String date) {
        return date != null && date.matches(DATE_PATTERN);
    }
}
