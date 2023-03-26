package com.myblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Zephery
 * @since 2018/1/28 16:40
 */
@Controller
public class OpenController {


    @RequestMapping("/open")
    public ModelAndView open(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/open");
        return mv;
    }

    @RequestMapping("/elk")
    public ModelAndView elk() {
        return new ModelAndView("/elk");
    }

    @RequestMapping("/myresume")
    public ModelAndView myresume() {
        return new ModelAndView("/myresume");
    }
}