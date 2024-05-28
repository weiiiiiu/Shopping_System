package com.yz.service.impl;


import com.yz.dao.UserDao;
import com.yz.dao.impl.UserDaoImpl;
import com.yz.entity.User;
import com.yz.service.UserService;

import java.sql.SQLException;


public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public boolean register(User user)  {
        int count = 0;
        try {
            count=userDao.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count>0;
    }
}
