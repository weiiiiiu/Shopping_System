package com.yz.service.impl;

import com.yz.dao.ProductDao;
import com.yz.dao.impl.ProductDaoImpl;
import com.yz.entity.Page;
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

    @Override
    public Product findProductById(String pid) {
        Product product=null;
        try {
            product=productDao.findProductById(pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        int count=0;
        try {
            count=productDao.updateProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count>0;
    }

    @Override
    public Page<Product> findPage(int pageNo, String cid) {
        /*分页；
        *当前页：pageNo 1
        * 当前页大小：pageSize 18
        * 当前页记录数：totalCount 101 SELECT count(pid) FROM product WHERE cid=1;
        * 当前页总记录数：pageCount 6
        * */
        Page<Product> page=new Page<>();
        page.setPageSize(18);
        page.setPageNo(pageNo);
        int totalCount=0;
        try {
            totalCount=productDao.countProduct(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        page.setTotalCount(totalCount);
        int pageCount=totalCount%page.getPageSize()==0?totalCount/page.getPageSize():totalCount/page.getPageSize()+1;
        page.setPageCount(pageCount);
        List<Product> list=null;
        try {
            list=productDao.finfPageProductList((pageNo-1)*page.getPageSize(),page.getPageSize(),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        page.setList(list);
        return page;
    }
}
