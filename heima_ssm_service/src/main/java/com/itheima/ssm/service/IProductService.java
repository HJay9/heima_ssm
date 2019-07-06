package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * @author Jay
 */
public interface IProductService {

    /**
     * 查询所有产品
     * @return
     */
    List<Product> findAll(String search);

    /**
     * 新增一条产品记录
     * @param product
     */
    void save(Product product);

}
