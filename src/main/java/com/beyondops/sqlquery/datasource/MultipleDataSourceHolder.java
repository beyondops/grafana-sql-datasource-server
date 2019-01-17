package com.beyondops.sqlquery.datasource;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/17.
 */
public class MultipleDataSourceHolder {

    private static ThreadLocal<String> routeKey = new ThreadLocal<String>();

    public static String getRouteKey() {
        String key = routeKey.get();
        return key;
    }

    public static void setRouteKey(String key) {
        routeKey.set(key);
    }

    public static void removeRouteKey() {
        routeKey.remove();
    }
}
