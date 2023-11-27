package com.neopragma.carrental;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.file.Path;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseIT {

    @Test
    public void connectionTest() throws Exception {
        String urlString = "";
        Connection conn = null;
        try {
            Properties props = new Properties();
            String pathname = "/carrental.properties";
            InputStream is = this.getClass().getResourceAsStream(pathname);
            assertNotNull(is, "InputStream is null for " + pathname);

            props.load(is);
            String databaseFilename = props.getProperty("database.filename");
            assertNotNull(databaseFilename, "database.filename from props is null");

            if (!databaseFilename.startsWith("/")) {
                databaseFilename = "/" + databaseFilename;
            }
            assertNotNull(this.getClass()
                            .getResource(databaseFilename),
                    "Looking for " + databaseFilename);

            Path pathToDatabase = Path.of(this.getClass().getResource(databaseFilename).getPath());
            assertNotNull(pathToDatabase, "Database file " + databaseFilename + " was not found.");

            String databaseConnectionString = props.getProperty("database.connection.string");
            urlString = MessageFormat.format(databaseConnectionString, pathToDatabase.toAbsolutePath());
            conn = DriverManager.getConnection(urlString);
            assertTrue(conn.isValid(10), "Database connection is not valid");

            String selectFromVehicles = "select vehicle_id, vin from vehicles";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectFromVehicles);
            int vehicleId = rs.getInt("vehicle_id");
            assertEquals(1, vehicleId);
            String vin = rs.getString("vin");
            assertEquals("testVIN1", vin);

        } catch (SQLException e) {
            fail("Got " + e + " on getConnection(" + urlString + ")");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
