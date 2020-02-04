package com.company.service;

import com.company.dao.UserDao;
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

    public boolean saveUser(User user) {
        try(UserDao dao = daoFactory.createUserDao()) {
            List<User> users = dao.findAll();
            for(User u : users) {
                if(u.getLogin().equals(user.getLogin())) {
                    return false;
                }
            }

            dao.create(user);
            return true;
        }
    }
}
