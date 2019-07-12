package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.lucene.LuceneHelper;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "search",defaultValue = "") String search) {
        //使用前先创建索引库
        createIndex();
        List<Product> productList = null;
        if (search == null || "".equals(search)) {
            productList = LuceneHelper.searchIndex();
        } else {
            productList = LuceneHelper.searchIndex(search);
        }
//        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll.do";
    }

    public void createIndex() {
        List<Product> productList = productService.findAll();
        LuceneHelper.createIndex(productList);
    }

}
