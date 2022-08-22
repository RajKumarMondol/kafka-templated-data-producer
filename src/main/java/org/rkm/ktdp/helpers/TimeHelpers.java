package org.rkm.ktdp.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeHelpers {
    public static int getTimeInSeconds(LocalTime time) {
        return (time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond());
    }

    public static long getTimeInSeconds(LocalDateTime dateTime) {
        return (dateTime.getYear() - 2020) * 31536000
                + dateTime.getDayOfYear() * 86400
                + dateTime.getHour() * 3600
                + dateTime.getMinute() * 60
                + dateTime.getSecond();
    }

    public static int getTimeInDays(LocalDate date) {
        return (date.getYear() - 2020) * 365 + date.getDayOfYear();
    }
}
