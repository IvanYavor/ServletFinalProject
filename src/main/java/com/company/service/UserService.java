package com.company.service;

import com.company.UserDao;
import com.company.dao.DaoFactory;
import com.company.model.entity.User;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        try(UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }
}
