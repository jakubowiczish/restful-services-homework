package com.distributedsystems.restfulserviceshomework.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utils {

    public static boolean isTheSameDate(LocalDateTime localDateTime, LocalDate localDate) {
        return localDateTime.toLocalDate().isEqual(localDate);
    }

    public static String createInformationStringForSingleDayWeather(final LocalDate date) {
        return "Weather for: " + date.toString();
    }

    public static String createInformationStringForDateRangeWeather(final LocalDate startDate, final LocalDate endDate) {
        return "Weather for: " + startDate.toString() + " - " + endDate.toString();
    }
}
