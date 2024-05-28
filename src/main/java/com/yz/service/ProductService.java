package com.yz.service;

import com.yz.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findNewProductList() ;//查询最新商品
    List<Product> findHotProductList() ;//查询热门商品
    List<Product> findAllProductList() ;//查询所有商品

    boolean addProduct(Product product);//添加商品
    boolean delProduct(String pid);
    Product findProductById(String pid);
    boolean updateProduct(Product product);


}
