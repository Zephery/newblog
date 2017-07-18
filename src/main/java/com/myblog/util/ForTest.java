package com.myblog.util;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by Zephery on 2017/1/23.
 */
public class ForTest {
    public static void main(String args[]){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(DateTime.now().toDate());
        HashMap<String,String> map=new HashMap<>();
    }
}
