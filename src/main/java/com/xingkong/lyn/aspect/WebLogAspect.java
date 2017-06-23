package com.xingkong.lyn.aspect;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.model.manage.SysLog;
import com.xingkong.lyn.service.ISysLog;
import com.xingkong.lyn.service.IUserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by lyn on 2017/4/30.
 */
@Aspect
@Component
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Resource
    private IUserInfo userInfoService;
    @Resource
    private ISysLog sysLogService;

    @Pointcut("execution(public * com.xingkong.lyn.controller.*.*(..))")
    private void webLogPoint(){}

    @Pointcut(value = "@annotation(adminLog)", argNames = "adminLog")
    private void manageLogPoint(AdminLog adminLog){}

    @Before("webLogPoint()")
    @Order(2)
    public void doBefore(JoinPoint joinPoint)throws Throwable{
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("manageLogPoint(adminLog)")
    @Order(1)
    public void manageLog(AdminLog adminLog)throws Throwable{
//        Subject currentUser = SecurityUtils.getSubject();
//        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
//        SysLog sysLog = new SysLog();
//        sysLog.setOperator(userInfo.getUsername());
//        sysLog.setOperatorId(userInfo.getId());
//        sysLog.setCreateTime(new Date());
//        sysLog.setContent(adminLog.value());
//        sysLogService.addSysLog(sysLog);
    }

}
