package com.tesda8.region8.util.service;

public class StringUtils{

    public static String formatSearchString(String input) {
        input.replace(",", "");
        return input;
    }
}
