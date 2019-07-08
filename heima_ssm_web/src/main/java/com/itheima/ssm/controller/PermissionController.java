package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IPermissionService;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/permission")
@Controller
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        List<Permission> permissionList = permissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    public String saveUser(Permission permission) {
        permissionService.savePermission(permission);
        return "redirect:findAll";
    }

    /**
     * 权限详情
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        Permission permission = permissionService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permission", permission);
        modelAndView.setViewName("permission-show");
        return modelAndView;
    }

    @RequestMapping("/deletePermission")
    public String deletePermission(String id) {
        permissionService.deletePermission(id);
        return "redirect:findAll";
    }

}
