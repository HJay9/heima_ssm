package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    /**
     * 查询所有订单
     * @return
     */
    List<Orders> findAll(Integer pageNum, Integer pageSize);

    /**
     * 根据 id 查询订单
     * @param id
     * @return
     */
    Orders findById(String id);

}
