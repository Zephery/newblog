package com.myblog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author
 * @Description
 */
public class StringUtil {

    /**
     * @param str
     * @return
     * @Description
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str
     * @return
     * @Description
     */
    public static boolean isNotEmpty(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str
     * @return
     * @Description
     */
    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * @param list
     * @return
     * @Description
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String l : list) {
            if (isNotEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }

    /**
     * 标签中提取数字
     *
     * @param str
     * @return
     */
    public static Integer stringgetint(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String num_str = m.replaceAll("").trim();
        return Integer.parseInt(num_str);
    }

    public static void main(String[] args) {
        System.out.println(stringgetint("python(342)"));
    }
}
