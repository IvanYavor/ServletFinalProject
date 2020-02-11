package com.company.dao.mapper;

import com.company.model.entity.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MessageMapper implements ObjectMapper<Message> {

    @Override
    public Message extractFromResultSet(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt("id"));
        message.setUserId(resultSet.getInt("user_id"));
        message.setText(resultSet.getString("text"));
        message.setDate(resultSet.getDate("date_message"));

        return message;
    }

    @Override
    public Message makeUnique(Map<Integer, Message> cache, Message message) {
        cache.putIfAbsent(message.getId(), message);
        return cache.get(message.getId());
    }
}
