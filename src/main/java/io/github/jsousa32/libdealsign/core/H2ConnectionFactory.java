package io.github.jsousa32.libdealsign.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionFactory {

    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:h2:mem:lib-dealsign;" +
                                      "DB_CLOSE_DELAY=-1;" +
                                      "INIT=CREATE TABLE IF NOT EXISTS tb_authentication (" +
                                      "  id UUID PRIMARY KEY, " +
                                      "  bearer VARCHAR(1500), " +
                                      "  expiresAt TIMESTAMP" +
                                      ");";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver H2 não encontrado.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
