package com.example.reggie.common;

/**
 * @ClassName BaseContext
 * @Description 基于ThreadLocal封装的工具类，用于获取和保存当前登录用户的id
 * 每一个前端发起的request请求，都会对应一个线程处理
 * @Author chenxu
 * @Date 2022/6/3 18:07
 **/
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setId(Long id){
        threadLocal.set(id);
    }

    public static Long getId(){
        return threadLocal.get();
    }
}
