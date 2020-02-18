package com.company.dao.impl;

import com.company.dao.UserDao;
import com.company.dao.mapper.UserMapper;
import com.company.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUserDao implements UserDao {
    public static final String DELETE_FROM_USERS_WHERE_ID = "delete from users where id = ?";
    public static final String UPDATE_USERS = "UPDATE users set login=?, password=?, full_name=?, role=?, id_speciality=?, test_score=?, accepted=? where id=?";
    public static final String SELECT_FROM_USERS_LEFT_JOIN_SPECIALITY = "select * from users left join speciality on users.id_speciality=speciality.id_speciality";
    public static final String SELECT_FROM_USERS_LEFT_JOIN_SPECIALITY_ON_USERS = "select * from users  left join speciality on users.id_speciality=speciality.id_speciality where id=?";
    public static final String WHERE_ROLE = "where role=?";
    public static final String INSERT_INTO_USERS_ID_LOGIN_PASSWORD_FULL_NAME_ROLE = "insert into users(id, login, password, full_name, role) values(NULL, ?, ?, ?, ?)";
    private Connection connection;
    private Logger LOG = Logger.getLogger(JDBCUserDao.class);

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String role = user.getRole().toString();

        final String query = INSERT_INTO_USERS_ID_LOGIN_PASSWORD_FULL_NAME_ROLE;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullName);
            preparedStatement.setString(4, role);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            LOG.error("SQLException ", e);
        }
    }

    @Override
    public User findById(int id) {
        final String query = SELECT_FROM_USERS_LEFT_JOIN_SPECIALITY_ON_USERS;
        String str_id = String.valueOf(id);

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            ps.setString(1, str_id);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                UserMapper userMapper = new UserMapper();
                user = userMapper.extractFromResultSet(rs);
            }

            connection.commit();
            connection.setAutoCommit(true);
            return user;
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error("SQLException ", ex);
            }
        }
        return null;
    }

    @Override
    public List<User> findByRole(User.ROLE role) {
        Map<Integer, User> users = new HashMap<>();
        final String query = SELECT_FROM_USERS_LEFT_JOIN_SPECIALITY + " " + WHERE_ROLE;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, String.valueOf(role));
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
//                user = userMapper.makeUnique(users, user);
                users.put(user.getId(), user);
            }

            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            return null;
        }

    }

    @Override
    public List<User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        final String query = SELECT_FROM_USERS_LEFT_JOIN_SPECIALITY;

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
//                user = userMapper.makeUnique(users, user);
                users.put(user.getId(), user);
            }

            return new ArrayList<>(users.values());

        } catch (SQLException e) {
            LOG.error("SQLException ", e);
            return null;
        }
    }


    @Override
    public void update(User user) {
        final String query = UPDATE_USERS;

        try (Statement st = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, String.valueOf(user.getRole()));
            preparedStatement.setInt(5, user.getSpecialityId());
            preparedStatement.setInt(6, user.getTestScore());
            preparedStatement.setString(7, String.valueOf(user.isAccepted()));
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error("SQLException ", e);
        }
    }


    @Override
    public void delete(int id) {
        final String query = DELETE_FROM_USERS_WHERE_ID;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(id));
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
