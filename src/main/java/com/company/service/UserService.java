package com.company.service;

import com.company.dao.DaoFactory;
import com.company.dao.UserDao;
import com.company.model.entity.User;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public boolean saveUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            List<User> users = dao.findAll();
            for (User u : users) {
                if (u.getLogin().equals(user.getLogin())) {
                    return false;
                }
            }

            dao.create(user);
            return true;
        }
    }

    public User getUserById(Integer id) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findById(id);
        }
    }

    public void updateUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.update(user);
        }
    }

    public void deleteUser(int id) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.delete(id);
        }
    }

    public List<User> getAllStudents() {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByRole(User.ROLE.USER);
        }
    }

    public boolean checkIfStudentIdExists(int id) {
        try(UserDao dao = daoFactory.createUserDao()) {
            List<User> students = dao.findByRole(User.ROLE.USER);
            for(User s : students) {
                if(s.getId() == id) {
                    return true;
                }
            }
            return  false;
        }
    }
}
