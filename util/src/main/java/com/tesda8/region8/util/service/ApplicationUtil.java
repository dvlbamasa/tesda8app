package com.tesda8.region8.util.service;

import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitPOType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ApplicationUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("MMM. dd, yyyy");

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    public static int getDefaultPageNumber() {
        return DEFAULT_PAGE_NUMBER;
    }

    public static int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    public static Month getCurrentMonth() {
        return Month.valueOf(LocalDateTime.now().getMonth().name());
    }

    public static Long getCurrentYear() {
        return (long) LocalDateTime.now().getYear();
    }

    public static LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now().plusHours(8);
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

    public static String trimRoleName(String role) {
        return role.substring(6, role.length()-1);
    }

    public static String getSuccessIndicatorType(String roleName) {
        if (roleName.equals("PLANNING") || roleName.equals("ADMIN")) {
            return "PO";
        }
        OperatingUnitPOType operatingUnitPOType = OperatingUnitPOType.valueOf(roleName);
        return operatingUnitPOType.successIndicatorType;
    }

}
