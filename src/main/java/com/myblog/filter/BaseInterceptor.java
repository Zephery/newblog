package com.myblog.filter;

import com.myblog.service.IAsyncService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/6 19:04
 * Description: 自定义拦截器，目前只是增加IP记录的功能，并记录后台反应的时长
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("StopWatch-StartTime");
    private NamedThreadLocal<Integer> visitNum = new NamedThreadLocal<>("visitNum");
    @Resource
    private IAsyncService asyncService;

    private static boolean judegeuri(String uri) {
        return uri.contains("index.html") || uri.contains("tech.html") || uri.contains("life.html") || uri.contains("trip.html")
                || uri.contains("log.html") || uri.contains("board.html") || uri.contains("aboutme.html") || uri.contains("donate.html")
                || uri.contains("weibonlp.html") || uri.contains("interest.html")
                || uri.contains("search.html") || uri.contains("getblogdetail.html") || uri.equals("/");
    }

    public static void main(String[] args) {
        String uri = "/";
        long start = System.currentTimeMillis();
        System.out.println(judegeuri(uri));
        System.out.println(System.currentTimeMillis() - start);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        return true;
    }

    /**
     * 获取一个新的session，并将它的ip地址存入数据库中
     *
     * @param request
     * @param response
     * @param o
     * @return
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object o, ModelAndView modelAndView) throws Exception {
//        try {
//            asyncService.insertMongo(request);
//            long endTime = System.currentTimeMillis();//2、结束时间
//            long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
//            long consumeTime = endTime - beginTime;//3、消耗的时间
//            String uri = request.getRequestURI();
//            if (StringUtils.isEmpty(uri) || !judegeuri(uri)) {
//                return;
//            }
//            String real_ip = IPUtils.getIpAddr(request);
//            IpLog ipLog = new IpLog();
//            ipLog.setSid(request.getSession().getId());
//            ipLog.setIp(real_ip);
//            if (request.getHeader("referer") != null) {
//                ipLog.setReferer(request.getHeader("referer"));
//            }
//            ipLog.setIpTime(DateTime.now().toDate());
//            ipLog.setArea(IPUtils.getAddressByIP(real_ip));
//            ipLog.setUri(uri);
//            ipLog.setResponseTime(consumeTime);
//            if (request.getSession().isNew()) {     //判断是不是新的一个session
//                ipLog.setVisitNum(1);
//                visitNum.set(1);
//            } else {
//                ipLog.setVisitNum(visitNum.get() == null ? 0 : visitNum.get() + 1);
//            }
//            if (!uri.contains("ajaxsearch.html")) {
//                asyncService.insertIpLog(ipLog);
//            }
//        } catch (Exception e) {
//            logger.error("Handle error", e);
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}