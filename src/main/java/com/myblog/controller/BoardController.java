package com.myblog.controller;

import com.myblog.util.SingleToMany;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Zephery on 2017/6/23.
 */
@Controller
public class BoardController {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @RequestMapping("/board")
    public String modelAndView(HttpServletResponse response) {
        response.setHeader("Content-Encoding", "gzip");
        return "board";
    }

    @RequestMapping("/singleToMany2")
    @ResponseBody
    public String singleToMany2() throws Exception {
        SingleToMany.getInstance().test();
        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
}
