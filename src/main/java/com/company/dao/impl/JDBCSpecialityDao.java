package com.company.dao.impl;

import com.company.dao.SpecialityDao;
import com.company.dao.mapper.SpecialityMapper;
import com.company.entity.Speciality;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCSpecialityDao implements SpecialityDao {
    public static final String SELECT_FROM_SPECIALITY_WHERE_ID = "Select * from speciality where id=?";
    public static final String INSERT_INTO_SPECIALITY = "insert into speciality(id_speciality, name, description, faculty) values(NULL, ?, ?, ?)";
    public static final String UPDATE_SPECIALITY = "UPDATE speciality set speciality.name=?, description=?, faculty=? where id=?";
    public static final String SELECT_FROM_SPECIALITY = "Select * from speciality";
    public static final String DELETE_FROM_SPECIALITY_WHERE_ID = "delete from speciality where id = ?";
    public static final String SELECT_FROM_SPECIALITY_WHERE_SPECIALITY_NAME = "Select * from speciality where speciality.name=?";
    private Connection connection;

    private Logger LOG = Logger.getLogger(JDBCSpecialityDao.class);

    public JDBCSpecialityDao(Connection connection) {
        this.connection = connection;
    }


    public Speciality findByName(String name) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_SPECIALITY_WHERE_SPECIALITY_NAME)) {
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();
            Speciality s = null;
            if (rs.next()) {
                SpecialityMapper specialityMapper = new SpecialityMapper();
                s = specialityMapper.extractFromResultSet(rs);
            }

            return s;
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            return null;
        }
    }

    @Override
    public void create(Speciality speciality) {
        String name = speciality.getName();
        String description = speciality.getDescription();
        String faculty = speciality.getFaculty();

        final String query = INSERT_INTO_SPECIALITY;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, faculty);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error("SQLException ", e);
        }
    }

    @Override
    public Speciality findById(int id) {
        final String query = SELECT_FROM_SPECIALITY_WHERE_ID;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Speciality speciality = null;
            if (rs.next()) {
                SpecialityMapper specialityMapper = new SpecialityMapper();
                speciality = specialityMapper.extractFromResultSet(rs);
            }

            return speciality;
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            return null;
        }
    }

    @Override
    public List<Speciality> findAll() {
        Map<Integer, Speciality> specialities = new HashMap<>();

        final String query = SELECT_FROM_SPECIALITY;

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            SpecialityMapper specialityMapper = new SpecialityMapper();

            while (rs.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(rs);
//                speciality = specialityMapper.makeUnique(specialities, speciality);
                specialities.put(speciality.getId(), speciality);
            }

            return new ArrayList<>(specialities.values());
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            return null;
        }
    }

    @Override
    public void update(Speciality speciality) {
        final String query = UPDATE_SPECIALITY;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, speciality.getName());
            preparedStatement.setString(2, speciality.getDescription());
            preparedStatement.setString(3, speciality.getFaculty());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error("SQLException ", e);
        }
    }

    @Override
    public void delete(int id) {
        final String query = DELETE_FROM_SPECIALITY_WHERE_ID;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
