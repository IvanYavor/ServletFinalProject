package com.company.dao;

import com.company.model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    List<User> findByRole(User.ROLE role);
}
