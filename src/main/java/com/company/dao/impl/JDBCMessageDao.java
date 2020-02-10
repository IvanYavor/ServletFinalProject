package com.company.dao.impl;

import com.company.dao.MessageDao;
import com.company.dao.mapper.MessageMapper;
import com.company.model.entity.Message;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCMessageDao implements MessageDao {
    private Connection connection;

    public JDBCMessageDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Message message) {
        int id = message.getId();
        int userId = message.getUserId();
        String text = message.getText();
        Date date = message.getDate();

        final String query = "insert into messages(id, user_id, text, date_message)" +
                " values(NULL, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, text);
            preparedStatement.setDate(3,  date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Message findByUserId(int userId) {
        final String query = "select * from messages where user_id=?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            Message message = null;
            if(rs.next()) {
                MessageMapper mapper = new MessageMapper();
                message = mapper.extractFromResultSet(rs);
            }

            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Message findById(int id) {
        final String query = "select * from messages where id=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Message message = null;
            if (rs.next()) {
                MessageMapper mapper = new MessageMapper();
                message = mapper.extractFromResultSet(rs);
            }

            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> findAll() {
        Map<Integer, Message> messages = new HashMap<>();
        final String query = "select * from messages";

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            MessageMapper mapper = new MessageMapper();
            while (rs.next()) {
                Message message = mapper.extractFromResultSet(rs);
                message = mapper.makeUnique(messages, message);
            }

            return new ArrayList<>(messages.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Message message) {
        final String query = "update messages set user_id=?, text=?, date_message=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, message.getUserId());
            preparedStatement.setString(2, message.getText());
            preparedStatement.setDate(3, (java.sql.Date) message.getDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        final String query = "delete from messages where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
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
