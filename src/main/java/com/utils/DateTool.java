
package com.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateTool {

    public static String standardTime(Date date) {
        if (date==null) return "";
        Date d=new Date(date.getTime());
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sf.format(d);
        return format;
    }
    public static long diffDays(String inTime, String outTime) throws Exception {
        if (inTime == null || outTime == null) {
            throw new Exception("时间参数不能为null");
        }
        String regex="([/\\.])";
        inTime = inTime.replaceAll(regex, "-");
        outTime = outTime.replaceAll(regex, "-");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String regexDate="\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
        if (!inTime.matches(regexDate) || !outTime.matches(regexDate)) {
            throw new Exception("时间参数错误");
        }
        Date inDate = sdf.parse(inTime);
        Date outDate = sdf.parse(outTime);

        Calendar in = Calendar.getInstance();
        Calendar out = Calendar.getInstance();
        in.setTime(inDate);
        out.setTime(outDate);

        int days = 0;
        if (in.get(Calendar.HOUR_OF_DAY) <= 6) {
            days++;
        }
        if (out.get(Calendar.HOUR_OF_DAY)>=14){
            days++;
        }
        if(in.get(Calendar.DAY_OF_MONTH)==out.get(Calendar.DAY_OF_MONTH)){
            if (in.get(Calendar.HOUR_OF_DAY)>6&&in.get(Calendar.HOUR_OF_DAY)<14)
                days++;
        }
        days += js(inDate, outDate);
        // 入住时间与退房时间 2018-01-12 12:30:30
//        String[] ruTimeArr = inTime.split("\\ ")[0].split("\\-");// {"2018","01","12"}
//        String[] TuiTimeArr = outTime.split("\\ ")[0].split("\\-");
//        LocalDate of = LocalDate.of(Integer.parseInt(ruTimeArr[0]), Integer.parseInt(ruTimeArr[1]),
//                Integer.parseInt(ruTimeArr[2]));
//        LocalDate end = LocalDate.of(Integer.parseInt(TuiTimeArr[0]), Integer.parseInt(TuiTimeArr[1]),
//                Integer.parseInt(TuiTimeArr[2]));
//        long days = end.toEpochDay() - of.toEpochDay();
//        // 2、计算退房时间戳是否小于12:00:00的时间戳
//        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
//        long time1 = sdf2.parse(outTime.split("\\ ")[1]).getTime();
//        long time2 = sdf2.parse("12:00:00").getTime();
//        long timeCha = time1 - time2;
//        if (timeCha > 0) {
//            days++;
//        }
        return days;
    }

    public static int js(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        //long类型的日期也支持
//    cal1.setTimeInMillis(long);
//    cal2.setTimeInMillis(long);
        cal1.setTime(d1);
        cal2.setTime(d2);

        //获取日期在一年(月、星期)中的第多少天
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);//第335天
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);//第365天

        //获取当前日期所在的年份
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        //如果两个日期的是在同一年，则只需要计算两个日期在一年的天数差；
        //不在同一年，还要加上相差年数对应的天数，闰年有366天

        if (year1 != year2) //不同年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else //不是闰年
                {
                    timeDistance += 365;
                }
            }
            System.out.println(timeDistance + (day2 - day1));
            return timeDistance + (day2 - day1);
        } else //同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static void main(String[] args) {
//        Double dd=123.123;
//        Float ff=(Float) dd;
//        java.util.Date utildate=new java.util.Date();
//        java.sql.Time sqltime=new java.sql.Time(utildate.getTime());
//        System.out.println(sqltime);


//        try {
//            long l = diffDays("2022.5.4 12:20:30", "2022.5.4 13:25:6");
//            System.out.println(l);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Date d = new Date();
//        System.out.println(d);//Wed May 04 16:56:14 CST 2022
//        System.out.println(d.toString());//
//        System.out.println(d.getTime());//1651654574323
//        System.out.println(d.toLocaleString());//2022-5-4 16:56:14
//        System.out.println(d.before(new Date(2000, 7, 30)));//true
//        System.out.println(d.after(new Date(1651654396068L)));//true
//        d.setTime(1651654396068L);//设置时间
//        d.compareTo(new Date(1651654396068L));//比较时间
//
//        Calendar ca = Calendar.getInstance();
//        Date date = new Date();
//        ca.setTime(date);
//        System.out.println("当前年份：" + ca.get(Calendar.YEAR));
//        System.out.println("当前月：" + (ca.get(Calendar.MONTH)+1));//初始月份是从0开始，所以输出值比是几月份小1
//        System.out.println("当前日期：" + ca.get(Calendar.DAY_OF_MONTH));
//        System.out.println("当前年份第几个星期：" + ca.get(Calendar.WEEK_OF_YEAR));
//        System.out.println("当前月份第几个星期：" + ca.get(Calendar.WEEK_OF_MONTH));
//        System.out.println("当前年份第几天：" + ca.get(Calendar.DAY_OF_YEAR));
//        System.out.println("当前月份第几天：" + ca.get(Calendar.DAY_OF_MONTH));
//        System.out.println("当前星期第几天：" + ca.get(Calendar.DAY_OF_WEEK));
//        System.out.println("当前月份第几个星期：" + ca.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//        System.out.println("当天第几个小时：" + ca.get(Calendar.HOUR_OF_DAY));
//        System.out.println("当前小时第几分钟：" + ca.get(Calendar.MINUTE));
//        System.out.println("当前分钟第几秒：" + ca.get(Calendar.SECOND));
//        System.out.println("当前秒数第几毫秒：" + ca.get(Calendar.MILLISECOND));

    }
}
