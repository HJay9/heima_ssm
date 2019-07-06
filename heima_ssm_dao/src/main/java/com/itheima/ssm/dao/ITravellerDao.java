package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;

import java.util.List;

public interface ITravellerDao {

    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    Traveller findById(String id);

}
