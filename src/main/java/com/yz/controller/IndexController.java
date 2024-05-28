package com.yz.controller;

import com.yz.entity.Product;
import com.yz.service.ProductService;
import com.yz.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> newProductList = productService.findNewProductList();
        List<Product> hotProductList = productService.findHotProductList();
        request.setAttribute("newProductList",newProductList);
        request.setAttribute("hotProductList",hotProductList);
        //请求跳转到首页，request作用域页面到页面之间传递数据
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
