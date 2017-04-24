package cn.qtech.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author wangruyu
 * @since 2017/4/23-18:57
 */
public class DateUtil {
    public static Date getCommonDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
