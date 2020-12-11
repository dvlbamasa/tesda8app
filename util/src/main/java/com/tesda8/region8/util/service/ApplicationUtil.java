package com.tesda8.region8.util.service;

import com.tesda8.region8.util.enums.Month;

import java.time.LocalDateTime;

public class ApplicationUtil {

    public static Month getCurrentMonth() {
        System.out.println(LocalDateTime.now().getMonth().name());
        return Month.valueOf(LocalDateTime.now().getMonth().name());
    }

    public static Long getCurrentYear() {
        return (long) LocalDateTime.now().getYear();
    }
}
