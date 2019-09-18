package cn.ffcs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ye.yongqin
 * @date: Created in 2019/4/30
 * 时间工具类
 */
public class TimeUtil {

    /**
     * 取当前时间并转换为日期格式
     *
     * @return
     */
    public static String convertCurrentTimeToDateType() {
        Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(date);
    }

    /**
     * 将指定时间转换为日期格式
     *
     * @param date
     * @return
     */
    public static String convertParamTimeToDateType(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(date);
    }

    /**
     * 将指定日期格式转换为时间
     *
     * @param date
     * @return
     */
    public static Date convertParamDateTypeToTime(String date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取日期格式的时分秒
     *
     * @param source
     * @return
     */
    public static String convertParamDateTypeToDateOnlyDay(String source) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        SimpleDateFormat fmt2 = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = fmt.parse(source);
            return fmt2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
