package com.entity;

import java.sql.ResultSet;

/**
 * Created by Aymen Ben Othmen on 16/07/16.
 */

public interface IEntity {

    boolean createTable();
    boolean update();
    boolean insert();
    boolean flush(String statement);
    void fillClass();
    ResultSet executeQuery(String query);
}
