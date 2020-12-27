//package com.myblog.filter;
//
//
//import com.myblog.dao.IpLogMapper;
//import com.myblog.model.IpLog;
//import com.myblog.util.IPUtils;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 这种方法无法导致ipLogMapper无法注入，有时间再自行修改，其中包括web.xml中的修改。
// */
//@Component
//public class IPFilter implements Filter {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(IPFilter.class);
//    private String[] forbidIps = null;
//    @Autowired
//    @Qualifier("ipLogMapper")
//    private IpLogMapper ipLogMapper;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //获取在web.xml中配置的<filter>的初始化参数
//        String initParamter = filterConfig.getInitParameter("forbidIps");
//        if (initParamter != null) {
//            forbidIps = initParamter.split(",");
//        }
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // TODO Auto-generated method stub
//    }
//
////    /**
////     * 检查params里的参数是否非法，非法则返回true
////     *
////     * @param params 被检查的参数，params数组可以为字符串数组，也可以为字符串数组的数组
////     * @return 当params里字符串含有非法字符串时返回true, 否则返回false；
////     */
////    public boolean isInvalidParams(Object[] params) {
////        if (params.length == 0) {
////            return false;
////        }
////        for (int i = 0; i < params.length; i++) {
////            String[] value;
////            if (params[i] instanceof String[]) {
////                value = (String[]) params[i];
////            } else {
////                value = new String[]{(String) params[i]};
////            }
////            for (int j = 0; j < value.length; j++) {
////                if (containInvalidString(value[j])) {
////                    logger.error("containInvalidString---" + value[j]);
////                    return true;
////                }
////            }
////        }
////        return false;
////    }
////
////    private boolean containInvalidString(String content) {
////        Pattern[] patterns = null;
////        String regex = "";
////
////        /**
////         * 这里需求修改一下,原来我们是读配置的。
////         *
////         * List list = ContextManager.getInstance().getStrStart("xss.str");
////         */
////        List list = new ArrayList();
////        list.add("<\\\\s*script[^>]*>.*<\\\\s*/\\\\s*script[^>]*>");
////        list.add("<.*script[^>]*>.*<.*script[^>]*>");
////        list.add("<.*script[^>]*>");
////        list.add("<.*embed[^>]*>");
////        list.add("<.*object[^>]*>");
////        list.add("<.*layer[^>]*>");
////        list.add("<.*style[^>]*>");
////        list.add("<.*meta[^>]*>");
////        list.add("<.*iframe[^>]*>");
////        list.add("<.*frame[^>]*>");
////        list.add("<.*link[^>]*>");
////        list.add("<.*import[^>]*>");
////        list.add(".*href.*");
////        list.add("eval\\W");
////        list.add("and\\W");
////        list.add("or\\W");
////        list.add("\\|\\|");
////        list.add("\\|scanner\\|");
////        list.add("prompt\\(");
////        list.add("<.*xml[^>]*>");
////        list.add("(<style[^>]*>[^>]+)(expression|javascript|vbscript|-moz-binding)(.*</style[^>]*>)");
////        list.add("(<[^>]*)AllowScriptAccess([^>]*>)");
////        list.add("drop\\\\s+table|xp_cmdshell|netlocalgroup\\\\s+administrators|net\\\\s+user|\"\"|((insert|delete|update|truncate|exec|and|or|union)\\\\s+)");
////        list.add("%00");
////        list.add("<\\\\s*a\\\\s+[^>]*href[^>]*http\\://");
////        list.add("(<[^>]*)(javascript|vbscript|livescript|ms-its|mhtml|data|firefoxurl|mocha):([^>]*>)");
////        list.add("(<[^>]*style=[^>]+)(expression|javascript|vbscript|-moz-binding)([^>]*>)");
////        list.add("(<[^>]*style\\=.*)/\\\\*.*\\\\*/([^>]*>)");
////        list.add("(<style[^>]*>.*)/\\\\*.*\\\\*/(.*</style[^>]*>)");
////        list.add("onmouseover\\\\s*\\=|onmouseout\\\\s*\\=|onmousedown\\\\s*\\=|onmouseup\\\\s*\\=|onmousemove\\\\s*\\=|onclick\\\\s*\\=|ondblclick\\\\s*\\=|onkeypress\\\\s*\\=|onkeydown\\\\s*\\=|onkeyup\\\\s*\\=|ondragstart\\\\s*\\=|onerrorupdate\\\\s*\\=|onhelp\\\\s*\\=|onreadystatechange\\\\s*\\=|onrowenter\\\\s*\\=|onrowexit\\\\s*\\=|onselectstart\\\\s*\\=|onload\\\\s*\\=|onunload\\\\s*\\=|onbeforeunload\\\\s*\\=|onblur\\\\s*\\=|onerror\\\\s*\\=|onfocus\\\\s*\\=|onresize\\\\s*\\=|onscroll\\\\s*\\=|oncontextmenu\\\\s*\\=|ACUstart|ACUend|cmdshell|truncate|alert|confirm|prompt");
////        // 下面这些定符是struts2漏洞的
////
////        list.add(".*getWriter.*");
////        list.add(".*FileOutputStream.*");
////        list.add(".*getRuntime.*  ");
////        list.add(".*getRequest.*");
////        list.add(".*getProperty.*");
////        list.add(".*\\\\\\\\u0023.*");
////        list.add(".*\\\\\\\\43.*");
////        if (list.size() == 0) {
////            return false;
////        }
////        patterns = new Pattern[list.size()];
////        for (int j = 0; j < list.size(); j++) {
////            regex = (String) list.get(j);
////            if (StringUtils.isNotEmpty(regex)) {
////                patterns[j] = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
////            }
////        }
////        for (int k = 0; k < patterns.length; k++) {
////            Matcher m = patterns[k].matcher(content);
////            if (m.find()) {
////                return true;
////            }
////        }
////        return false;
////    }
//}
