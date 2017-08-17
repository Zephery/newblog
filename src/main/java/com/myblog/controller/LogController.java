package com.myblog.controller;

import com.google.gson.*;
import com.myblog.JMX.JMXClient;
import com.myblog.model.FanPie;
import com.myblog.model.TopTen;
import com.myblog.util.IPUtils;
import com.myblog.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Zephery on 2017/6/23.
 */
@Controller
public class LogController {
    private final static Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("log")
    public ModelAndView log(HttpServletRequest request) {
        JedisUtil jedis = JedisUtil.getInstance();    //remember not to close
        String temp = jedis.get("daterange");
        String pv_count = jedis.get("pv_count");
        String visitor_count = jedis.get("visitor_count");
        String ip_count = jedis.get("ip_count");
        String bounce_ratio = jedis.get("bounce_ratio");
        String avg_visit_time = jedis.get("avg_visit_time");
        String top_ten = jedis.get("top_ten");
        String source = jedis.get("source");
        String rukou_str = jedis.get("rukouyemian");
        String diyu_str = jedis.get("diyu");
        String pv_sum = jedis.get("pv_sum");
        String uv_sum = jedis.get("uv_sum");
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        //前十访问页面
        JsonArray array = parser.parse(top_ten).getAsJsonArray();
        List<TopTen> topTens = new ArrayList<>();
        for (JsonElement element : array) {
            TopTen topTen = gson.fromJson(element, TopTen.class);
            topTens.add(topTen);
        }
        //来源统计
        JsonArray sourcearray = parser.parse(source).getAsJsonArray();
        List<FanPie> sourcelist = new ArrayList<>();
        for (JsonElement element : sourcearray) {
            FanPie fanPie = gson.fromJson(element, FanPie.class);
            sourcelist.add(fanPie);
        }
        //前十入口页面
        JsonArray rukouarray = parser.parse(rukou_str).getAsJsonArray();
        List<TopTen> rukou = new ArrayList<>();
        for (JsonElement element : rukouarray) {
            TopTen topTen = gson.fromJson(element, TopTen.class);
            rukou.add(topTen);
        }
        //地域地图
        JsonArray diyuarray = parser.parse(diyu_str).getAsJsonArray();
        List<TopTen> diyu = new ArrayList<>();
        for (JsonElement element : diyuarray) {
            TopTen topTen = gson.fromJson(element, TopTen.class);
            diyu.add(topTen);
        }
        rukou.sort(new Comparator<TopTen>() {
            @Override
            public int compare(TopTen o1, TopTen o2) {
                if (o1.getPv_count() > o2.getPv_count()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        diyu.sort(new Comparator<TopTen>() {
            @Override
            public int compare(TopTen o1, TopTen o2) {
                if (o1.getPv_count() > o2.getPv_count()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        List<String> jmx_memory_use = JedisUtil.getInstance().lrange("jmx_memory_use");
        List<String> jmx_memory_time = JedisUtil.getInstance().lrange("jmx_memory_time");
        JsonArray array1 = new JsonArray();
        for (int i = 0; i < jmx_memory_time.size(); i++) {
            JsonObject object = new JsonObject();
            object.addProperty("x", jmx_memory_time.get(i));
            object.addProperty("y", Integer.parseInt(jmx_memory_use.get(i)));
            array1.add(object);
        }
        ModelAndView mv = new ModelAndView();
        String ip = IPUtils.getIpAddr(request);
        String yourcity = IPUtils.getAddressByIP(ip);
        mv.addObject("ip", ip);
        mv.addObject("yourcity", yourcity);
        mv.addObject("daterange", parser.parse(temp).getAsJsonArray());
        mv.addObject("topTens", topTens);
        mv.addObject("pv_count", pv_count);
        mv.addObject("visitor_count", visitor_count);
        mv.addObject("ip_count", ip_count);
        mv.addObject("bounce_ratio", bounce_ratio);
        mv.addObject("sourcelist", sourcelist);
        mv.addObject("rukou", rukou.subList(0, rukou.size() > 5 ? 5 : rukou.size()));
        mv.addObject("avg_visit_time", avg_visit_time);
        mv.addObject("diyu", diyu);
        mv.addObject("diyumax", diyu.get(0).getPv_count());
        mv.addObject("pv_sum", pv_sum);
        mv.addObject("uv_sum", uv_sum);
        mv.addObject("jmx_data", array1);
        mv.addObject("jmx_memory_use", jmx_memory_use);
        mv.setViewName("log");
        return mv;
    }

    @RequestMapping("/jmx")
    @ResponseBody
    public void jmx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String time = request.getParameter("time");
        int aa = Integer.parseInt(JMXClient.getInstance().getJVMUsage().toString());
        Integer bb = aa / 1048576;
        List<String> jmx_memory_use = JedisUtil.getInstance().lrange("jmx_memory_use");
        List<String> jmx_memory_time = JedisUtil.getInstance().lrange("jmx_memory_time");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(bb.toString());
    }
}
