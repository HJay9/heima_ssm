package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("/findAll")
//    @RolesAllowed("Role_ADMIN")
//    @PermitAll
    @DenyAll
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer pageNum, @RequestParam(value = "size", defaultValue = "5") Integer pageSize) {
        List<Orders> ordersList = ordersService.findAll(pageNum, pageSize);
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findDetailByid(String id) {
        Orders orders = ordersService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }

}
