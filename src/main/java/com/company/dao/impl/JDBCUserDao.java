package com.company.dao.impl;

import com.company.dao.UserDao;
import com.company.dao.mapper.UserMapper;
import com.company.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String role = user.getRole().toString();

        final String query = "insert into users(id, login, password, full_name, role)" +
                " values(NULL, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullName);
            preparedStatement.setString(4, role);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        final String query = "select * from users  left join speciality on users.id_speciality=speciality.id_speciality where id=?";
        String str_id = String.valueOf(id);

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, str_id);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                UserMapper userMapper = new UserMapper();
                user = userMapper.extractFromResultSet(rs);
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findByRole(User.ROLE role) {
        Map<Integer, User> users = new HashMap<>();
        final String query = " select * from users left join speciality on users.id_speciality=speciality.id_speciality where role=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, String.valueOf(role));
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
            }

            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        final String query = " select * from users left join speciality on users.id_speciality=speciality.id_speciality";

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
            }

            return new ArrayList<>(users.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void update(User user) {
        final String query = "UPDATE users set login=?, password=?, full_name=?, role=?, id_speciality=?, test_score=?, accepted=? where id=?";

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
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        final String query = "delete from users where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
