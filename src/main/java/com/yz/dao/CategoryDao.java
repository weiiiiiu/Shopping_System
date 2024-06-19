package com.yz.dao;

import com.yz.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> findAll() throws SQLException; //查询所有分类
    int addCategory(Category category) throws SQLException; //添加分类

    int delCategory(String cid) throws SQLException; //删除分类

    Category findCategoryById(String cid) throws SQLException; //根据ID查询分类

    int updateCategory(Category category) throws SQLException; //更新分类

    int countCategory() throws SQLException; //计算分类数量



    List<Category> findPageCategoryList(int start, int count) throws SQLException; //分页查询分类
}