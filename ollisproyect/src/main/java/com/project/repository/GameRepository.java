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
