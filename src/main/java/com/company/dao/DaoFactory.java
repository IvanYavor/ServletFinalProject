package com.company.dao;

import com.company.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract SpecialityDao createSpecialityDao();

    public abstract MessageDao createMessageDao();

    public abstract SubjectDao createSubjectDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }

        return daoFactory;
    }
}
