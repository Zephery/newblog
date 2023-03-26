package com.myblog.controller;

import com.myblog.model.User;
import com.myblog.model.UserInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Zephery
 * @since 2018/1/30 18:51
 */
@Slf4j
@Controller
@RequestMapping("/cy")
public class CYController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //该接口只有当畅言已登录，getUserInfo返回未登录时，才会被调用用来登录自身网站
    @RequestMapping("/login")
    public void loginByCy(@RequestParam(value = "callback") String callback,
                          @RequestParam(value = "user_id") String user_id,
                          @RequestParam(value = "nickname") String nickname,
                          @RequestParam(value = "sign") String sign, @RequestParam(value = "img_url") String img_url,
                          @RequestParam(value = "profile_url") String profile_url, HttpServletResponse resp)
            throws Exception {
        //自己网站的登录逻辑，记录登录信息到cookie
        Cookie cookie = new Cookie("user_id", user_id);
        resp.addCookie(cookie);
        resp.getWriter().write(callback + "({\"user_id\":" + user_id + ",reload_page:0})");
    }

    @RequestMapping("/loginout")
    public void loginBySite(@RequestParam(value = "callback") String callback, HttpServletResponse resp) throws Exception {
        //清除自己网站cookies信息,同时前端logout.js代码用来清理畅言cookie
        resp.getWriter().write(callback + "({\"code\":\"1\",reload_page:0, js-src:logout.js})");
    }

    //该接口在页面每一次加载时都会被调用，用来判断用户在自己网站是否登录
    @RequestMapping("/getUserInfo")
    @SuppressWarnings("unchecked")
    public void getUserInfo(@RequestParam(value = "callback") String callback, HttpServletRequest res,
                            HttpServletResponse resp) throws Exception {
        UserInfo userinfo = new UserInfo();
        Cookie[] cookies = res.getCookies();
        if (!isContains("user_id", cookies)) {//此处为模拟逻辑，具体实现可以变化
            userinfo.setIs_login(0);//用户未登录
        } else {
            userinfo.setIs_login(1);//用户已登录
            User user = new User();
//            user.setUser_id(Integer.parseInt(getCookieValue("user_id", cookies))); //该值具体根据自己用户系统设定
//            user.setNickname(getCookieValue("nickname", cookies)); //该值具体根据自己用户系统设定
//            user.setImg_url(getCookieValue("img_url", cookies)); //该值具体根据自己用户系统设定，可以为空
//            user.setProfile_url(getCookieValue("profile_url", cookies));//该值具体根据自己用户系统设定，可以为空
//            user.setSign(getCookieValue("sign", cookies)); //签名已弃用，任意赋值即可
//            userinfo.setUser(user);
            new Thread(() -> stringRedisTemplate.opsForList().leftPush("cyuser", user.toString()));
        }
//        resp.setContentType("application/x-javascript");//指定传输格式为js
//        resp.getWriter().write(callback + "(" + JSONArray.toJSONString(userinfo) + ")");//拼接成jsonp格式
    }

    //该方法判断cookie中是否存在键值为key的value值
    public boolean isContains(String key, Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                if (cookie.getValue() != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    //该方法获取cookie中键值为key的value值
    public Object getCookieValue(String key, Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key))
                return cookie.getValue();
        }
        return null;
    }
}