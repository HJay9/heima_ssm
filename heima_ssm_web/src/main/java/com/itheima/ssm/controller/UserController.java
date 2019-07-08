package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import com.itheima.ssm.service.IUserService;
import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        List<UserInfo> userInfoList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userInfoList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/save")
    public String saveUser(UserInfo user) {
        userService.saveUser(user);
        return "redirect:findAll";
    }

    /**
     * 用户详情
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        UserInfo userInfo = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 查询可选角色
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findOptionalRole(String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        List<Role> optionalRole = roleService.findOptionalRole(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userInfo);
        modelAndView.addObject("roleList", optionalRole);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     * 添加角色
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId, String[] ids) {
        if (userId == null || "".equals(userId) || ids == null || ids.length == 0) {
            return "redirect:findAll";
        }
        roleService.updateRoleByUser(userId, ids);
        return "redirect:findAll";
    }

}
