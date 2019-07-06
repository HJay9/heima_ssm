package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDao productDao;


    /**
     * 查询所有产品
     *
     * @return
     */
    @Override
    public List<Product> findAll(String search) {
        return productDao.findAll(search);
    }

    /**
     * 新增一条产品记录
     *
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
