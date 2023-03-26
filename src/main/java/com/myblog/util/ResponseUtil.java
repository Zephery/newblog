package com.myblog.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;


public class ResponseUtil {

    public static void write(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(obj.toString());
        out.flush();
        out.close();
    }
}
