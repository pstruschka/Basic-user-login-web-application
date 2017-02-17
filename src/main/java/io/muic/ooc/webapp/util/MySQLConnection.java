package io.muic.ooc.webapp.util;

import io.muic.ooc.webapp.User;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class MySQLConnection {

    enum UserTableColumns {
        id, username, password, firstName, lastName;
    }

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
    private String sql;

    public MySQLConnection(String jdbcDriverStr, String jdbcURL){
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }

    private boolean getConnection() {
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            //.getConnection("jdbc:mysql://10.8.0.1/ooc", "p", "threat-bull-middle");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private boolean closeConnection() {

        try {
            if (!connection.isClosed()) {
                connection.close();
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Integer userExists(String username) {
        Integer id = null;
        if(!StringUtils.isEmpty(username) && getConnection()) {
            sql = "SELECT id FROM user WHERE username = ?;";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("in");
                    id = resultSet.getInt(UserTableColumns.id.toString());
                } else System.out.println("out");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
        return id;
    }


    public String getUserPassword(String username) {
        String password = null;
        if(!StringUtils.isEmpty(username) && getConnection()) {
            sql = "SELECT * FROM user WHERE username = ?;";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.first()){
                    password = resultSet.getString(UserTableColumns.password.toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
        return password;
    }

    public void getUsers() {
        HashMap<Integer, User> userHashMap;
        if (getConnection()) {
            sql = "SELECT * FROM user;";

            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                userHashMap = new HashMap<>();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(UserTableColumns.id.toString());
                    String userName = resultSet.getString(UserTableColumns.username.toString());
                    String firstName = resultSet.getString(UserTableColumns.firstName.toString());
                    String lastName = resultSet.getString(UserTableColumns.lastName.toString());
                    System.out.println(id + " " + userName);
                    User user = new User(userName, firstName, lastName);
                    userHashMap.put(id, user);
                }
                User.setUserMap(userHashMap);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    public void updateUser(int id, String username, String firstName, String lastName) {
        if (getConnection()) {
            sql = "UPDATE `ooc`.`user` SET `username` = ?, `firstname`= ?, `lastname`= ? WHERE `id`= ? ;";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setInt(4, id);
                int i = preparedStatement.executeUpdate();
                System.out.println(i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    public void removeUser(int id) {
        if (getConnection()) {
            sql = "DELETE FROM `ooc`.`user` WHERE `id`= ?;";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    public boolean addUser(String username, String password, String firstName, String lastName) {
        int results = 0;
        if (getConnection()) {
            sql = "INSERT INTO `ooc`.`user` (`username`, `password`, `firstname`, `lastname`) VALUES (?, ?, ?, ?);";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, firstName);
                preparedStatement.setString(4, lastName);
                results = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results > 0;
    }
}
