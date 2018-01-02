package com.myblog.util;

/**
 * Created by Zephery on 2017/6/29.
 */
public class TestUtil {
    public static void main(String[] args) {
        String foo = "1";
        char a = '1';
        String bar = foo;
        foo = "2" + 1;
        System.out.println(bar);
        System.out.println(foo);
        System.out.println(a);
    }
}
