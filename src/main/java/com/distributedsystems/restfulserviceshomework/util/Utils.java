package com.distributedsystems.restfulserviceshomework.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utils {

    public static boolean isTheSameDate(LocalDateTime localDateTime, LocalDate localDate) {
        return localDateTime.toLocalDate().isEqual(localDate);
    }

    public static String createInformationStringForSingleDayWeather(final LocalDate date) {
        return LocalDate.now().isBefore(date)
                ? "Weather forecast for: " + date.toString()
                : "Official weather for: " + date.toString();
    }

    public static String createInformationStringForDateRangeWeather(final LocalDate startDate, final LocalDate endDate) {
        final LocalDate today = LocalDate.now();
        if (today.isBefore(startDate))
            return "Weather forecast for date range: " + startDate.toString() + " - " + endDate.toString();
//        else if (today.isAfter(startDate)) // TODO: update
        return "";
    }
}
