package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *     日期时间工具类
 * </p>
 *
 * @author wangbowang
 * @since 2018/01/30
 */
public class DateUtils {

    public final static String YEAR= "YYYY";
    public final static String MONTH= "MM";
    public final static String DAY= "DD";
    public final static String HOUR= "HH";
    public final static String MINUTE="mm";
    public final static String SECOND= "ss";
    public final static String MILLISECOND= "SSS";
    public final static String YYYY_MM= "YYYY-MM";
    public final static String YYYY_MM_DD= "YYYY-MM-dd";
    /**24小时制*/
    public final static String YYYY_MM_DD_24HH_mm_ss= "YYYY-MM-dd HH:mm:ss";
    /**12小时制*/
    public final static String YYYY_MM_DD_12HH_mm_ss= "YYYY-MM-dd hh:mm:ss";
    /**24小时制*/
    public final static String YYYY_MM_DD_HH_mm_ss_SSS= "YYYY-MM-dd HH:mm:ss:SSS";
    public final static String YYYYMM= "YYYYMM";
    public final static String YYYYMMDD= "YYYYMMdd";
    /**24小时制*/
    public final static String YYYYMMDDHHmmss= "YYYYMMddHHmmss";
    public static SimpleDateFormat sdf ;

    /**
     *获取日期所在年
     */
    public static int getYear(Date date){
        sdf=new SimpleDateFormat(DateUtils.YEAR);
        return Integer.valueOf(sdf.format(date));
    }

    /**
     *获取日期月
     */
    public static Integer getMonth(Date date){
        sdf=new SimpleDateFormat(DateUtils.MONTH);
        return Integer.valueOf(sdf.format(date));
    }
    /**
     *获取日期天
     */
    public static Integer getDay(Date date){
        sdf=new SimpleDateFormat(DateUtils.DAY);
        return Integer.valueOf(sdf.format(date));
    }
    /**
     *获取日期时
     */
    public static Integer getHour(Date date){
        sdf=new SimpleDateFormat(DateUtils.HOUR);
        return Integer.valueOf(sdf.format(date));
    }
    /**
     *获取日期分
     */
    public static Integer getMinute(Date date){
        sdf=new SimpleDateFormat(DateUtils.MINUTE);
        return Integer.valueOf(sdf.format(date));
    }

    /**
     *获取日期秒
     */
    public static Integer getSecond(Date date){
        sdf=new SimpleDateFormat(DateUtils.SECOND);
        return Integer.valueOf(sdf.format(date));
    }

    /**
     *日期运算:给指定日期增加指定年
     */
    public static Date addYear(Date date,Integer year){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR,year);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定月
     */
    public static Date addMonth(Date date,Integer month){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,month);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定天
     */
    public static Date addDay(Date date,Integer day){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR,day);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定时
     */
    public static Date addHour(Date date,Integer hour){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR,hour);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定分钟
     */
    public static Date addMinute(Date date,Integer minute){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE,minute);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定秒
     */
    public static Date addSecond(Date date,Integer second){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND,second);
        return c.getTime();
    }
    /**
     *将长整型的毫秒数转化为日期
     */
    public static Date convertTimeToDate(Long time ){
        return new Date(time);
    }

    /**
     *获取日期的毫秒数
     */
    public static Long getTime(Date date){
        return date.getTime();
    }

    /**
     *获取指定日期所在月的第一天的00时00分00秒00毫秒
     */
    public static Date getFirstDayOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMinimum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     *返回当月最后一天的00时00分00秒00毫秒
     */
    public static Date getLastDayOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMinimum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     *返回当月最后一天的23时59分59秒999毫秒
     */
    public static Date getLastDayTimeOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.HOUR_OF_DAY,c.getActualMaximum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMaximum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMaximum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMaximum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     *将日期字符串按指定模式转化为日期
     */
    public static Date convertStringtoDate(String time,String pattern){
        sdf=new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *将日期按指定模式转化为字符串
     */
    public static String convertDatetoString(Date date,String pattern){
        sdf=new SimpleDateFormat(pattern);
        String time = sdf.format(date);
        return time;
    }

    /**
     * 获取指定日期是当前周周几
     * @param date 指定日期
     * @return 周几（时间是从周日开始计算的，周日-周六 对应返回 1-7）
     */
    public static int getDayOfWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定日期是当前月第几天
     * @param date 指定日期
     * @return 当前月第几天
     */
    public static int getDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取指定日期是当前月第几周
     * @param date 指定日期
     * @return 当前月第几周
     */
    public static int getWeekOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }




    public static void main(String[] args){
        Date date = new Date();
        //获取日期字段测试
        System.out.println(getYear(new Date()));
        System.out.println(getMonth(new Date()));
        System.out.println(getDay(date));
        System.out.println(getHour(date));
        System.out.println(getMinute(date));
        System.out.println(getSecond(date));
        //日期字符串转化测试
        System.out.println(convertDatetoString(getFirstDayOfMonth(new Date()),DateUtils.YYYY_MM_DD_24HH_mm_ss));
        System.out.println(convertDatetoString(getLastDayOfMonth(new Date()),DateUtils.YYYY_MM_DD_24HH_mm_ss));
        System.out.println(convertDatetoString(getLastDayTimeOfMonth(new Date()),DateUtils.YYYY_MM_DD_HH_mm_ss_SSS));
        //日期运算测试
        System.out.println(convertDatetoString(addYear(date,1),DateUtils.YYYY_MM_DD_24HH_mm_ss));
        System.out.println(convertDatetoString(addMonth(date,13),DateUtils.YYYY_MM_DD_24HH_mm_ss));
        System.out.println(convertDatetoString(addDay(date,367),DateUtils.YYYY_MM_DD_24HH_mm_ss));
        System.out.println(convertDatetoString(addHour(date,25),DateUtils.YYYY_MM_DD_24HH_mm_ss));
        System.out.println(convertDatetoString(addMinute(date,61),DateUtils.YYYY_MM_DD_24HH_mm_ss));
        System.out.println(convertDatetoString(addSecond(date,61),DateUtils.YYYY_MM_DD_24HH_mm_ss));

        Date futureDate = addDay(date,367);
        Long time = futureDate.getTime()-date.getTime();
        Long day = time/(1000*3600*24);
        System.out.println(day);
    }
}
