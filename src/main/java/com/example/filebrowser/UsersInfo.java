package com.example.filebrowser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UsersInfo {

    public static void addUser(User user){
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try(Connection conn = DataBase.getConnection(); PreparedStatement state = conn.prepareStatement(sql)) {
            state.setString(1, user.getUsername());
            state.setString(2, user.getPassword());
            state.setString(3, user.getEmail());
            state.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка " + e);
        }

    }

    public static User getUser(String username){
        String sql = "SELECT username, password, email FROM users WHERE username = ?";


        try(Connection conn = DataBase.getConnection(); PreparedStatement state = conn.prepareStatement(sql)) {
            state.setString(1, username);
            ResultSet resultSet = state.executeQuery();
            if(resultSet.next()){
                return new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("Ошибка " + e);
        }
        return null;
    }

    public static boolean validateUser(String login, String password){
        User user = getUser(login);
        return user != null && user.getPassword().equals(password);
    }
}
