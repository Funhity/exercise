
package com.huaxia.sso.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtility {
    public static final SimpleDateFormat FORMAT_SIMPLE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat FORMAT_SIMPLE_CN = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat FORMAT_FULL = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static final SimpleDateFormat FORMAT_FULL_CN = new SimpleDateFormat("yyyy年MM月dd hh小时mm分钟ss秒");
    public static final SimpleDateFormat FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat FORMAT_HH_MM_SS = new SimpleDateFormat("hh:mm:ss");

    public static String getGMTString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        dateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return FORMAT_FULL.format(date);
    }

    /**
     * 日期到 字符转换
     *
     * @param format
     * @param date
     * @return
     */
    public static String format(SimpleDateFormat format, Date date) {
        try {
            return format.format(date);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static String getNanoTimeStamp() {
        return System.nanoTime()+"";
    }

    public static String getMillisTimeStamp() {
        return System.currentTimeMillis()+"";
    }

    public static String format(String format, Date date) {
        return format(new SimpleDateFormat(format), date);
    }

    public static Date add(Date date, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(field, value);
        return calendar.getTime();
    }

    public static Date add(int field, int value) {
        return add(null, field, value);
    }

    public static Date set(Date date, int field, int value) {

        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.set(field, value);
        return date;
    }

    public static Date set(int field, int value) {
        return add(null, field, value);
    }

    /**
     * 字符到日期转换
     *
     * @param format
     * @param date
     * @return
     */
    public static Date parse(SimpleDateFormat format, String date) {
        try {
            return format.parse(date);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static Date parse(String format, String date) {
        return parse(new SimpleDateFormat(format), date);
    }

    public static Date clearTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }


    public static Date parse(String date) {
        if (Validation.dateRegexSimple.matcher(date).find()) {
            if (date.contains("年")) {
                return parse(FORMAT_SIMPLE_CN, date);
            } else {
                return parse(FORMAT_SIMPLE, date);
            }
        } else {
            if (date.contains("年")) {
                return parse(FORMAT_FULL_CN, date);
            } else {
                return parse(FORMAT_FULL, date);
            }
        }
    }

    /**
     * 强制将String类型转换为Long类型;
     *
     * @param size
     * @return
     */
    public static Long getByteSize(String size) {
        String nuit = size.substring(size.length() - 1, size.length()).toUpperCase();
        if (nuit.equalsIgnoreCase("K")) {
            String num = size.substring(0, size.length() - 1);
            return Long.parseLong(num) * 1024;
        } else if (nuit.equalsIgnoreCase("M")) {
            String num = size.substring(0, size.length() - 1);
            return Long.parseLong(num) * 1024 * 1024;
        } else {
            return Long.parseLong(size);
        }
    }

    /**
     * 强制将String类型转换为Long类型;
     *
     * @param size
     * @return
     */
    public static String getByteCNM(Long size) {
        if (size == 0 || size < 1024) {
            return "1K";
        } else if (size / (1024) < 1024) {
            return (size / 1024) + "K";
        } else if (size / (1024 * 1024) < 1024) {
            return (size / (1024 * 1024)) + "M";
        } else {
            return (size / (1024 * 1024 * 1024)) + "G";
        }
    }


    /**
     * 在当天的日期上加或减Number天后的 的日期
     *
     * @param dayNumber
     * @return
     */
    public static Date getDay(Integer dayNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayNumber);
        calendar.set(Calendar.HOUR, 12);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    /**
     * @param date
     * @return
     */
    public static String getTimeDifference(String date) {
        try {
            return getTimeDifference(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException ex) {
            ex.printStackTrace();
            return "日期错误";
        }
    }

    public static String getTimeDifference(Date date) {
        long timer = new Date().getTime() - date.getTime();
        if ((timer / 365 / 24 / 60 / 60 / 1000) > 0) {
            return (timer / 365 / 24 / 60 / 60 / 1000) + "年";
        } else if ((timer / 30 / 24 / 60 / 60 / 1000) > 0) {
            return (timer / 30 / 24 / 60 / 60 / 1000) + "个月";
        } else if ((timer / 24 / 60 / 60 / 1000) == 1) {
            return "昨天";
        } else if ((timer / 24 / 60 / 60 / 1000) == 2) {
            return "前天";
        } else if ((timer / 24 / 60 / 60 / 1000) > 2) {
            return (timer / 24 / 60 / 60 / 1000) + "天";
        } else if ((timer / 60 / 60 / 1000) > 0) {
            return (timer / 60 / 60 / 1000) + "小时";
        } else if ((timer / 60 / 1000) > 0) {
            return (timer / 60 / 1000) + "分钟";
        } else {
            return "1分钟";
        }
    }

    /**
     * 当前时间所在的自然周
     */
    public static String[] mondayToSunday() {
        Date now = new Date();
        Date time = new Date(now.getYear(), now.getMonth(), now.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
       // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        String imptimeBegin = sdf.format(cal.getTime());
        Date mondayDate = cal.getTime();
        //System.out.println("所在周星期一的日期：" + imptimeBegin);

        cal.add(Calendar.DATE, 6);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        String imptimeEnd = sdf.format(cal.getTime());
        Date sundayDate = cal.getTime();
       // System.out.println("所在周星期日的日期：" + imptimeEnd);

        DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // System.out.println("星期一的开始："+datetimeDf.format(mondayDate));
       // System.out.println("星期天的结束："+datetimeDf.format(sundayDate));
        String[] rs = new String[] {datetimeDf.format(mondayDate), datetimeDf.format(sundayDate)};
        return rs;
    }

    /**
     * 当前月份的自然周
     * @param date
     * @throws Exception
     */
    public static void printfWeeks(String date) throws Exception {
        // String date = "2013-09";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date1 = dateFormat.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("days:" + days);
        int count = 0;
        for (int i = 1; i <= days; i++) {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = dateFormat1.parse(date + "-" + i);
            calendar.clear();
            calendar.setTime(date2);
            int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
            if (k == 1) {// 若当天是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                if (i - 6 <= 1) {
                    System.out.println("本周开始日期:" + date + "-" + 1);
                } else {
                    System.out.println("本周开始日期:" + date + "-" + (i - 6));
                }
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
            if (k != 1 && i == days) {// 若是本月最好一天，且不是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                System.out.println("本周开始日期:" + date + "-" + (i - k + 2));
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
        }
    }

    public static void main(String[] args) {
//        Date t1 = DateUtility.parse("1987-04-22");
//        Date t2 = DateUtility.parse(DateUtility.format(DateUtility.FORMAT_SIMPLE, new Date()));
//        System.out.println(t1.getTime() > t2.getTime());
//
//        System.out.println(DateUtility.clearTime(new Date()));
        try {
            mondayToSunday();
        } catch (Exception e) {

        }
    }
}
