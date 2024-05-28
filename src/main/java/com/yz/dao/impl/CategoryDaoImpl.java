package com.yz.dao.impl;

import com.yz.dao.CategoryDao;
import com.yz.entity.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public List<Category> findAll() throws SQLException {
        String sql = "select * from category";
        List<Category> list=qr.query(sql,new BeanListHandler<Category>(Category.class));
        return list;
    }
}
