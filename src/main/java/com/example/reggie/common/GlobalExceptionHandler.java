package com.example.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Author chenxu
 * @Date 2022/5/28 18:10
 **/
@Slf4j
@ResponseBody           //将处理结果封装成JSON返回
@ControllerAdvice(annotations = {RestController.class, Controller.class})       //扫描哪些类里的异常
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     * 全局拦截异常的关键在两个注解：@ControllerAdvice, @ExceptionHandler
     * chenxu
     * 2022/5/28 18:35
     **/

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error("异常信息：{}",ex.getMessage());

        //根据报错处理账号已存在问题
        if(ex.getMessage().contains("Duplicate entry")){
            String[] account = ex.getMessage().split(" ");
            String msg = "用户账号" + account[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("失败");
    }
}

