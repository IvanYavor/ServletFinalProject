package com.company.dao.impl;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;


public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    ResourceBundle dbConfig = ResourceBundle.getBundle("db");
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(dbConfig.getString("db.url"));
                    ds.setUsername(dbConfig.getString("db.username"));
                    ds.setPassword(dbConfig.getString("db.password"));
                    ds.setMinIdle(Integer.parseInt(dbConfig.getString("db.minIdle")));
                    ds.setMaxIdle(Integer.parseInt(dbConfig.getString("db.maxIdle")));
                    ds.setMaxOpenPreparedStatements(Integer.parseInt(dbConfig.getString("db.maOpenPreparedStatements")));
                    dataSource = ds;

                }
            }
        }

        return dataSource;
    }


}
