package com.company.dao.mapper;

import com.company.model.entity.Speciality;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SpecialityMapper implements ObjectMapper<Speciality> {
    @Override
    public Speciality extractFromResultSet(ResultSet resultSet) throws SQLException {

        Speciality speciality = new Speciality();
        speciality.setId(resultSet.getInt("id_speciality"));
        speciality.setName(resultSet.getString("name"));
        speciality.setDescription(resultSet.getString("description"));
        speciality.setFaculty(resultSet.getString("faculty"));
        return speciality;
    }

    @Override
    public Speciality makeUnique(Map<Integer, Speciality> cache, Speciality speciality) {
        cache.putIfAbsent(speciality.getId(), speciality);
        return cache.get(speciality.getId());
    }
}
