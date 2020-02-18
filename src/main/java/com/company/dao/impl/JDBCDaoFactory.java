package com.company.dao.impl;

import com.company.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public SpecialityDao createSpecialityDao() {
        return new JDBCSpecialityDao(getConnection());
    }

    @Override
    public MessageDao createMessageDao() {
        return new JDBCMessageDao(getConnection());
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new JDBCSubjectDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
