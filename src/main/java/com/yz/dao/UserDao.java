package com.yz.dao;

import com.yz.entity.User;

import java.sql.SQLException;

public interface UserDao {
    public int addUser(User user) throws SQLException;
}
