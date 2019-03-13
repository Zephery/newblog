package com.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wenzhihuai
 * @since 2019-03-13 19:18
 */
@Controller
public class LiveController {
    @RequestMapping(value = "/live")
    public ModelAndView donate() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("live");
        return mv;
    }
}
