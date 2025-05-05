package com.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
