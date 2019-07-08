package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        List<Role> roleList = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 添加用户
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String saveUser(Role role) {
        roleService.saveRole(role);
        return "redirect:findAll";
    }

    /**
     * 用户详情
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        Role role = roleService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        modelAndView.setViewName("role-show");
        return modelAndView;
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(String id) {
        roleService.deleteRole(id);
        return "redirect:findAll";
    }

    /**
     * 查询可选权限
     * @param id
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id) {
        Role role = new Role();
        role.setId(id);
        List<Permission> optionalPermission = roleService.findOptionalPermission(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionList", optionalPermission);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /**
     * 添加权限
     * @return
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId, String[] ids) {
        if (roleId == null || "".equals(roleId) || ids == null || ids.length == 0) {
            return "redirect:findAll";
        }
        roleService.updatePermissionByRole(roleId, ids);
        return "redirect:findAll";
    }

}
