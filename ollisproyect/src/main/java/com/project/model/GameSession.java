package com.project.model;

import java.time.LocalDateTime;

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

    // Constructor parametrizado
    public GameSession(long id, Long gameId, Long userId, LocalDateTime startTime, LocalDateTime endTime, long totalTime) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
    }

    // GETTERS / SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getFormattedTime() {
        long hours = totalTime / 3600;
        long minutes = (totalTime % 3600) / 60;
        long seconds = totalTime % 60;
        return String.format("%02d:&02d:%02d", hours, minutes, seconds);
    }
}
