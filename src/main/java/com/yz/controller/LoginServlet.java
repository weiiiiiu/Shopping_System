package com.yz.controller;

import com.yz.entity.User;
import com.yz.service.UserService;
import com.yz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 获取复选框数据
        String remember = request.getParameter("remember");

        // 调用service查询
        User user = null;
        try {
            user = service.login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 判断
        if(user != null){
            // 登录成功，跳转到查询所有的BrandServlet

            // 判断用户是否勾选记住我
            if("1".equals(remember)){
                // 勾选了，发送Cookie

                // 创建Cookie对象
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);
                // 设置Cookie的存活时间
                c_username.setMaxAge( 60 * 60 * 24 * 7);
                c_password.setMaxAge( 60 * 60 * 24 * 7);
                // 设置Cookie的路径
                c_username.setPath("/");
                c_password.setPath("/");
                // 发送
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            // 将登陆成功后的user对象和用户名，存储到session，并将用户重定向到指定的页面。
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setAttribute("username", username); // 存储用户名
            session.setAttribute("login_status", "success");

            System.out.println("Username stored in session: " + username); // 调试代码

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/admin/home.jsp");
        }else {
            // 登录失败,

            // 存储错误信息到session
            HttpSession session = request.getSession();
            session.setAttribute("login_status", "failure");

            // 跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}