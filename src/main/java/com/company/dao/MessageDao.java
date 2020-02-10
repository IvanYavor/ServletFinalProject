package com.company.dao;

import com.company.model.entity.Message;

public interface MessageDao extends GenericDao<Message> {
    Message findByUserId(int userId);
}
