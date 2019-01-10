package com.neuedu.controller;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;
    @RequestMapping(value = "/product/{pageNo}/{pageSize}")
    public ServerResponse<Product> find(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
        return productService.findBypage(pageNo,pageSize);
    }
    @RequestMapping(value = "/product")
    public ServerResponse<Product> find(){
        return productService.findAll();
    }
}
