package com.yz.dao;

import com.yz.entity.User;

import java.sql.SQLException;

public interface UserDao {
    public int addUser(User user) throws SQLException;
    User select(String username, String password) throws SQLException;
    User selectByUsername(String username) throws SQLException;
    void add(User user) throws SQLException;
}