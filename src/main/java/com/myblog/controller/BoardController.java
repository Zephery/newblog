package com.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zephery on 2017/6/23.
 */
@Controller
public class BoardController {
//    @RequestMapping("board")
//    public ModelAndView modelAndView(){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("board");
//        return modelAndView;
//    }
    @RequestMapping("board")
    public String modelAndView(HttpServletResponse response){
        response.setHeader("Content-Encoding","gzip");
        return "board";
    }
}
