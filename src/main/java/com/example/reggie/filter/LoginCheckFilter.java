package com.example.reggie.filter;


import com.alibaba.fastjson.JSON;
import com.example.reggie.common.BaseContext;
import com.example.reggie.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        /**
         * 1.获取到用户的请求
         * 2.判断请求是否需要登录处理，如果不需要则直接放行
         * 3.需要登录的则判断登录状态
         */

        //从线程工具类获取用户id
        Long empId = (Long) request.getSession().getAttribute("employee");
        BaseContext.setId(empId);

        //获取本次请求URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);

        String[] urls = new String[]{
                //定义不处理直接放行的请求
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        //判断是否直接放行
        boolean check = check(urls, requestURI);
        if(check){
            //如果匹配上，直接放行
            log.info("本次请求不需要处理：{}",requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //判断登录状态，如果已经登录则直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id:{}",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");
        //如果未登录，返回未登录结果，通过输出流方式向客户端页面响应数据
        //这里的NOTLOGIN需要参照前端的接收数据
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
        return;

//        log.info("拦截到请求：{}",request.getRequestURI());
//        filterChain.doFilter(request,response);
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requstURI
     * @return
     */
    public boolean check(String[] urls, String requstURI){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url, requstURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
