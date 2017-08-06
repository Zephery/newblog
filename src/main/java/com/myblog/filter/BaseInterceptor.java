package com.myblog.filter;

import com.myblog.dao.IpLogMapper;
import com.myblog.model.IpLog;
import com.myblog.util.IPUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/6 19:04
 * Description: 自定义拦截器，目前只是增加IP记录的功能
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    @Resource
    private IpLogMapper ipLogMapper;

    /**
     * 获取一个新的session，并将它的ip地址存入数据库中
     *
     * @param request
     * @param response
     * @param o
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        try {
            if (request.getSession().isNew()) {     //判断是不是新的一个session
                String real_ip = IPUtils.getIpAddr(request);
                IpLog ipLog = new IpLog();
                ipLog.setIp(real_ip);
                ipLog.setIpTime(DateTime.now().toDate());
                ipLogMapper.insert(ipLog);
            }
        } catch (Exception e) {
            logger.error("preHandle error", e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}