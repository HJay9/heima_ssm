package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Orders;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询所有订单
     * @return
     */
    List<Orders> findAll();

    /**
     * 根据 id 查询订单
     * @param id
     * @return
     */
    Orders findById(String id);

}
