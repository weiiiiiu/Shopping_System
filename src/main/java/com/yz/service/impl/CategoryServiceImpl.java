package com.yz.service.impl;

import com.yz.dao.CategoryDao;
import com.yz.dao.impl.CategoryDaoImpl;
import com.yz.entity.Category;
import com.yz.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> list=null;
        try {
            list = categoryDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
