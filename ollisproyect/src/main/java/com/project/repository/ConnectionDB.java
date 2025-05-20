package com.project.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase de utilidad para gestionar la conexión a la base de datos. Proporciona
 * un método estático para obtener una conexión a la base de datos MySQL.
 */
public class ConnectionDB {

    /**
     * URL de la base de datos. Especifica la ubicación de la base de datos
     * MySQL.
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users";

    private static final String DB_NAME = "Users";

    /**
     * Nombre de usuario para la conexión a la base de datos.
     */
    private static final String DB_USER = "root";

    /**
     * Contraseña para la conexión a la base de datos.
     */
    private static final String DB_PASS = "root";

    /**
     * Obtiene una conexión a la base de datos MySQL.
     *
     * @return Un objeto {@link Connection} que representa la conexión a la base
     * de datos.
     * @throws SQLException Si ocurre un error al intentar conectarse a la base
     * de datos.
     */
    public static Connection getConnection() throws SQLException {
        createDatabaseIfNeeded();
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private static void createDatabaseIfNeeded() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE '" + DB_NAME + "'");
            if (!rs.next()) {
                System.out.println("Base de datos no existe. Creando base de datos...");
                runSQLScript(conn, "./scripts/users.sql");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runSQLScript(Connection conn, String path) throws Exception {
        InputStream is = ConnectionDB.class.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("No se encontró el archivo: " + path);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sql = new StringBuilder();
        String line;

        try (Statement stmt = conn.createStatement()) {
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    stmt.execute(sql.toString());
                    sql.setLength(0);
                }
            }
        }
    }
}
