package com.company.dao.mapper;

import com.company.entity.Subject;
import com.company.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements ObjectMapper<Subject> {
    @Override
    public Subject extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        Subject s = new Subject();
        s.setId(resultSet.getInt("id"));
        s.setName(resultSet.getString("name"));
        s.setSpecialityId(resultSet.getInt("speciality_id"));
        return s;
    }
}
