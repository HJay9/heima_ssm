package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Jay
 * @date 2019/7/8
 */
@Component
@Aspect
public class SysLogger {

    @Autowired
    private ISysLogService sysLogService;

    @Around("execution(* com.itheima.ssm.controller.*.*(..))")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object returnValue = null;
        try {

            // 记录开始访问的时间点
            Date startTime = new Date();

            // 获取方法执行所需的参数
            Object[] args = pjp.getArgs();
            // 调用业务层方法（切入点方法）
            returnValue = pjp.proceed(args);


            //得到 springSecurity 的容器
            SecurityContext context = SecurityContextHolder.getContext();
            //获取容器里的当前登录用户对象
            User user = (User) context.getAuthentication().getPrincipal();
            //获取操作的用户名
            String username = user.getUsername();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //IP地址
            String ipAddr = request.getRemoteAddr();
            //url
            String url = request.getRequestURI();
            //类名
            String className = pjp.getTarget().getClass().getSimpleName();
            //方法名
            String methodName = pjp.getSignature().getName();

            // 计算执行时长
            long time = System.currentTimeMillis() - startTime.getTime();

            //将日志封装进 SysLog 对象
            SysLog sysLog = new SysLog();
            //访问时间
            sysLog.setVisitTime(startTime);
            //执行时长
            sysLog.setExecutionTime(time);
            //访问方法：类+方法名
            sysLog.setMethod(className + "=>" + methodName);
            //url
            sysLog.setUrl(url);
            //ip
            sysLog.setIp(ipAddr);
            //访问用户名
            sysLog.setUsername(username);

            //日志信息封装完毕，插入记录到数据库
            sysLogService.saveLog(sysLog);

            return returnValue;
        } catch (Throwable t) {
            throw new RuntimeException();
        }
    }

}
