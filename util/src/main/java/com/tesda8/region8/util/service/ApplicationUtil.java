package com.tesda8.region8.util.service;

import com.tesda8.region8.util.enums.Month;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ApplicationUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("MMM. dd, yyyy");

    public static Month getCurrentMonth() {
        return Month.valueOf(LocalDateTime.now().getMonth().name());
    }

    public static Long getCurrentYear() {
        return (long) LocalDateTime.now().getYear();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static String formatLocalDateTime(LocalDateTime dateToConvert) {
        return dateToConvert.format(formatter);
    }

    public static String formatLocalDateTimeToString(LocalDateTime dateToConvert) {
        return dateToConvert.format(stringFormatter);
    }
}
