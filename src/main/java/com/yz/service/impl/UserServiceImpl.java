package com.yz.service.impl;

import com.yz.dao.UserDao;
import com.yz.dao.impl.UserDaoImpl;
import com.yz.entity.User;
import com.yz.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) throws SQLException {
        int count = userDao.addUser(user);
        return count > 0;
    }

    @Override
    public boolean validateUser(String username, String password) throws SQLException {
        User user = userDao.select(username, password);
        return user != null;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        return userDao.select(username, password);
    }
}