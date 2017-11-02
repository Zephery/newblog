package com.myblog.util;

/**
 * Created by Zephery on 2017/6/29.
 */
public class TestUtil {
    public static void main(String[] args) {
        new TestUtil().print("13131");
    }

    public void printint(int i) {
        System.out.println(i);
    }

    private static boolean judegeuri(String uri) {
        return uri.contains("index.html") || uri.contains("tech.html") || uri.contains("life.html") || uri.contains("trip.html")
                || uri.contains("log.html") || uri.contains("board.html") || uri.contains("aboutme.html") || uri.contains("donate.html")
                || uri.contains("weibonlp.html") || uri.contains("interest.html")
                || uri.contains("search.html") || uri.contains("getblogdetail.html") || uri.equals("/");
    }

    public <T> T print(T t) {
        System.out.println(t);
        return t;
    }
}
