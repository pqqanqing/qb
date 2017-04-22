package com.wjs.qb.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    private final static SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取本周一的日期，以"yyyy-MM-dd"格式返回
     * @return
     */
    public static String getMonday() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        calendar.add(Calendar.DATE, -dayOfWeek + 1);
        return YYYY_MM_DD.format(calendar.getTime());
    }
}
