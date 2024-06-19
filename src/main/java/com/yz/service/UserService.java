package com.yz.service;

import com.yz.entity.User;
import java.sql.SQLException;

public interface UserService{
    public boolean register(User user) throws SQLException;
    boolean validateUser(String username, String password) throws SQLException;
    User login(String username, String password) throws SQLException;
}