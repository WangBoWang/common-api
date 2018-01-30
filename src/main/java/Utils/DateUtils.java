package Utils;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.regexp.internal.RE;

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
     *获取指定日期年份
     */
    private static int getYear(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.YEAR);
    }
    /**
     *获取指定日期月
     * 月份从0开始的
     */
    private static Integer getMonthOfYear(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.MONTH);
    }
    /**
     *获取日期天(月天)
     */
    private static Integer getDayOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.DAY_OF_MONTH);
    }
    /**
     *获取日期时(天时)
     */
    private static Integer getHourOfDay(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.HOUR_OF_DAY);
    }
    /**
     *获取日期分
     */
    private static Integer getMinuteOfHour(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.MINUTE);
    }

    /**
     *获取日期秒
     */
    private static Integer getSecondOfMinute(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.SECOND);
    }

    /**
     *获取日期年周
     */
    private static Integer getWeekOfYear(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     *获取日期月周
     */
    private static Integer getWeekOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     *获取日期星期几
     * 周末为1
     */
    private static Integer getDayOfWeek(Date date){
        Calendar c = Calendar.getInstance();
        if(null!=date){
            c.setTime(date);
        }
        return c.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获取当前日期年、月、日、时、分、秒、年周、月周、星期几
     */
    private static int getYear(){ return getYear(null);}
    private static Integer getMonthOfYear(){return getMonthOfYear(null);}
    private static Integer getDayOfMonth(){return getDayOfMonth(null);}
    private static int getHourOfDay(){ return getHourOfDay(null);}
    private static Integer getMinuteOfHour(){return getMinuteOfHour(null);}
    private static Integer getSecondOfMinute(){return getSecondOfMinute(null);}
    private static Integer getWeekOfYear(){return getWeekOfYear(null);}
    private static Integer getWeekOfMonth(){return getWeekOfMonth(null);}
    private static Integer getDayOfWeek(){return getDayOfWeek(null);}


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
     *获取指定日期所在年的第一天的00时00分00秒00毫秒
     */
    private static Date getFirstDayOfYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR,c.getActualMinimum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMinimum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     *获取指定日期所在年最后一天的00时00分00秒00毫秒
     */
    private static Date getLastDayOfYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR,c.getActualMaximum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMinimum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     *获取指定日期所在年最后一天的23时59分59秒999毫秒
     */
    private static Date getLastDayTimeOfYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR,c.getActualMaximum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMaximum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMaximum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMaximum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMaximum(Calendar.MILLISECOND));
        return  c.getTime();
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
     *获取指定日期所在月最后一天的00时00分00秒00毫秒
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
     *获取指定日期所在月最后一天的23时59分59秒999毫秒
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
     *获取指定日期所在周的第一天的00时00分00秒00毫秒
     */
    private static Date getFirstDayOfWeek(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK,c.getActualMinimum(Calendar.DAY_OF_WEEK));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMinimum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     *获取指定日期所在周的最后一天的00时00分00秒00毫秒
     */
    private static Date getLastDayOfWeek(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK,c.getActualMaximum(Calendar.DAY_OF_WEEK));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMinimum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     *获取指定日期所在周的最后一天的23时59分59秒999毫秒
     */
    private static Date getLastDayTimeOfWeek(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK,c.getActualMaximum(Calendar.DAY_OF_WEEK));
        c.set(Calendar.HOUR_OF_DAY,c.getActualMaximum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,c.getActualMaximum(Calendar.MINUTE));
        c.set(Calendar.SECOND,c.getActualMaximum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND,c.getActualMaximum(Calendar.MILLISECOND));
        return  c.getTime();
    }

    /**
     * 获取当前日期年、月、周第一和最后一天。
     * 月份从0开始。周从周末(1)开始
     * 时分秒毫秒
     * 00时00分00秒00毫秒
     * 23时59分59秒999毫秒
     */
    private static Date getFirstDayOfYear(){return getFirstDayOfYear(null);}
    private static Date getLastDayOfYear(){return getLastDayOfYear(null);}
    private static Date getLastDayTimeOfYear(){return getLastDayTimeOfYear(null);}
    private static Date getFirstDayOfMonth(){return getFirstDayOfMonth(null);}
    private static Date getLastDayOfMonth(){return getLastDayOfMonth(null);}
    private static Date getLastDayTimeOfMonth(){return getLastDayTimeOfMonth(null);}
    private static Date getFirstDayOfWeek(){return getFirstDayOfWeek(null);}
    private static Date getLastDayOfWeek(){return getLastDayOfWeek(null);}
    private static Date getLastDayTimeOfWeek(){return getLastDayTimeOfWeek(null);}

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
        System.out.println(getMonthOfYear(new Date()));
        System.out.println(getDayOfMonth(date));
        System.out.println(getHourOfDay(date));
        System.out.println(getMinuteOfHour(date));
        System.out.println(getSecondOfMinute(date));
        System.out.println(getWeekOfYear());
        System.out.println(getWeekOfMonth());
        System.out.println(getDayOfWeek());
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

        System.out.println(getYear(null));
    }
}
