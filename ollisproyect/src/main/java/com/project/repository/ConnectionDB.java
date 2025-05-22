package com.project.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase de utilidad para gestionar la conexión a la base de datos. Proporciona
 * un método estático para obtener una conexión a la base de datos MySQL.
 */
public class ConnectionDB {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConnectionDB.class.getClassLoader().getResourceAsStream("com/project/config/config.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("No se encontró el archivo config.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar config.properties", e);
        }
    }

    /**
     * URL de la base de datos. Especifica la ubicación de la base de datos en
     * Railway MySQL.
     */
    private static final String DB_URL = props.getProperty("db.url");

    /**
     * Nombre de usuario para la conexión a la base de datos. Especifica el
     * usuario de la base de datos en Railway SQL
     */
    private static final String DB_USER = props.getProperty("db.user");

    /**
     * Contraseña para la conexión a la base de datos. Especifica la contraseña
     * de la base de datos en Railway SQL
     */
    private static final String DB_PASS = props.getProperty("db.pass");

    /**
     * Obtiene una conexión a la base de datos MySQL.
     *
     * @return Un objeto {@link Connection} que representa la conexión a la base
     * de datos.
     * @throws SQLException Si ocurre un error al intentar conectarse a la base
     * de datos.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
