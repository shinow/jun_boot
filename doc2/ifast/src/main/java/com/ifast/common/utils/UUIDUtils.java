package com.ifast.common.utils;

import java.util.UUID;

/**
 * <pre>
 *
 * </pre>
 * <small> 2019-05-09 16:40 | Aron</small>
 */
public abstract class UUIDUtils {

    public static String get(boolean isPureStr) {
        String result = UUID.randomUUID().toString();
        if (isPureStr) {
            result = result.replaceAll("-", "");
        }
        return result;
    }

    public static String get() {
        return get(true);
    }

}
