package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.myblog.model.Image;
import com.myblog.service.IImageService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Zephery on 2017/6/19.
 */

/**
 * {
 * "is_robot": false,
 * "photo_id": 10596568,
 * "unm": "一抹红嫣",
 * "uid": 1693226,
 * "cmts": [],
 * "good": false,
 * "common": false,
 * "album_wait_audit": false,
 * "price": 0,
 * "rid": 95800538,
 * "buylnk": "",
 * "sender_wait_audit": false,
 * "zanc": 0,
 * "sta": 0,
 * "ava": "http://cdn.duitang.com/uploads/people/201401/15/20140115014745_4K4Tr.png",
 * "coupon_price": 0,
 * "albnm": "花花女子与花花世界",
 * "iht": 172,
 * "albid": 5542595,
 * "favc": 99,
 * "wait_audit": false,
 * "ruid": 802123,
 * "id": 97290923,
 * "repc": 0,
 * "isrc": "http://cdn.duitang.com/uploads/blog/201309/14/20130914190628_rFT3G.thumb.200_0.jpeg",
 * "msg": "【辣味椒盐虾】1、 洋葱切丝、葱切小段；2、 锅中放油，稍稍多一些，虾入油锅炸；3、 一直大火炸，炸至虾壳
 * 跟虾肉分离,捞起沥
 * 油；4、 锅中放一部分炸虾的油，放干辣椒爆香略炒；5、 放洋葱，小火炒香,放入炸好的虾，转大火，稍稍翻炒,放入椒盐，炒匀；6、
 * 撒葱花，出锅。"
 * }
 */
@Controller
public class TripController {
    private final static Logger logger = LoggerFactory.getLogger(TripController.class);
    @Resource
    private IImageService imageService;


    @RequestMapping("/trip")
    public ModelAndView gettrip() {
        return new ModelAndView("trip");
    }

    @RequestMapping("/ajaxpic")
    public void ajaxpic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = request.getParameter("page");
        Integer pagenum;
        if (StringUtils.isEmpty(page)) {
            pagenum = 1;
        } else {
            pagenum = Integer.parseInt(page);
        }
        PageHelper.startPage(pagenum, 15);
        List<Image> lists = imageService.getAllImage();
        PageInfo<Image> images = new PageInfo<>(lists);
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for (Image image : images.getList()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("photo_id", image.getImageid());
            jsonObject.addProperty("unm", image.getImagename());
            jsonObject.addProperty("uid", image.getImageid());
            jsonObject.addProperty("ava", image.getImagepath());
            jsonObject.addProperty("isrc", image.getImagepath());
            jsonObject.addProperty("id", image.getImageid());
            jsonObject.addProperty("msg", image.getContent());
            jsonObject.addProperty("iht", image.getIht());
            jsonArray.add(jsonObject);
        }
        long totalcount = images.getPages();
        String temp = ",\"totalcount\":" + String.valueOf(totalcount) + ",\"has_next\":" +
                String.valueOf(pagenum < totalcount) + "";
        String str = gson.toJson(jsonArray);
        String json = "{\"data\":{\"blogs\":" + str + ",\"hasrp\":true,\"pgsource\":" +
                "\"_\",\"nopth\":false" + temp + "},\"success\":true}";
        response.getWriter().write(json);
    }

    @RequestMapping("/tripbypage")
    public String tripbypage() {
        return "jofej";
    }

    @RequestMapping("/updateallpic")
    public void temppic() {
        try {
            List<Image> list = imageService.getAllImage();
            for (Image image : list) {
                URL url = new URL(image.getImagepath());
                BufferedImage bufferedImg = ImageIO.read(url.openStream());
                int imgWidth = bufferedImg.getWidth();
                int height = bufferedImg.getHeight();
                int iht = (height * 200) / imgWidth;
                image.setIht(iht);
                imageService.updateiht(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/imagedetail")
    public void imagedetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String str_imageid = request.getParameter("imageid");
        try {
            Integer imageid = Integer.parseInt(str_imageid);
            Image image = imageService.getImagebyId(imageid);
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(image));
        } catch (Exception e) {
            logger.error("imageid error", e);
            response.getWriter().write("imageid error");
        }
    }
}
