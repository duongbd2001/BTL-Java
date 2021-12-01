package com.example.springintellij.dao;

import com.example.springintellij.model.UserModel;

interface UserDAO {
    public UserModel login(String username);
}
