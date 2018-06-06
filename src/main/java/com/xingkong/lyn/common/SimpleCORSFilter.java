package com.xingkong.lyn.common;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lyn on 2017/6/27.
 */
@Component
public class SimpleCORSFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest)request;
        Cookie[] cookies = req.getCookies();
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        if(null != cookies){
            resp.setHeader("Set-Cookie", cookies.toString());
        }
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {

    }
}