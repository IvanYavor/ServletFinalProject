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
        final String query = "select * from users where id=?";
        String str_id = String.valueOf(id);

        try(Statement st = connection.createStatement()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, str_id);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                UserMapper userMapper = new UserMapper();
                user = userMapper.extractFromResultSet(rs);
            }


//            UserMapper userMapper = new UserMapper();
//            User user = userMapper.extractFromResultSet(rs);

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        //        return null;
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
    public void update(User user) {
        final String query = "UPDATE users set login=? where id=?";

        try (Statement st = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, String.valueOf(user.getId()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        final String query = "delete from users where id = ?";

        try(Statement st = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
