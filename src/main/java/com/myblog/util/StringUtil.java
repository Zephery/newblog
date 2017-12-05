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
        Pattern p = Pattern.compile("[0-9A-Z]+");
        Matcher m = p.matcher(str);
        String string = "";
        while (m.find()) {
            string = m.group();
        }
        return Integer.parseInt(string);
    }

    public static void main(String[] args) {
        System.out.println(stringgetint("tomcat9 (1233)"));
    }
}
