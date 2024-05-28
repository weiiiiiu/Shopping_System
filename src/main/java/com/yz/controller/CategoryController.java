package com.yz.controller;

import com.alibaba.fastjson.JSON;
import com.yz.dao.CategoryDao;
import com.yz.entity.Category;
import com.yz.service.CategoryService;
import com.yz.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    CategoryService categoryService=new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        if("list".equals(type)){
            list(request,response);
        }

    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list=categoryService.findAll();
        String json= JSON.toJSONString(list);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
