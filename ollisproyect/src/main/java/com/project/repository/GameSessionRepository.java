package com.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GameSessionRepository {

    public void saveGameSession(Long gameId, Long userId, LocalDateTime startTime, LocalDateTime endTime, long totalSeconds) {
        String insertQuery = "INSERT INTO GameSession (game_id, user_id, start_time, end_time, total_time) VALUES (?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE Usuario SET balance = balance + ? WHERE id = ?";

        int coinsMin = 10;
        int earnedCoins = (int) (totalSeconds / 60) * coinsMin;

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmtSession = conn.prepareStatement(insertQuery); PreparedStatement stmtBalance = conn.prepareStatement(updateQuery)) {
            stmtSession.setLong(1, gameId);
            stmtSession.setLong(2, userId);
            stmtSession.setTimestamp(3, Timestamp.valueOf(startTime));
            stmtSession.setTimestamp(4, Timestamp.valueOf(endTime));
            stmtSession.setLong(5, totalSeconds);
            stmtSession.executeUpdate();

            stmtBalance.setInt(1, earnedCoins);
            stmtBalance.setLong(2, userId);
            stmtBalance.executeUpdate();

            System.out.println("Se añadieron " + earnedCoins + " monedas al usuario " + userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getTotalPlayedTime(Long gameId, Long userId) {
        String query = "SELECT SUM(total_time) AS total FROM GameSession WHERE game_id = ? AND user_id = ?";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, gameId);
            pstmt.setLong(2, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getLong("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public int getUserBalance(int userId) {
        String query = "SELECT balance FROM Users WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
