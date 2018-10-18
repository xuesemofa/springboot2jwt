package com.hengbang.hbcrm.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dates2 {

    /**
     * 获得该月第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth2(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获得该月最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth2(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获得该月最后N天
     *
     * @param year
     * @param month
     * @param i     最后几天
     * @return
     */
    public static String getLastDayOfMonth3(int year, int month, int i) {
        try {
            String lastDayOfMonth2 = getLastDayOfMonth2(year, month);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String t = lastDayOfMonth2 + ",";
            Date date = null;
            for (int j = 1; j < i; j++) {
                lastDayOfMonth2 = sdf.format(getPreviousDate(sdf.parse(lastDayOfMonth2)));
                t += lastDayOfMonth2 + ",";
            }
            return t;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回当前时间字符串
     *
     * @return String
     */
    public static String getDateTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        return sdf.format(new Date());

    }

    public static String getDateTimeString2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 采用默认的格式
        return sdf.format(new Date());

    }

    /**
     * 将字符串转成时间
     *
     * @param DateString
     * @return
     */
    public static Date getDate(String DateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        try {
            return sdf.parse(DateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回日期转换成字符串yyyyMMdd
     *
     * @param date --日期
     * @return String
     */
    public static String getDateString1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 采用年月日
        return sdf.format(date);
    }

    /**
     * 精确到微秒
     *
     * @param DateString
     * @return
     */
    public static Date getDate1(String DateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // 采用默认的格式
        try {
            return sdf.parse(DateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回系统日期时间字符串
     *
     * @param datetimeType --时间格式(如yyyyMMddHHmmss)
     * @return String
     */
    public static String getDateTimeString(String datetimeType) {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeType);
        return sdf.format(new Date());
    }

    /**
     * 返回系统时间字符串
     *
     * @param dateTime --日期时间
     * @return String
     */
    public static String getDateTimeString(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        return sdf.format(dateTime);
    }

    /**
     * 返回系统时间字符串精确到微秒
     *
     * @param dateTime --日期时间
     * @return String
     */
    public static String getDateTimeString1(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // 采用默认的格式
        return sdf.format(dateTime);
    }

    /**
     * 返回系统日期字符串
     *
     * @param date --日期
     * @return String
     */
    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 采用默认的格式
        return sdf.format(date);
    }

    /**
     * 返回系统时间字符串
     *
     * @param date         --时间
     * @param datetimeType --时间格式(如yyyyMMddHHmmss)
     * @return String
     */
    public static String getDateTimeString(Date date, String datetimeType) {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeType);
        return sdf.format(date);
    }

    /**
     * 一个日期加上小时后变成新的时间
     *
     * @param date   日期
     * @param minute 小时
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, long minute) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + minute * 60 * 1000);
        return c.getTime();
    }

    /**
     * 一个日期减去分钟后变成新的时间
     *
     * @param date 日期
     *             分种
     * @return 返回相加后的日期
     */
    public static Date subDate(Date date, long minute) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - minute * 60 * 1000);
        return c.getTime();
    }

    /**
     * 一个日期减去分钟后变成新的时间
     *
     * @param date 日期
     *             分种
     * @return 返回相加后的日期
     */
    public static Date subDate1(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - second * 1000);
        return c.getTime();
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 两个日期之间相差几秒
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int subDate(String beginTime, String endTime) {
        Date begin = getDate(beginTime);
        Date end = getDate(endTime);
        return (int) (getMillis(end) - getMillis(begin) / 1000);
    }

    /**
     * 将时间格式的字符串转换成时间类型
     *
     * @param datetimeString --时间格式的字符串
     * @return
     */
    public static Date parseStringToDate(String datetimeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        Date dt = null;
        try {
            dt = sdf.parse(datetimeString);
        } catch (Exception e) {
        }
        return dt;
    }

    /**
     * 将时间格式的字符串转换成时间类型
     *
     * @param datetimeString --时间格式的字符串
     * @param datetimeType   --时间格式
     * @return
     */
    public static Date parseStringToDate(String datetimeString,
                                         String datetimeType) {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeType);
        Date dt = null;
        try {
            dt = sdf.parse(datetimeString);
        } catch (Exception e) {
        }
        return dt;
    }

    /**
     * 获得某一日期的前一天
     *
     * @param date
     * @return Date
     */
    public static Date getPreviousDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static int dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return w;
    }

    public static String dateToWeek2(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取某段时这里写代码片间内的所有日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    public static List<String> findDates2(String dBegin, String dEnd) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        List<String> lDate = new ArrayList<>();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(format.parse(dBegin));
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(format.parse(dEnd));
        // 测试此日期是否在指定日期之后
        while (format.parse(dEnd).after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(format.format(calBegin.getTime()));
        }
        return lDate;
    }
/***********************周操作****************************************************************************/
    /**
     * 根据指定日期获取当前所在当年的第几周
     *
     * @param today
     * @return
     */
    public static int getWeek(String today) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getWeek2(String today) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

//    public static void main(String[] args) {

//        WeekUtils cd = new WeekUtils();
//        System.out.println("开始时间: " + cd.getStartDayOfWeekNo(2015,47) );
//        System.out.println("结束时间:" + cd.getEndDayOfWeekNo(2015,47) );

//    }

    /**
     * get first date of given month and year
     *
     * @param year
     * @param month
     * @return
     */
    public String getFirstDayOfMonth(int year, int month) {
        String monthStr = month < 10 ? "0" + month : String.valueOf(month);
        return year + "-" + monthStr + "-" + "01";
    }

    /**
     * get the last date of given month and year
     *
     * @param year
     * @param month
     * @return
     */
    public String getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * get Calendar of given year
     *
     * @param year
     * @return
     */
    private Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    /**
     * get start date of given week no of a year
     *
     * @param year
     * @param weekNo
     * @return
     */
    public String getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * get the end day of given week no of a year.
     *
     * @param year
     * @param weekNo
     * @return
     */
    public String getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }

    /**********************************月操作*************************************************************************/
    /**
     * 获取当前年月
     *
     * @return
     */
    public String getYM() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M");
        Calendar c = Calendar.getInstance();
        return format.format(c.getTime());
    }

    /**
     * 获取任意时间的下一个月
     * 描述:<描述函数实现的功能>.
     *
     * @param repeatDate
     * @return
     */
    public static String getPreMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("YYYY-MM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(5, 7);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year, month, Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取任意时间的上一个月
     * 描述:<描述函数实现的功能>.
     *
     * @param repeatDate
     * @return
     */
    public static String getLastMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year, month - 2, Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }
}
