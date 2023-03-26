package com.myblog.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.myblog.model.FanPie;
import com.myblog.model.TopTen;
import com.myblog.util.IPUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Zephery on 2017/6/23.
 */
@Slf4j
@Controller
public class LogController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private Environment environment;
    @Resource
    private IPUtils ipUtils;
    private static final String METRIC_NAME = "system.cpu.usage";


    @RequestMapping("/log")
    public ModelAndView log(HttpServletRequest request) throws IOException {
        log.info("log");
        String temp = stringRedisTemplate.opsForValue().get("daterange");
        String pv_count = stringRedisTemplate.opsForValue().get("pv_count");
        String visitor_count = stringRedisTemplate.opsForValue().get("visitor_count");
        String bounce_ratio = stringRedisTemplate.opsForValue().get("bounce_ratio");
        String avg_visit_time = stringRedisTemplate.opsForValue().get("avg_visit_time");
        String top_ten = stringRedisTemplate.opsForValue().get("top_ten");
        String source = stringRedisTemplate.opsForValue().get("source");
        String rukou_str = stringRedisTemplate.opsForValue().get("rukouyemian");
        String diyu_str = stringRedisTemplate.opsForValue().get("diyu");
        String pv_sum = stringRedisTemplate.opsForValue().get("pv_sum");
        String uv_sum = stringRedisTemplate.opsForValue().get("uv_sum");
        Gson gson = new Gson();
        //前十访问页面
        JsonArray array = JsonParser.parseString(top_ten).getAsJsonArray();
        List<TopTen> topTens = new ArrayList<>();
        for (JsonElement element : array) {
            TopTen topTen = gson.fromJson(element, TopTen.class);
            topTens.add(topTen);
        }
        log.info("前十访问页面");
        //来源统计
        JsonArray sourcearray = JsonParser.parseString(source).getAsJsonArray();
        List<FanPie> sourcelist = new ArrayList<>();
        for (JsonElement element : sourcearray) {
            FanPie fanPie = gson.fromJson(element, FanPie.class);
            sourcelist.add(fanPie);
        }
        log.info("来源统计");
        //前十入口页面
        JsonArray rukouarray = JsonParser.parseString(rukou_str).getAsJsonArray();
        List<TopTen> rukou = new ArrayList<>();
        for (JsonElement element : rukouarray) {
            TopTen topTen = gson.fromJson(element, TopTen.class);
            rukou.add(topTen);
        }
        log.info("前十入口页面");
        //地域地图
        JsonArray diyuarray = JsonParser.parseString(diyu_str).getAsJsonArray();
        List<TopTen> diyu = new ArrayList<>();
        for (JsonElement element : diyuarray) {
            TopTen topTen = gson.fromJson(element, TopTen.class);
            diyu.add(topTen);
        }
        rukou.sort((o1, o2) -> {
            if (o1.getPv_count() > o2.getPv_count()) {
                return -1;
            } else {
                return 1;
            }
        });
        diyu.sort((o1, o2) -> {
            if (o1.getPv_count() > o2.getPv_count()) {
                return -1;
            } else {
                return 1;
            }
        });
        ModelAndView mv = new ModelAndView();
        String ip = IPUtils.getIpAddr(request);
//        String yourcity = IPUtils.getAddressByIP(ip);
        String yourcity = "功能已注释";
        log.info("yourcity is {}", yourcity);
        mv.addObject("ip", ip);
        mv.addObject("yourcity", yourcity);
        mv.addObject("daterange", JsonParser.parseString(temp).getAsJsonArray());
        mv.addObject("topTens", topTens);
        mv.addObject("pv_count", pv_count);
        mv.addObject("visitor_count", visitor_count);
        mv.addObject("bounce_ratio", bounce_ratio);
        mv.addObject("sourcelist", sourcelist);
        mv.addObject("rukou", rukou.subList(0, rukou.size() > 5 ? 5 : rukou.size()));
        mv.addObject("avg_visit_time", avg_visit_time);
        mv.addObject("diyu", diyu);
        mv.addObject("diyumax", diyu.get(0).getPv_count());
        mv.addObject("pv_sum", pv_sum);
        mv.addObject("uv_sum", uv_sum);
        log.info("host");
        //host
        Set<String> profiles = new HashSet<>(Arrays.asList(environment.getActiveProfiles()));
        if (profiles.contains("dev")) {
            mv.addObject("host", "localhost");
        } else {
            mv.addObject("host", ipUtils.getServerIp());
        }

        mv.setViewName("log");
        return mv;
    }
}
