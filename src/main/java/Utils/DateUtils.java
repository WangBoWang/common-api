package Utils;

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

    private final static String YEAR= "YYYY";
    private final static String MONTH= "MM";
    private final static String DAY= "DD";
    private final static String HOUR= "HH";
    private final static String MINUTE="mm";
    private final static String SECOND= "ss";
    private final static String MILLISECOND= "SSS";
    private final static String YYYY_MM= "YYYY-MM";
    private final static String YYYY_MM_DD= "YYYY-MM-dd";
    private final static String YYYY_MM_DD_24HH_mm_ss= "YYYY-MM-dd HH:mm:ss";//24小时制
    private final static String YYYY_MM_DD_12HH_mm_ss= "YYYY-MM-dd hh:mm:ss";//12小时制
    private final static String YYYY_MM_DD_HH_mm_ss_SSS= "YYYY-MM-dd HH:mm:ss:SSS";//24小时制
    private final static String YYYYMM= "YYYYMM";
    private final static String YYYYMMDD= "YYYYMMdd";
    private final static String YYYYMMDDHHmmss= "YYYYMMddHHmmss";//24小时制
    private static SimpleDateFormat sdf ;

    /**
     *获取日期所在年
     */
    private static int getYear(Date date){
        sdf=new SimpleDateFormat(DateUtils.YEAR);
        return Integer.valueOf(sdf.format(date));
    }

    /**
     *获取日期月
     */
    private static Integer getMonth(Date date){
        sdf=new SimpleDateFormat(DateUtils.MONTH);
        return Integer.valueOf(sdf.format(date));
    }
    /**
     *获取日期天
     */
    private static Integer getDay(Date date){
        sdf=new SimpleDateFormat(DateUtils.DAY);
        return Integer.valueOf(sdf.format(date));
    }
    /**
     *获取日期时
     */
    private static Integer getHour(Date date){
        sdf=new SimpleDateFormat(DateUtils.HOUR);
        return Integer.valueOf(sdf.format(date));
    }
    /**
     *获取日期分
     */
    private static Integer getMinute(Date date){
        sdf=new SimpleDateFormat(DateUtils.MINUTE);
        return Integer.valueOf(sdf.format(date));
    }

    /**
     *获取日期秒
     */
    private static Integer getSecond(Date date){
        sdf=new SimpleDateFormat(DateUtils.SECOND);
        return Integer.valueOf(sdf.format(date));
    }

    /**
     *日期运算:给指定日期增加指定年
     */
    private static Date addYear(Date date,Integer year){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR,year);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定月
     */
    private static Date addMonth(Date date,Integer month){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,month);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定天
     */
    private static Date addDay(Date date,Integer day){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR,day);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定时
     */
    private static Date addHour(Date date,Integer hour){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR,hour);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定分钟
     */
    private static Date addMinute(Date date,Integer minute){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE,minute);
        return c.getTime();
    }

    /**
     *日期运算:给指定日期增加指定秒
     */
    private static Date addSecond(Date date,Integer second){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND,second);
        return c.getTime();
    }
    /**
     *将长整型的毫秒数转化为日期
     */
    private static Date convertTimeToDate(Long time ){
        return new Date(time);
    }

    /**
     *获取日期的毫秒数
     */
    private static Long getTime(Date date){
        return date.getTime();
    }

    /**
     *获取指定日期所在月的第一天的00时00分00秒00毫秒
     */
    private static Date getFirstDayOfMonth(Date date){
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
    private static Date getLastDayOfMonth(Date date){
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
    private static Date getLastDayTimeOfMonth(Date date){
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
    private static Date convertStringtoDate(String time,String pattern){
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
    private static String convertDatetoString(Date date,String pattern){
        sdf=new SimpleDateFormat(pattern);
        String time = sdf.format(date);
        return time;
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
