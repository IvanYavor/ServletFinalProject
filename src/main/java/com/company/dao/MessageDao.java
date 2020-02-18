package com.company.dao;

import com.company.entity.Message;

public interface MessageDao extends GenericDao<Message> {
    Message findByUserId(int userId);
}
