package com.xingkong.lyn.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.UserInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by lyn on 2017/6/21.
 * 思路：
 * 1.读取当前登录用户名，获取在缓存中的sessionId队列
 * 2.判断队列的长度，大于最大登录限制的时候，按踢出规则
 *  将之前的sessionId中的session域中存入kickout：true，并更新队列缓存
 * 3.判断当前登录的session域中的kickout如果为true，
 * 想将其做退出登录处理，然后再重定向到踢出登录提示页面
 */
public class KickoutSessionControllerFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(KickoutSessionControllerFilter.class);

    private String kickoutUrl; //踢出后的地址
    private boolean kickoutAfter = false; //踢出之前登陆的/之后登陆的用户 默认踢出之前的用户
    private int maxSession = 2; //同一账号最大会话数 默认1

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro_redis_cache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登陆，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        String username = userInfo.getUsername();
        Serializable sessionId = session.getId();

        //读取缓存 没有就存入
        Deque<Serializable> deque = cache.get("shiro_redis_session:" + username);

        if(null == deque){
            deque = new ArrayDeque<>();
        }

        //如果队列里没有此sessionId，且用户没有被踢出; 放入队列
        if (!deque.contains(sessionId) && null == session.getAttribute("kickout")){
            //将sessionId存入队列
            deque.push(sessionId);
            //将用户的sessionId队列缓存
            cache.put("shiro_redis_session:" + username, deque);
        }

        //如果队列里的sessionId数超过最大会话数，开始踢人
        while (deque.size() > maxSession){
            Serializable kickoutSessionId = null;
            if (kickoutAfter){
                //true踢出后者
                kickoutSessionId = deque.removeFirst();
            } else {
                //false踢出前者
                kickoutSessionId = deque.removeLast();
            }
            //踢出后更新缓存队列
            cache.put(username, deque);

            try{
                //获取被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(null != kickoutSession){
                    //设置会话的kickout属性表示踢出
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e){
            }
        }

        //如果被提出了，直接推出，重定向到踢出后的网址
        if (null != (Boolean)session.getAttribute("kickout") && (Boolean)session.getAttribute("kickout") == true){
            //会话被提出了
            try{
                //退出登录
                subject.logout();
            } catch (Exception e){
            }
            saveRequest(request);

            AjaxResults ajaxResults = new AjaxResults();
            //判断是否Ajax请求
            if("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))){
                ajaxResults.setCode(1);
                ajaxResults.setMsg("您已经在其他地方登录，请重新登录！");
                //输出json串
                out(response, ajaxResults);
            }else {
                //重定向
                WebUtils.issueRedirect(request, response, kickoutUrl);
            }
            return false;
        }
        return true;
    }

    private void out(ServletResponse hresponse, AjaxResults ajaxResults)throws IOException{
        try{
            hresponse.setCharacterEncoding("UTF-8");
            PrintWriter out = hresponse.getWriter();
            out.println(JSON.toJSONString(ajaxResults));
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("输出json异常，可以忽视");
        }
    }

}