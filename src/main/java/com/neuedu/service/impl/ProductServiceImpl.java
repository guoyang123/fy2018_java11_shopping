package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Override
    public ServerResponse<Product> findBypage(int pageNo,int pageSize) {

        PageHelper.startPage(pageNo,pageSize);
        List<Product> productList= productMapper.selectAll();// limit (pageno-1 )*pageSize,pageSize

        PageInfo pageInfo=new PageInfo(productList);
       return  ServerResponse.createServerResponseBySuccess(null,pageInfo);
    }
    @Override
    public ServerResponse<Product> findAll() {

        PageHelper.startPage(1,10);
        List<Product> productList= productMapper.selectAll();// limit (pageno-1 )*pageSize,pageSize
        PageInfo pageInfo=new PageInfo(productList);
        return  ServerResponse.createServerResponseBySuccess(null,pageInfo);
    }
}
