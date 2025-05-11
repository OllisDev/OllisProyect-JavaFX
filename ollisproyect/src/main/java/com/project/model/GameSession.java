package com.project.model;

import java.time.LocalDateTime;

/**
 * Clase que representa una sesión de juego en el sistema. Contiene información
 * sobre el juego jugado, el usuario que lo jugó, el tiempo de inicio, el tiempo
 * de finalización y la duración total de la sesión.
 */
public class GameSession {

    // Atributos
    private long id;
    private Long gameId;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long totalTime;

    // Constructor por defecto
    public GameSession() {

    }

    /**
     * Constructor parametrizado. Crea una instancia de la clase
     * {@link GameSession} con los valores especificados.
     *
     * @param id El identificador único de la sesión de juego.
     * @param gameId El identificador único del juego asociado.
     * @param userId El identificador único del usuario que jugó el juego.
     * @param startTime La fecha y hora de inicio de la sesión.
     * @param endTime La fecha y hora de finalización de la sesión.
     * @param totalTime El tiempo total jugado en segundos.
     */
    public GameSession(long id, Long gameId, Long userId, LocalDateTime startTime, LocalDateTime endTime, long totalTime) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
    }

    // GETTERS / SETTERS
    /**
     * Obtiene el identificador único de la sesión de juego.
     *
     * @return El identificador de la sesión.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la sesión de juego.
     *
     * @param id El identificador de la sesión.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador único del juego asociado a la sesión.
     *
     * @return El identificador del juego.
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * Establece el identificador único del juego asociado a la sesión.
     *
     * @param gameId El identificador del juego.
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * Obtiene el identificador único del usuario que jugó el juego.
     *
     * @return El identificador del usuario.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Establece el identificador único del usuario que jugó el juego.
     *
     * @param userId El identificador del usuario.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Obtiene la fecha y hora de inicio de la sesión de juego.
     *
     * @return La fecha y hora de inicio.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Establece la fecha y hora de inicio de la sesión de juego.
     *
     * @param startTime La fecha y hora de inicio.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Obtiene la fecha y hora de finalización de la sesión de juego.
     *
     * @return La fecha y hora de finalización.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Establece la fecha y hora de finalización de la sesión de juego.
     *
     * @param endTime La fecha y hora de finalización.
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Obtiene el tiempo total jugado en segundos.
     *
     * @return El tiempo total jugado.
     */
    public long getTotalTime() {
        return totalTime;
    }

    /**
     * Establece el tiempo total jugado en segundos.
     *
     * @param totalTime El tiempo total jugado.
     */
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Obtiene el tiempo total jugado en formato de horas, minutos y segundos.
     *
     * @return Una cadena con el tiempo total jugado en formato "HH:mm:ss".
     */
    public String getFormattedTime() {
        long hours = totalTime / 3600;
        long minutes = (totalTime % 3600) / 60;
        long seconds = totalTime % 60;
        return String.format("%02d:&02d:%02d", hours, minutes, seconds);
    }
}
