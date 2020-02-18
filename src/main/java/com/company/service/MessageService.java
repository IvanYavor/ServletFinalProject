package com.company.service;

import com.company.dao.DaoFactory;
import com.company.dao.MessageDao;
import com.company.entity.Message;
import org.apache.log4j.Logger;

import java.util.List;

public class MessageService {
    private static final Logger LOG = Logger.getLogger(MessageService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Message> getAllMessages() {
        try (MessageDao dao = daoFactory.createMessageDao()) {
            return dao.findAll();
        }
    }

    public void createMessage(Message message) {
        try (MessageDao dao = daoFactory.createMessageDao()) {
            dao.create(message);
        }
    }

    public Message getByUserId(int userId) {
        try (MessageDao dao = daoFactory.createMessageDao()) {
            return dao.findByUserId(userId);
        }
    }

}
