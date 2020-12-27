package com.myblog.aspect;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 本类定义了全局exception处理
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler<T> {

    /**
     * 用于兜底的普通exception处理
     *
     * @param req http请求信息
     * @param e   exception信息
     * @return 出错信息
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<T> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("", e);
        String errorMsg = e.getMessage();
        return ResponseEntity.badRequest().build();
    }


}
