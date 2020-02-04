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

        try(Statement st = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullName);
            preparedStatement.setString(4, role);

            int i = preparedStatement.executeUpdate();

            if(i != 0) {
                System.out.println("You added user");
            } else {
                System.out.println("You didn't add user");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        final String query = " select * from users";

        try(Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while(rs.next()) {
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
    public void update(int id) {

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
