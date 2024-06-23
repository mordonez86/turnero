package turneromedico.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_BASE_URL = "jdbc:h2:tcp://localhost/{DIR}/turnero";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection connect() {
        Connection c = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("H2 Driver class not found. Ensure the H2 driver JAR is in the classpath.");
            e.printStackTrace();
            System.exit(0);
        }
        try {
            String url = DB_BASE_URL.replace("{DIR}", getDirectorioBase());
            // System.out.println("Connecting to: " + url);
            c = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            c.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("Failed to establish a database connection.");
            e.printStackTrace();
            System.exit(0);
        }

        return c;
    }

    private static String getDirectorioBase() {
        File currDir = new File("h2/base_de_datos/");
        // System.out.println("Directory: " + currDir.getAbsolutePath());
        return currDir.getAbsolutePath();
    }

    public static String obtenerUbicacionBase() {
        String url = DB_BASE_URL.replace("{DIR}", getDirectorioBase());
        return url;
    }
}
