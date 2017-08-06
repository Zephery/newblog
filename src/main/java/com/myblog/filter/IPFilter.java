package com.myblog.filter;


import com.myblog.dao.IpLogMapper;
import com.myblog.model.IpLog;
import com.myblog.util.IPUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这种方法无法导致ipLogMapper无法注入，有时间再自行修改，其中包括web.xml中的修改。
 */
@Component
public class IPFilter implements Filter {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(IPFilter.class);
    String[] forbidIps = null;
    @Autowired
    @Qualifier("ipLogMapper")
    private IpLogMapper ipLogMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取在web.xml中配置的<filter>的初始化参数
        String initParamter = filterConfig.getInitParameter("forbidIps");
        if (initParamter != null) {
            forbidIps = initParamter.split(",");
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        //由于拦截器定义的拦截规范是/* ,所以所有的请求都会被拦截，为了防止死循环 开一个特例显示具体的访问结果
        if (((HttpServletRequest) request).getRequestURI().contains("404.jsp")) {
            filterChain.doFilter(request, response);
            return;//不加return 会继续执行请求。
        }
        if (request.getAttribute("uri") != null) {
            String uri = request.getAttribute("uri").toString();
        }
        String remoteAddr = IPUtils.getIpAddr((HttpServletRequest) request);
        IpLog ipLog = new IpLog();
        ipLog.setIp(remoteAddr);
        ipLog.setIpTime(DateTime.now().toDate());
        ipLogMapper.insert(ipLog);
        if (forbidIps != null) {
            for (int i = 0; i < forbidIps.length; i++) {
                if (forbidIps[i].equals(remoteAddr)) {
                    //如果访问的ip与配置的ip相等 则直接过滤掉。
                    ((HttpServletResponse) response).sendRedirect("404.jsp");
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
