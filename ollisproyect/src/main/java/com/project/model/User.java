package com.project.model;

import java.time.LocalDateTime;

/**
 * Clase que representa un usuario en el sistema. Contiene información básica
 * sobre el usuario, como su nombre, apellidos, nombre de usuario, contraseña,
 * correo electrónico, fecha de nacimiento, identificador único y saldo de
 * monedas.
 */
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

    /**
     * Constructor parametrizado. Crea una instancia de la clase {@link User}
     * con los valores especificados.
     *
     * @param name El nombre del usuario.
     * @param lastName Los apellidos del usuario.
     * @param userName El nombre de usuario único.
     * @param password La contraseña del usuario.
     * @param email El correo electrónico del usuario.
     * @param birthday La fecha de nacimiento del usuario.
     */
    public User(String name, String lastName, String userName, String password, String email, LocalDateTime birthday) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    /**
     * Constructor parametrizado con identificador. Crea una instancia de la
     * clase {@link User} con los valores especificados.
     *
     * @param id El identificador único del usuario.
     * @param name El nombre del usuario.
     * @param lastName Los apellidos del usuario.
     * @param userName El nombre de usuario único.
     * @param password La contraseña del usuario.
     * @param email El correo electrónico del usuario.
     * @param birthday La fecha de nacimiento del usuario.
     */
    public User(Long id, String name, String lastName, String userName, String password, String email, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    /**
     * Constructor parametrizado con saldo de monedas. Crea una instancia de la
     * clase {@link User} con los valores especificados.
     *
     * @param id El identificador único del usuario.
     * @param name El nombre del usuario.
     * @param lastName Los apellidos del usuario.
     * @param userName El nombre de usuario único.
     * @param password La contraseña del usuario.
     * @param email El correo electrónico del usuario.
     * @param birthday La fecha de nacimiento del usuario.
     * @param balance El saldo de monedas del usuario.
     */
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

    /**
     * Constructor parametrizado para pruebas. Crea una instancia de la clase
     * {@link User} con los valores básicos especificados.
     *
     * @param userName El nombre de usuario único.
     * @param password La contraseña del usuario.
     * @param email El correo electrónico del usuario.
     */
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    // GETTERS / SETTERS
    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name El nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return Los apellidos del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece los apellidos del usuario.
     *
     * @param lastName Los apellidos del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el nombre de usuario único.
     *
     * @return El nombre de usuario.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Establece el nombre de usuario único.
     *
     * @param userName El nombre de usuario.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     *
     * @return La fecha de nacimiento del usuario.
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     *
     * @param birthday La fecha de nacimiento del usuario.
     */
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id El identificador del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el saldo de monedas del usuario.
     *
     * @return El saldo de monedas del usuario.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Establece el saldo de monedas del usuario.
     *
     * @param balance El saldo de monedas del usuario.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Devuelve una representación en forma de cadena de la clase {@link User}.
     *
     * @return Una cadena que representa al usuario.
     */
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
