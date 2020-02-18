package com.company.service;

import com.company.dao.DaoFactory;
import com.company.dao.SubjectDao;
import com.company.entity.Subject;

import java.util.List;

public class SubjectService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Subject> getAllSubjects() {
        try (SubjectDao dao = daoFactory.createSubjectDao()) {
            return dao.findAll();
        }
    }

    public void createSubject(Subject s) {
        try (SubjectDao dao = daoFactory.createSubjectDao()) {
            dao.create(s);
        }
    }


    public Subject getSubjectById(int id) {
        try (SubjectDao dao = daoFactory.createSubjectDao()) {
            return dao.findById(id);
        }
    }
}
