package com.chow.config;

public class CommonFallback {

    public static String fallback(String name, Throwable e) {
        return "Throwable";
    }
}
