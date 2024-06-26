package com.yz.dao.impl;

import com.yz.dao.ProductDao;
import com.yz.entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public List<Product> findNewProductList() throws SQLException {
        String sql = "SELECT * FROM product ORDER BY pdate DESC LIMIT 0,9";
        List<Product> list=qr.query(sql,new BeanListHandler<Product>(Product.class));
        return list;
    }

    @Override
    public List<Product> findHotProductList() throws SQLException {
        String sql = "SELECT * FROM product WHERE is_hot=1 LIMIT 0,9";
        List<Product> list=qr.query(sql,new BeanListHandler<Product>(Product.class));
        return list;
    }

    @Override
    public List<Product> findAllProductList() throws SQLException {
        String sql = "select * from product order by pdate desc";
        List<Product> list=qr.query(sql,new BeanListHandler<Product>(Product.class));
        return list;
    }

    @Override
    public int addProduct(Product product) throws SQLException {
        String sql = "insert into product(pid,pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,pflag,cid)" +
                "values(?,?,?,?,?,?,?,?,?,?)";
        int count = qr.update(sql,
                product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),
                product.getPimage(),product.getPdate(),product.getIs_hot(),
                product.getPdesc(),product.getPflag(),product.getCategory().getCid());
        return count;
    }

    @Override
    public int delProduct(String pid) throws SQLException {
        String sql = "delete from product where pid=?";
        return qr.update(sql,pid);
    }

    @Override
    public Product findProductById(String pid) throws SQLException {
        String sql = "select * from product where pid=?";
        return qr.query(sql,new BeanHandler<Product>(Product.class),pid);
    }

    @Override
    public int updateProduct(Product product) throws SQLException {
        String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,is_hot=?,cid=? where pid=?";
        int count = qr.update(sql,product.getPname(),product.getMarket_price(),product.getShop_price(),
                product.getPimage(),product.getIs_hot(),product.getCategory().getCid(),product.getPid());
        return count;
    }

    @Override
    public int countProduct(String cid) throws SQLException {
        String sql = "select count(cid) from product where cid=?";
        //int count = 0;
        //count= qr.query(sql,new BeanHandler<Integer>(Integer.class),cid);
        Long row = (Long) qr.query(sql,new ScalarHandler(),cid);
        return Math.toIntExact(row);//long类型转int类型
    }

    @Override
    public List<Product> finfPageProductList(int start, int count, String cid) throws SQLException {
        String sql="select * from product where cid=?limit ?,?";
        List<Product> list=null;
        list=qr.query(sql,new BeanListHandler<Product>(Product.class),cid,start,count);
        return list;
    }
}
