package com.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.model.User;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserRepository {

    public boolean CreateUser(User user) {
        if (ValidateUserRegisterUserName(user.getUserName())) {
            System.out.println("El usuario ya existe en la base de datos");
            return false;
        }

        if (ValidateUserRegisterEmail(user.getEmail())) {
            System.out.println("El email ya existe en la base de datos");
            return false;
        }
        String query = "INSERT INTO Usuario (name, lastName, userName, password, email, birthday) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setObject(6, user.getBirthday());

            int rows = pstmt.executeUpdate();

            if (rows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    Long generatedId = generatedKeys.getLong(1);
                    user.setId(generatedId);
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean ValidateUserRegisterUserName(String userName) {
        String query = "SELECT 1 FROM Usuario WHERE userName = ? LIMIT 1";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userName);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ValidateUserRegisterEmail(String email) {
        String query = "SELECT 1 FROM Usuario WHERE email = ? LIMIT 1";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ValidateUserLogIn(TextField userName, PasswordField password) {
        String query = "SELECT * FROM Usuario WHERE userName = ? AND password = ?";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userName.getText());
            pstmt.setString(2, password.getText());
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByUsername(String username) {

        String query = "SELECT * FROM Usuario WHERE userName = ?";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getTimestamp("birthday").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra el usuario
    }

}
