package com.yz.dao.impl;
import com.yz.dao.UserDao;
import com.yz.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import util.DataSourceUtils;

import java.sql.SQLException;


public class UserDaoImpl implements UserDao{
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public int addUser(User user) throws SQLException {
        String sql = "insert into user(uid,username,password) values(?,?,?)";
        int row=qr.update(sql,user.getUid(), user.getUsername(),user.getPassword());
        return row;
    }
}
