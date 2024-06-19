package com.yz.service.impl;

import com.yz.dao.CategoryDao;
import com.yz.dao.impl.CategoryDaoImpl;
import com.yz.entity.Category;
import com.yz.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        List<Category> list = null;
        try {
            list = categoryDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public String addCategory(Category category) {
        int count = 0;
        try {
            count = categoryDao.addCategory(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count > 0 ? "添加成功" : "添加失败";
    }

    @Override
    public boolean delCategory(String cid) {
        int count = 0;
        try {
            count = categoryDao.delCategory(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count > 0;
    }

    @Override
    public boolean updateCategory(Category category) {
        int count = 0;
        try {
            count = categoryDao.updateCategory(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count > 0;
    }

    @Override
    public int getMaxCid() {
        return 0;
    }
}