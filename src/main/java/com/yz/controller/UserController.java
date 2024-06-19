package com.yz.controller;


import com.yz.entity.User;
import com.yz.service.impl.UserServiceImpl;

import com.yz.service.UserService;
import util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println(name +"  "+pwd);
        User user = new User();//实例化对象
        user.setUid(CommonUtil.generateUUID());
        user.setUsername(name);//给对象属性赋值
        user.setPassword(pwd);
        //将对象数据传递给业务了逻辑层，业务逻辑将数据给数据访问层，数据访问层将数据提交给数据库
        //数据库将数据反馈给数据访问层，数据访问层将数据反馈给业务逻辑层，业务逻辑层将数据反馈给控制层
        //控制层将数据反馈给
        boolean flag = false;
        try {
            flag = userService.register(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().print(flag);
    }
}
