package com.example.reggie.common;

/**
 * @ClassName CustomException
 * @Description 自定义业务异常类
 * @Author chenxu
 * @Date 2022/6/4 20:13
 **/
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
