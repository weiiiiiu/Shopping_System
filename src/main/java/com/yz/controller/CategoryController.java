package com.yz.controller;

import com.alibaba.fastjson.JSON;
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
import java.util.UUID;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    CategoryService categoryService=new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        if("list".equals(type)){
            list(request,response);
        } else if("add".equals(type)){
            addCategory(request,response);
        } else if("del".equals(type)){
            delCategory(request,response);
        } else if("update".equals(type)){
            updateCategory(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list=categoryService.findAll();
        String json= JSON.toJSONString(list);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        String cname = request.getParameter("cname");
        Category category = new Category();
        category.setCname(cname);
        // 获取当前cid的最大值，并加一作为新的cid
        int newCid = categoryService.getMaxCid() + 1;
        category.setCid(String.valueOf(newCid));
        String result = categoryService.addCategory(category);
        response.setContentType("text/html;charset=utf8");
        response.getWriter().print(result);
    }

    private void delCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        boolean flag = categoryService.delCategory(cid);
        response.getWriter().print(flag);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        Category category = new Category();
        category.setCid(cid);
        category.setCname(cname);
        boolean flag = categoryService.updateCategory(category);

        response.getWriter().print(flag);
    }
}