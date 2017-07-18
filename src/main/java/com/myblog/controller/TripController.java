package com.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zephery on 2017/6/19.
 */
@Controller
public class TripController {
    @RequestMapping("trip")
    public ModelAndView gettrip(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("trip");
        return modelAndView;
    }
}
