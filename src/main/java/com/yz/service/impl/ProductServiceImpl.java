package com.yz.service.impl;

import com.yz.dao.ProductDao;
import com.yz.dao.impl.ProductDaoImpl;
import com.yz.entity.Product;
import com.yz.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao=new ProductDaoImpl();
    @Override
    public List<Product> findNewProductList() {
        List<Product> list = null;
        try {
            list=productDao.findNewProductList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Product> findHotProductList() {
        List<Product> list = null;
        try {
            list=productDao.findHotProductList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Product> findAllProductList() {
        List<Product> list = null;
        try {
            list=productDao.findAllProductList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean addProduct(Product product) {
        int count=0;
        try {
            count=productDao.addProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count>0;
    }

    @Override
    public boolean delProduct(String pid) {
        int count=0;
        try {
            count=productDao.delProduct(pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count>0;
    }
}
