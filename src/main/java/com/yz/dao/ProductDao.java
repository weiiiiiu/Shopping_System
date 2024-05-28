package com.yz.dao;

import com.yz.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findNewProductList() throws SQLException;//查询最新商品
    List<Product> findHotProductList() throws SQLException;//查询热门商品
    List<Product> findAllProductList() throws SQLException;//查询所有商品
    int addProduct(Product product) throws SQLException;

    int  delProduct(String pid) throws SQLException;

    Product findProductById(String pid) throws SQLException;

    int updateProduct(Product product) throws SQLException;
}
