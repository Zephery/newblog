package com.myblog.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ni Shengwu
 * @Description
 */
public class DateUtil {

    /**
     * @param date
     * @param format
     * @return
     * @author Ni Shengwu
     */
    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * @param str
     * @param format
     * @return
     * @throws Exception
     * @author Ni Shengwu
     */
    public static Date formatString(String str, String format) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static String getCurrentDateStr() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }

    public static List<String> findDates(String start, String end) {
        Date startDate = DateTime.parse(start).toDate();
        Date endDate = DateTime.parse(end).toDate();
        List<String> lDate = new ArrayList<>();
        //lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(startDate);
        calBegin.add(Calendar.DAY_OF_MONTH, 1);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(endDate);
        // 测试此日期是否在指定日期之后
        while (endDate.after(calBegin.getTime())) {
        }
        return lDate;
    }

    public static void main(String args[]) {
        List<String> date = findDates("20170610", "20170625");
        System.out.println();
    }
}
