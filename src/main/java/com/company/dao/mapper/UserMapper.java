package com.company.dao.mapper;

import com.company.model.entity.Speciality;
import com.company.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("users.login"));
        user.setPassword(resultSet.getString("users.password"));
        user.setFullName(resultSet.getString("users.full_name"));
        user.setRole(User.ROLE.valueOf(resultSet.getString("users.role")));
        user.setSpecialityId(resultSet.getInt("users.id_speciality"));
        Speciality speciality = new Speciality();
        speciality.setName(resultSet.getString("name"));
        speciality.setFaculty(resultSet.getString("faculty"));
        speciality.setDescription(resultSet.getString("description"));
        user.setSpeciality(speciality);
        user.setTestScore(resultSet.getInt("users.test_score"));
        user.setAccepted(Boolean.parseBoolean(resultSet.getString("users.accepted")));
        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
