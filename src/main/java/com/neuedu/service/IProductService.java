package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;

public interface IProductService {

    ServerResponse<Product> findAll(int pageNo,int pageSize);

}
