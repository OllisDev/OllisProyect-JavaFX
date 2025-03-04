package com.project.model;

import java.time.LocalDateTime;

public class User {

    // Atributos
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private LocalDateTime birthday;

    // Constructor por defecto
    public User() {

    }

    // Constructor parametrizado
    public User(String name, String lastName, String userName, String password, String email, LocalDateTime birthday) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;

    }

    // GETTERS / SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

}
