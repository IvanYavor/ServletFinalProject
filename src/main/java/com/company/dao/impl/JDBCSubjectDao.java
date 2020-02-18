package com.company.dao.impl;

import com.company.dao.SubjectDao;
import com.company.dao.mapper.SubjectMapper;
import com.company.entity.Subject;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCSubjectDao implements SubjectDao {
    private Connection connection;
    private Logger LOG = Logger.getLogger(JDBCSpecialityDao.class);
    public static final String SELECT_FROM_SUBJECT = "Select * from subject";
    public static final String SELECT_FROM_SUBJECT_WHERE_ID = "Select * from subject where id=?";
    public static final String INSERT_INTO_SUBJECT_ID_NAME_SPECIALITY_ID_VALUES_NULL = "insert into subject(id, name, speciality_id) values(NULL, ?, ?, ?)";

    public JDBCSubjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Subject entity) {
        String name = entity.getName();
        int specialityId = entity.getSpecialityId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SUBJECT_ID_NAME_SPECIALITY_ID_VALUES_NULL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, specialityId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
        }
    }

    @Override
    public Subject findById(int id) {


        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_SUBJECT_WHERE_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Subject s = null;
            if (rs.next()) {
                SubjectMapper sm = new SubjectMapper();
                s = sm.extractFromResultSet(rs);
            }

            return s;
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            return null;
        }
    }

    @Override
    public List<Subject> findAll() {

        Map<Integer, Subject> subjectHashMap = new HashMap<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_FROM_SUBJECT);

            SubjectMapper sm = new SubjectMapper();

            while (rs.next()) {
                Subject s = sm.extractFromResultSet(rs);
                subjectHashMap.put(s.getId(), s);
            }

            return new ArrayList<>(subjectHashMap.values());
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            return null;
        }
    }

    @Override
    public void update(Subject entity) {

    }

    @Override
    public void delete(int id) {

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
