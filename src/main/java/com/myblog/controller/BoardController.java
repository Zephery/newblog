package com.myblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zephery on 2017/6/23.
 */
@Controller
public class BoardController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @RequestMapping("board")
    public String modelAndView(HttpServletResponse response) {
        response.setHeader("Content-Encoding", "gzip");
        return "board";
    }
}
