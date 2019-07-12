package com.itheima.ssm.dao;


import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * @author Jay
 */
public interface IProductDao {

    /**
     * 查询所有产品
     * @return
     */
    List<Product> findAll();

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    Product findById(String id);

    /**
     * 新增一条产品记录
     * @param product
     */
    void save(Product product);
}
