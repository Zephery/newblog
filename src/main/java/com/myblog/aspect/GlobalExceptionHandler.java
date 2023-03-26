package com.myblog.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
