package com.onlinelearning.util;

public class DataUtil {

    public static boolean isStringNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isStringNullOrBlank(String str) {
        return str == null || str.isBlank();
    }

    public static String trimToNull(String str) {
        return isStringNullOrBlank(str)? null : str.trim();
    }

    public static Long parseToLong(Object obj, Long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }

        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
