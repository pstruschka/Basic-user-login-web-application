package io.muic.ooc.webapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnection {

    private final String jdbcDriverStr;
    private final String jdbcURL;

    private Connection connection;
    private Connection connection2;
    private Statement statement;
    private Statement statement2;
    private ResultSet resultSet;
    private ResultSet resultSet2;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
    String sql;

    public MySQLConnection(String jdbcDriverStr, String jdbcURL){
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }

    public boolean addUser(String username, String hashedPass){
        //"INSERT INTO `ooc`.`user` (`username`, `password`) VALUES ('user', 'pass');"
        return true;
    }
}
