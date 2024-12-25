package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void setupDatabase() {
        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement()
        ) {

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS CarModel (" +
                            "id INT PRIMARY KEY, " +
                            "brand VARCHAR(255) NOT NULL, " +
                            "model VARCHAR(255) NOT NULL, " +
                            "countryOrigin VARCHAR(255), " +
                            "countryCode VARCHAR(10));"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Dealership (" +
                            "name VARCHAR(255) PRIMARY KEY);"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Car (" +
                            "id INT PRIMARY KEY, " +
                            "carModelId INT, " +
                            "dealershipName VARCHAR(255), " +
                            "state VARCHAR(50), " +
                            "configuration VARCHAR(255), " +
                            "color VARCHAR(50), " +
                            "price DOUBLE, " +
                            "FOREIGN KEY (carModelId) REFERENCES CarModel(id), " +
                            "FOREIGN KEY (dealershipName) REFERENCES Dealership(name));"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
