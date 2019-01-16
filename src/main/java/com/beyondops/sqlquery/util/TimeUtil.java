package com.beyondops.sqlquery.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */
public class TimeUtil {

    public static long getUnixTimestamp(LocalDateTime localDateTime) {
        long currentUnixTimestamp = localDateTime.toEpochSecond(ZoneOffset.UTC);
        return currentUnixTimestamp;
    }

    public static long getUnixTimestampMs(LocalDateTime localDateTime) {
        return getUnixTimestamp(localDateTime) * 1000;
    }

    public static void main(String[] args) {
        System.out.println(TimeUtil.getUnixTimestamp(LocalDateTime.now()));
        System.out.println(TimeUtil.getUnixTimestampMs(LocalDateTime.now()));
    }
}
