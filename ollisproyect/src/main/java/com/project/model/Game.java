package com.project.model;

public class Game {

    // Atributos
    private Long id;
    private String name;
    private String genre;
    private String exePath;

    // Constructor por defecto
    public Game() {

    }

    // Constructor parametrizado
    public Game(String name, String genre, String exePath) {
        this.name = name;
        this.genre = genre;
        this.exePath = exePath;
    }

    public Game(Long id, String name, String genre, String exePath) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.exePath = exePath;
    }

    // GETTERS / SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExePath() {
        return exePath;
    }

    public void setExePath(String exePath) {
        this.exePath = exePath;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
