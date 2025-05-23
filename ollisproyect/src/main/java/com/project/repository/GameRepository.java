package com.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Game;

/**
 * Clase que gestiona las operaciones relacionadas con los juegos en la base de
 * datos. Proporciona métodos para listar, agregar y eliminar juegos.
 */
public class GameRepository {

    /**
     * Obtiene la lista de todos los juegos vinculados y almacenados en la base
     * de datos por cada usuario.
     *
     * @param userId el parametro userId vincula todos los juegos almacenados
     * por cada usuario.
     *
     * @return Una lista de objetos {@link Game} que representan los juegos
     * almacenados.
     */
    public static List<Game> showListGames(long userId) {
        List<Game> games = new ArrayList<>();
        String query = "SELECT g.id, g.name, g.genre, g.exePath "
                + "FROM games g "
                + "JOIN GameSession gs ON g.id = gs.game_id "
                + "WHERE gs.user_id = ?";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game();
                    game.setId(rs.getLong("id"));
                    game.setName(rs.getString("name"));
                    game.setGenre(rs.getString("genre"));
                    game.setExePath(rs.getString("exePath"));
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    /**
     * Agrega un nuevo juego a la base de datos.
     *
     * @param game El objeto {@link Game} que contiene los datos del juego a
     * agregar.
     *
     * @param userId el parametro userId vincula el juego registado de cada
     * usuario
     */
    public static void addListGames(Game game, long userId) {
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

            String linkQuery = "INSERT INTO GameSession (game_id, user_id, start_time, end_time, total_time) VALUES (?, ?, NOW(), NOW(), 0)";
            try (PreparedStatement linkStmt = conn.prepareStatement(linkQuery)) {
                linkStmt.setLong(1, game.getId());
                linkStmt.setLong(2, userId);
                linkStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un juego de la base de datos.
     *
     * @param game El objeto {@link Game} que representa el juego a eliminar.
     * Debe contener un ID válido.
     */
    public static void deleteListGames(long userId, long gameId) {
        String deleteRelation = "DELETE FROM Usuario_Games WHERE user_id = ? AND game_id = ?";
        String checkRelation = "SELECT COUNT(*) FROM Usuario_Games WHERE game_id = ?";
        String deleteSessions = "DELETE FROM GameSession WHERE game_id = ?";
        String deleteGame = "DELETE FROM games WHERE id = ?";

        try (Connection conn = ConnectionDB.getConnection()) {
            // 1. Eliminar la relación usuario-juego
            try (PreparedStatement pstmt = conn.prepareStatement(deleteRelation)) {
                pstmt.setLong(1, userId);
                pstmt.setLong(2, gameId);
                pstmt.executeUpdate();
            }

            // 2. Verificar si el juego sigue asignado a algún usuario
            try (PreparedStatement pstmt = conn.prepareStatement(checkRelation)) {
                pstmt.setLong(1, gameId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        // 3. Eliminar sesiones asociadas al juego
                        try (PreparedStatement delSessions = conn.prepareStatement(deleteSessions)) {
                            delSessions.setLong(1, gameId);
                            delSessions.executeUpdate();
                        }
                        // 4. Si no está asignado a nadie, eliminar el juego
                        try (PreparedStatement delGame = conn.prepareStatement(deleteGame)) {
                            delGame.setLong(1, gameId);
                            delGame.executeUpdate();
                            System.out.println("Juego eliminado completamente de la base de datos.");
                        }
                    } else {
                        System.out.println("Juego eliminado solo de la lista del usuario.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void assignGameToUser(long userId, long gameId) {
        String query = "INSERT INTO Usuario_Games (user_id, game_id) VALUES (?, ?)";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, userId);
            pstmt.setLong(2, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
