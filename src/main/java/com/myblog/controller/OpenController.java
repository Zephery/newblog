package com.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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