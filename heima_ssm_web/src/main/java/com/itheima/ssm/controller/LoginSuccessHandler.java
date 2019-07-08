package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author Jay
 * @date 2019/7/8
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ISysLogService sysLogService;

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //将日志封装进 SysLog 对象
        SysLog sysLog = new SysLog();
        //访问时间
        sysLog.setVisitTime(new Date());
        //执行时长
        sysLog.setExecutionTime(null);
        //访问方法：类+方法名
        sysLog.setMethod("user" + "=>" + "login");
        //url
        sysLog.setUrl("/user/login");
        //ip
        sysLog.setIp(request.getRemoteAddr());

        //获取容器里的当前登录用户对象
        User user = (User) authentication.getPrincipal();
        //访问用户名
        sysLog.setUsername(user.getUsername());

        //日志信息封装完毕，插入记录到数据库
        sysLogService.saveLog(sysLog);

        HttpSession session = request.getSession();

        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
