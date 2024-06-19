package com.yz.service;

import com.yz.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    boolean delCategory(String cid);

    String addCategory(Category category);

    boolean updateCategory(Category category);

    int getMaxCid();
}
