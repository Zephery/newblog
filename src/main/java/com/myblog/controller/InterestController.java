package com.myblog.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myblog.model.KeyAndValue;
import com.myblog.model.TopTen;
import com.myblog.model.Weibo;
import com.myblog.service.IWeiboService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/5 0:29
 * Description:
 */
@Controller
public class InterestController {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(InterestController.class);
    @Resource
    private IWeiboService weiboService;

    @RequestMapping("interest")
    public ModelAndView interest() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("interest");
        return mv;
    }

    @RequestMapping("weibonlp")
    public ModelAndView weibonlpdetail(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String weibo = request.getParameter("weibo");
        if (StringUtils.isEmpty(weibo)) {
            List<Weibo> weibos = weiboService.getAllWeibo();
            mv.addObject("weibos", weibos);
            mv.setViewName("weibonlp");
            return mv;
        }
        try {
            JsonObject object = weiboService.getWeiboDetail(weibo);
            mv.addObject("type", object.get("type"));
            JsonArray array = object.get("data").getAsJsonArray();
            Gson gson = new Gson();
            List<KeyAndValue> kvlist = new ArrayList<>();
            for (JsonElement element : array) {
                KeyAndValue keyAndValue = gson.fromJson(element, KeyAndValue.class);
                kvlist.add(keyAndValue);
            }
            mv.addObject("kvs", kvlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("weibonlp");
        return mv;
    }
}