package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CarModelServiceImplTest {

    private CarModelServiceImpl carModelService;
    private final String testFilePath = "src/test/resources/cars_test.csv";

    @BeforeEach
    void initializeTestEnvironment() throws IOException {
        prepareCsvFile();
        carModelService = new CarModelServiceImpl();
        carModelService.load(testFilePath);
    }

    private void prepareCsvFile() throws IOException {
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(testFilePath))) {
            csvWriter.write("id,brand,model,countryOrigin,countryCode\n");
            csvWriter.write("1,Toyota,Corolla,Japan,JP\n");
            csvWriter.write("2,Ford,Focus,USA,US\n");
            csvWriter.write("3,Honda,Civic,Japan,JP\n");
            csvWriter.write("4,Audi,A4,Germany,DE\n");
            csvWriter.write("5,Toyota,Camry,Japan,JP\n");
        }
    }

    @Test
    void verifyUniqueBrandRetrieval() {
        Set<String> brands = carModelService.getUniqueBrands();

        assertEquals(4, brands.size());
        assertTrue(brands.containsAll(Arrays.asList("Toyota", "Ford", "Honda", "Audi")));
    }

    @Test
    void verifyModelSearchByBrand() {
        List<String> toyotaModels = carModelService.findModelsByBrand("Toyota");
        assertEquals(2, toyotaModels.size());
        assertTrue(toyotaModels.containsAll(Arrays.asList("Corolla", "Camry")));

        List<String> hondaModels = carModelService.findModelsByBrand("Honda");
        assertEquals(1, hondaModels.size());
        assertTrue(hondaModels.contains("Civic"));

        List<String> nonexistentModels = carModelService.findModelsByBrand("Unknown");
        assertTrue(nonexistentModels.isEmpty());
    }

    @Test
    void verifyBrandGrouping() {
        Map<String, Integer> groupedBrands = carModelService.groupByBrand();

        assertEquals(4, groupedBrands.size());
        assertEquals(2, groupedBrands.get("Toyota"));
        assertEquals(1, groupedBrands.get("Ford"));
        assertEquals(1, groupedBrands.get("Honda"));
        assertEquals(1, groupedBrands.get("Audi"));
    }

    @Test
    void removeTestFile() throws IOException {
        Files.deleteIfExists(Paths.get(testFilePath));
    }
}
