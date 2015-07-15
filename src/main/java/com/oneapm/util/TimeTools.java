package com.oneapm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTools {

        public static SimpleDateFormat formatDay = new SimpleDateFormat("yyyyMMdd");
        public static SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public static int today;

        public static String getStrTime(String cc_time) {
                String re_StrTime = null;
                long lcc_time = Long.valueOf(cc_time);
                re_StrTime = formatTime.format(new Date(lcc_time * 1000L));
                return re_StrTime;
        }

        public static String format() {
                Date date = new Date();
                return formatTime.format(date);
        }

        public static String format(Date date) {
                return formatTime.format(date);
        }

        /**
         * 两日相隔天数
         * 
         * @param date
         * @return
         * @throws ParseException
         */
        public static int fromToday(String date, boolean choose) throws ParseException {
                Date dateTime = formatTime.parse(date);
                int time = Integer.parseInt(formatDay.format(dateTime));
                if (choose) {
                        return (today - time);
                }
                int today = Integer.parseInt(formatDay.format(new Date()));
                return (today - time);
        }

        public static int fromHour(String date) throws ParseException {
                Date dateTime = formatTime.parse(date);
                Date today = new Date();
                long da = dateTime.getTime();
                long to = today.getTime();
                return (int) ((to - da) / 1000 / 60 / 60);
        }

        public static String next(String start, int day) throws ParseException {
                Date date = new Date();
                long time = formatTime.parse(start).getTime() + day * 60 * 60 * 24 * 1000;
                date.setTime(time);
                return formatTime.format(date);
        }

        public static void formatToday() {
                today = Integer.parseInt(formatDay.format(new Date()));
        }
        
        /**
         * 获取一个月的第几天
         * 
         * @param day
         * @return
         */
        @SuppressWarnings("deprecation")
        public static int formatDay(int day) {
                if (day < 0 || day > 28)
                        return 0;
                Date date = new Date();
                int d = date.getDate();
                if (day <= 0)
                        return d;
                d -= day;
                if (d > 0)
                        return d;
                int m = date.getMonth();
                m -= 1;
                if (m == 0)
                        m = 12;
                return d + MonthDay(date.getYear(), m);
        }

        public static int MonthDay(int year, int month) {
                int[] monthDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                        monthDay[1]++;
                return monthDay[month];
        }

        public static int apartDay(String startTime, String endTime) throws ParseException {
                return (int) ((formatTime.parse(startTime).getTime() - formatTime.parse(endTime).getTime()) / (24 * 60 * 60 * 1000));
        }

        public static int apartHour(String startTime, String endTime) {
                try {
                        return (int) ((formatTime.parse(startTime).getTime() - formatTime.parse(endTime).getTime()) / (60 * 60 * 1000));
                } catch (Exception e) {

                }
                return -1;
        }

        /**
         * 获取day天前的日期零点
         * 
         * @param day
         * @return
         */
        @SuppressWarnings("deprecation")
        public static String getDateTime(int day) {
                Date date = new Date();
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                long time = (date.getTime() / 1000) - day * 60 * 60 * 24;
                date.setTime(time * 1000);
                String dateTime = formatTime.format(date);
                return dateTime;
        }

        public static String addHour(int hour) throws ParseException {
                Date date = new Date();
                Date dateTime = formatTime.parse(format());
                long da = dateTime.getTime();
                date.setTime(da + hour * 1000 * 60 * 60);
                return formatTime.format(date);
        }

        @SuppressWarnings("deprecation")
        public static String getDateTime(String dateTime) throws ParseException {
                Date date = formatTime.parse(dateTime);
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                return formatTime.format(date);
        }

        /**
         * 获取距dateTime day天前的零点
         * 
         * @param day
         * @param dateTime
         * @return
         * @throws ParseException
         */
        @SuppressWarnings("deprecation")
        public static String getDateTime(int day, String dateTime) throws ParseException {
                Date date = formatTime.parse(dateTime);
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                long time = (date.getTime() / 1000) - day * 60 * 60 * 24;
                date.setTime(time * 1000);
                dateTime = formatTime.format(date);
                return dateTime;
        }

        public static String week(String time, int n, int week) {
                try {
                        Date date = formatTime.parse(time);
                        int w = dayForWeek(date);
                        if (w == 7) {
                                if (week == 7) {
                                        return weekDay(n, week, date);
                                } else {
                                        return weekDay(n + 1, week, date);
                                }
                        } else {
                                if (week == 7) {
                                        return weekDay(n - 1, week, date);
                                } else {
                                        return weekDay(n, week, date);
                                }
                        }
                } catch (Exception e) {
                }
                return null;
        }

        public static int dayForWeek(Date date) throws Exception {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                int dayForWeek = 0;
                if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                        dayForWeek = 7;
                } else {
                        dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                }
                return dayForWeek;
        }

        @SuppressWarnings("deprecation")
        public static String weekDay(int n, int week, Date date) throws ParseException {
                if (week <= 0 || week > 7) {
                        return null;
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, -n * 7);
                switch (week) {
                case 1:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        break;
                case 2:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                        break;
                case 3:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                        break;
                case 4:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                        break;
                case 5:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        break;
                case 6:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                        break;
                case 7:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        break;
                default:
                        break;
                }
                String day = formatTime.format(cal.getTime());
                Date time = formatTime.parse(day);
                time.setHours(0);
                time.setMinutes(0);
                time.setSeconds(0);
                String dateTime = formatTime.format(time);
                return dateTime;
        }

}
