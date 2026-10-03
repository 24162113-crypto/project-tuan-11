package vn.hcmute.util;

public class StringUtil_24162113 {
    private StringUtil_24162113() {
    }

    public static String clean(String value) {
        return value == null ? "" : value.trim();
    }

    public static int toInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(clean(value));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long toLong(String value, long defaultValue) {
        try {
            return Long.parseLong(clean(value));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
