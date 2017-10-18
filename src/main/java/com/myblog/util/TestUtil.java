package com.myblog.util;

/**
 * Created by Zephery on 2017/6/29.
 */
public class TestUtil {
    private static boolean judegeuri(String uri) {
        return uri.contains("index.html") || uri.contains("tech.html") || uri.contains("life.html") || uri.contains("trip.html")
                || uri.contains("log.html") || uri.contains("board.html") || uri.contains("aboutme.html") || uri.contains("donate.html")
                || uri.contains("weibonlp.html") || uri.contains("interest.html")
                || uri.contains("search.html") || uri.contains("getblogdetail.html") || uri.equals("/");
    }

    public static void main(String[] args) {
        String uri = "joijeoifjaoiwejofweif/";
        long start = System.currentTimeMillis();
        System.out.println(judegeuri(uri));
        System.out.println(System.currentTimeMillis() - start);
    }
}
