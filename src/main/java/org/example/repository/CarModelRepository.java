package org.example.repository;

import org.example.database.DatabaseConnection;
import org.example.entity.CarModelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarModelRepository {

    public void create(CarModelEntity carModel) {
        String sql = "INSERT INTO CarModel (id, brand, model, countryOrigin, countryCode) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carModel.getId());
            stmt.setString(2, carModel.getBrand());
            stmt.setString(3, carModel.getModel());
            stmt.setString(4, carModel.getCountryOrigin());
            stmt.setString(5, carModel.getCountryCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to add the car model to the database: " + e.getMessage());
        }
    }

    public List<CarModelEntity> findAll() {
        List<CarModelEntity> carModels = new ArrayList<>();
        String sql = "SELECT * FROM CarModel";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CarModelEntity carModel = new CarModelEntity(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("countryOrigin"),
                        rs.getString("countryCode"));
                carModels.add(carModel);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all car models: " + e.getMessage());
        }
        return carModels;
    }

    public Optional<CarModelEntity> findById(int id) {
        String sql = "SELECT * FROM CarModel WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CarModelEntity carModel = new CarModelEntity(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("countryOrigin"),
                        rs.getString("countryCode"));
                return Optional.of(carModel);
            }
        } catch (SQLException e) {
            System.err.println("Error finding car model by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    public void update(CarModelEntity carModel) {
        String sql = "UPDATE CarModel SET brand = ?, model = ?, countryOrigin = ?, countryCode = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModel());
            stmt.setString(3, carModel.getCountryOrigin());
            stmt.setString(4, carModel.getCountryCode());
            stmt.setInt(5, carModel.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating car model record: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM CarModel WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting car model record: " + e.getMessage());
        }
    }
}
