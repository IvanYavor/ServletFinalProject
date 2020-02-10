package com.company.model.entity;

import java.sql.Date;

public class Message {
    private int id;
    private int userId;
    private String text;
    private Date date;

    public Message(int id, int userId, String text, Date date) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.date = date;
    }

    public Message(int userId, String text, Date date) {
        this.userId = userId;
        this.text = text;
        this.date = date;
    }


    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
