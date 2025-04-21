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
    private Long id;
    private int balance;

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

    // Constructor parametrizado (con ID)
    public User(Long id, String name, String lastName, String userName, String password, String email, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    // Constructor parametrizado (con las monedas)
    public User(Long id, String name, String lastName, String userName, String password, String email, LocalDateTime birthday, int balance) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.balance = balance;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("name=").append(name);
        sb.append(", lastName=").append(lastName);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", birthday=").append(birthday);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

}
