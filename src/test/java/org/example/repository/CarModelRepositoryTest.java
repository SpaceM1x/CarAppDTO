package org.example.repository;

import org.example.database.DatabaseConnection;
import org.example.database.DatabaseSetup;
import org.example.entity.CarModelEntity;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarModelRepositoryTest {

    private Connection connection;
    private CarModelRepository carModelRepository;

    @BeforeAll
    public void initializeEnvironment() throws SQLException {
        connection = DatabaseConnection.getConnection();
        new DatabaseSetup().setupDatabase();
        carModelRepository = new CarModelRepository();
    }

    @BeforeEach
    public void purgeTables() throws SQLException {
        executeUpdate("DELETE FROM CarModel");
        executeUpdate("DELETE FROM Dealership");
        executeUpdate("DELETE FROM Car");
    }

    @Test
    public void shouldAddCarModelToDatabase() throws SQLException {
        CarModelEntity testModel = new CarModelEntity(1, "Toyota", "Camry", "Japan", "JP");
        carModelRepository.create(testModel);

        try (PreparedStatement query = connection.prepareStatement("SELECT * FROM CarModel WHERE id = ?")) {
            query.setInt(1, 1);
            try (ResultSet result = query.executeQuery()) {
                assertTrue(result.next());
                assertEquals("Toyota", result.getString("brand"));
            }
        }
    }

    @Test
    public void shouldRetrieveAllCarModels() throws SQLException {
        List<CarModelEntity> modelsToInsert = List.of(new CarModelEntity(1, "Toyota", "Camry", "Japan", "JP"));
        modelsToInsert.forEach(carModelRepository::create);

        List<CarModelEntity> retrievedModels = carModelRepository.findAll();

        assertEquals(modelsToInsert.size(), retrievedModels.size());
        assertEquals(modelsToInsert.get(0).getBrand(), retrievedModels.get(0).getBrand());
    }

    @Test
    public void shouldFindCarModelById() throws SQLException {
        CarModelEntity testModel = new CarModelEntity(1, "Toyota", "Camry", "Japan", "JP");
        carModelRepository.create(testModel);

        Optional<CarModelEntity> foundModel = carModelRepository.findById(1);

        assertTrue(foundModel.isPresent());
        assertEquals("Toyota", foundModel.get().getBrand());
    }

    @Test
    public void shouldModifyExistingCarModel() throws SQLException {
        CarModelEntity testModel = new CarModelEntity(1, "Toyota", "Camry", "Japan", "JP");
        carModelRepository.create(testModel);

        testModel.setBrand("Honda");
        carModelRepository.update(testModel);

        Optional<CarModelEntity> updatedModel = carModelRepository.findById(1);
        assertTrue(updatedModel.isPresent());
        assertEquals("Honda", updatedModel.get().getBrand());
    }

    @Test
    public void shouldRemoveCarModelById() throws SQLException {
        CarModelEntity testModel = new CarModelEntity(1, "Toyota", "Camry", "Japan", "JP");
        carModelRepository.create(testModel);

        carModelRepository.delete(1);

        Optional<CarModelEntity> deletedModel = carModelRepository.findById(1);
        assertFalse(deletedModel.isPresent());
    }

    @AfterAll
    public void cleanupEnvironment() throws SQLException {
        resetDatabase();

        if (connection != null) {
            connection.close();
        }
    }

    private void executeUpdate(String sql) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    private void resetDatabase() throws SQLException {
        try (PreparedStatement resetStmt = connection.prepareStatement(
                "SET REFERENTIAL_INTEGRITY FALSE; DROP ALL OBJECTS; SET REFERENTIAL_INTEGRITY TRUE;")) {
            resetStmt.executeUpdate();
        }
    }
}
