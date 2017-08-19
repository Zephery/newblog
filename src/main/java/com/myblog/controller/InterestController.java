package com.myblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/5 0:29
 * Description:
 */
@Controller
public class InterestController {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(InterestController.class);

    @RequestMapping("interest")
    public ModelAndView interest() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("interest");
        return mv;
    }

    @RequestMapping("weibonlp")
    public ModelAndView weibonlp() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("weibonlp");
        return mv;
    }
}