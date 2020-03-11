package com.distributedsystems.restfulserviceshomework.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utils {

    public static boolean isTheSameDate(LocalDateTime localDateTime, LocalDate localDate) {
        return localDateTime.toLocalDate().isEqual(localDate);
    }
}
