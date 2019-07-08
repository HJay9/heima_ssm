package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;

import java.util.List;

public interface ITravellerDao {

    /**
     * 根据 orderId 查询
     * @param orderId
     * @return
     */
    Traveller findByOrderId(String orderId);

}
