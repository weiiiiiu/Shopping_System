package com.yz.dao;

import com.yz.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> findAll() throws SQLException;
}
