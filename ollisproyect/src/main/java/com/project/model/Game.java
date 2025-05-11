package com.project.model;

/**
 * Clase que representa un juego en el sistema. Contiene información básica
 * sobre el juego, como su nombre, género y ruta ejecutable.
 */
public class Game {

    // Atributos
    private Long id;
    private String name;
    private String genre;
    private String exePath;

    // Constructor por defecto
    public Game() {

    }

    /**
     * Constructor parametrizado. Crea una instancia de la clase {@link Game}
     * con los valores especificados.
     *
     * @param name El nombre del juego.
     * @param genre El género del juego.
     * @param exePath La ruta del archivo ejecutable del juego.
     */
    public Game(String name, String genre, String exePath) {
        this.name = name;
        this.genre = genre;
        this.exePath = exePath;
    }

    /**
     * Constructor parametrizado con identificador. Crea una instancia de la
     * clase {@link Game} con los valores especificados.
     *
     * @param id El identificador único del juego.
     * @param name El nombre del juego.
     * @param genre El género del juego.
     * @param exePath La ruta del archivo ejecutable del juego.
     */
    public Game(Long id, String name, String genre, String exePath) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.exePath = exePath;
    }

    // GETTERS / SETTERS
    /**
     * Obtiene el identificador único del juego.
     *
     * @return El identificador del juego.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del juego.
     *
     * @param id El identificador del juego.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del juego.
     *
     * @return El nombre del juego.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del juego.
     *
     * @param name El nombre del juego.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la ruta del archivo ejecutable del juego.
     *
     * @return La ruta del archivo ejecutable.
     */
    public String getExePath() {
        return exePath;
    }

    /**
     * Establece la ruta del archivo ejecutable del juego.
     *
     * @param exePath La ruta del archivo ejecutable.
     */
    public void setExePath(String exePath) {
        this.exePath = exePath;
    }

    /**
     * Obtiene el género del juego.
     *
     * @return El género del juego.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Establece el género del juego.
     *
     * @param genre El género del juego.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

}
