package com.stefan.testeauto.util;

import java.time.Duration;

public class DateUtil {

    /**
     * @param duration format mm:ss
     */
    public static Duration getDuration(String duration) {
        var minutes = duration.substring(0, 2);
        var seconds = duration.substring(3);
        return Duration.parse(String.format("PT%sM%sS", minutes, seconds));
    }

}
