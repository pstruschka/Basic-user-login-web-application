package io.muic.ooc.webapp;

import java.util.HashMap;
import java.util.HashSet;

public class User {
    private static HashMap<Integer, User> userMap;
    private static HashSet<String> usernames = new HashSet<>();
    private String userName;
    private String firstName;
    private String lastName;

    public User(String userName, String firstName, String lastName) {
        usernames.add(userName);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() { return userName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public static HashMap<Integer, User> getUserMap() { return userMap; }

    public static void setUserMap(HashMap<Integer, User> userSet) { User.userMap = userSet; }

    public static User userFromID(Integer id) {
        if (id!=null) return userMap.getOrDefault(id, null);
        return null;
    }

    public static Boolean isNewUserName(String userName) {
        return !usernames.contains(userName);
    }

    public static Boolean userExists(Integer id) {
        return userMap.containsKey(id);
    }
}
