package com.yz.dao.impl;

import com.yz.dao.UserDao;
import com.yz.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.DataSourceUtils;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public int addUser(User user) throws SQLException {
        String sql = "insert into user(uid,username,password) values(?,?,?)";
        int row = qr.update(sql, user.getUid(), user.getUsername(), user.getPassword());
        return row;
    }

    @Override
    public User select(String username, String password) throws SQLException {
        String sql = "select * from user where username = ? and password = ? and state = 1";
        User user = qr.query(sql, new BeanHandler<>(User.class), username, password);
        return user;
    }

    @Override
    public User selectByUsername(String username) throws SQLException {
        String sql = "select * from user where username = ?";
        User user = qr.query(sql, new BeanHandler<>(User.class), username);
        return user;
    }

    @Override
    public void add(User user) throws SQLException {
        String sql = "insert into user values(null,?,?)";
        qr.update(sql, user.getUsername(), user.getPassword());
    }
}