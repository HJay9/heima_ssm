package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 用于获取访问 IP
     */
    @Autowired
    private HttpServletRequest request;
    /**
     * 将日志数据存入数据库
     */
    @Autowired
    private ISysLogService sysLogService;
    /**
     * 访问时间
     */
    private Date visitTime;
    /**
     * 访问的类名称
     */
    private Class clazz;
    /**
     * 访问的方法名称
     */
    private Method method;

    @Around("execution(* com.itheima.ssm.controller.*.*(..))")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object returnValue = null;
        try {
            // 获取方法执行所需的参数
            Object[] args = pjp.getArgs();

            // 前置通知
            doBefore(pjp);

            // 调用业务层方法（切入点方法）
            returnValue = pjp.proceed(args);

            // 后置通知
            doAfter(pjp);

            return returnValue;
        } catch (Throwable t) {
            throw new RuntimeException();
        }
    }

    /**
     * 前置方法，用于记录当前时间、获取访问的类和方法
     * @param jp
     */
    public void doBefore(JoinPoint jp) {
        //记录当前时间
        visitTime = new Date();
        //获取访问的类
        clazz = jp.getTarget().getClass();
        //获取访问的方法
        MethodSignature signature = (MethodSignature) jp.getSignature();
        method = signature.getMethod();

    }

    /**
     * 后置方法
     */
    public void doAfter(JoinPoint jp) {
        //计算执行时长
        Long time = System.currentTimeMillis() - visitTime.getTime();
        //获取访问 url
        String url = "";
        if (clazz != null && method != null && clazz != SysLogger.class) {
            //获取类上的注解对象
            RequestMapping classRequestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classRequestMapping != null) {
                //获取类上的RequestMapping注解的值
                String[] clazzValue = classRequestMapping.value();
                //获取方法上的注解对象
                RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                if (methodRequestMapping != null) {
                    //获取方法上的RequestMapping注解的值
                    String[] methodValue = methodRequestMapping.value();
                    url = clazzValue[0] + methodValue[0];
                }
            }
        }

        //得到 springSecurity 的容器
        SecurityContext context = SecurityContextHolder.getContext();
        //获取容器里的当前登录用户对象
        User user = (User) context.getAuthentication().getPrincipal();


        //将日志封装进 SysLog 对象
        SysLog sysLog = new SysLog();
        //访问时间
        sysLog.setVisitTime(visitTime);
        //执行时长
        sysLog.setExecutionTime(time);
        //访问方法：类+方法名
        sysLog.setMethod(clazz.getSimpleName() + "=>" + method.getName());
        //url
        sysLog.setUrl(url);
        //ip
        sysLog.setIp(request.getRemoteAddr());
        //访问用户名
        sysLog.setUsername(user.getUsername());

        //日志信息封装完毕，插入记录到数据库
        sysLogService.saveLog(sysLog);

    }

}
