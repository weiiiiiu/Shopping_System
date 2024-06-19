package com.yz.dao.impl;

import com.yz.dao.CategoryDao;
import com.yz.entity.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public List<Category> findAll() throws SQLException {
        String sql = "select * from category";
        return qr.query(sql, new BeanListHandler<Category>(Category.class));
    }

    @Override
    public int addCategory(Category category) throws SQLException {
        // 插入新的记录，不指定cid，让数据库自动生成
        String insertSql = "INSERT INTO category(cname) VALUES(?)";
        return qr.update(insertSql, category.getCname());
    }

    @Override
    public int delCategory(String cid) throws SQLException {
        String sql = "delete from category where cid=?";
        return qr.update(sql, cid);
    }

    @Override
    public Category findCategoryById(String cid) throws SQLException {
        String sql = "select * from category where cid=?";
        return qr.query(sql, new BeanHandler<Category>(Category.class), cid);
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        String sql = "update category set cname=? where cid=?";
        return qr.update(sql, category.getCname(), category.getCid());
    }

    @Override
    public int countCategory() throws SQLException {
        String sql = "select count(cid) from category";
        Long row = (Long) qr.query(sql, new ScalarHandler());
        return Math.toIntExact(row);
    }




    @Override
    public List<Category> findPageCategoryList(int start, int count) throws SQLException {
        String sql="select * from category limit ?,?";
        return qr.query(sql, new BeanListHandler<Category>(Category.class), start, count);
    }
}