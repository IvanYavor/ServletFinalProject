package com.company.service;

import com.company.dao.DaoFactory;
import com.company.dao.MessageDao;
import com.company.dao.SpecialityDao;
import com.company.model.entity.Message;
import com.company.model.entity.Speciality;

import java.util.List;

public class MessageService {
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
        try(MessageDao dao = daoFactory.createMessageDao()) {
            return dao.findByUserId(userId);
        }
    }

}
