package com.example.filebrowser;

import java.util.HashMap;
import java.util.Map;

public class UsersInfo {
    private static final Map<String, User> users = new HashMap<>();

    public static void addUser(User user){
        users.put(user.getUsername(), user);
    }

    public static User getUser(String login){
        return users.get(login);
    }

    public static boolean validateUser(String login, String password){
        User user = getUser(login);
        return user != null && user.getPassword().equals(password);
    }

}
