package com.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Game;

public class GameRepository {

    public static List<Game> showListGames() {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM games";

        try (Connection conn = ConnectionDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getLong("id"));      // Asignar correctamente el ID
                game.setName(rs.getString("name"));
                game.setGenre(rs.getString("genre"));
                game.setExePath(rs.getString("exePath"));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static void addListGames(Game game) {
        String query = "INSERT INTO games (name, genre, exePath) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, game.getName());
            pstmt.setString(2, game.getGenre());
            pstmt.setString(3, game.getExePath());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    game.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteListGames(Game game) {
        if (game.getId() == null) {
            System.out.println("Error: Intentando eliminar un juego con ID nulo");
            return;
        }
        String query = "DELETE FROM games WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, game.getId());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Juego eliminado de la base de datos");
            } else {
                System.out.println("Error: no se encontró el juego en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
