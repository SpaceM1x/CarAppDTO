package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.DealerCenterDTO;
import org.example.dto.DealershipDTO;
import org.example.dto.CarModelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerServiceTest {

    private DealerService dealerService;
    private DealerCenterDTO dealerCenter;
    private DealershipDTO dealership;

    @BeforeEach
    public void initializeEnvironment() {
        dealerService = new DealerService();
        dealerCenter = new DealerCenterDTO(1L);
        dealership = new DealershipDTO("BMW Auto");
    }

    @Test
    public void verifyMultithreadedProcessing() throws InterruptedException {
        // Подготовка тестовых данных: создаем список автомобилей
        List<CarDTO> cars = generateCarList(3000);

        // Разбиваем список на три части для потоков
        List<CarDTO> firstBatch = cars.subList(0, 1000);
        List<CarDTO> secondBatch = cars.subList(1000, 2000);
        List<CarDTO> thirdBatch = cars.subList(2000, 3000);

        // Создаем потоки для обработки
        Thread threadA = new Thread(() -> dealerService.processCars(dealerCenter, firstBatch));
        Thread threadB = new Thread(() -> dealerService.processCars(dealerCenter, secondBatch));
        Thread threadC = new Thread(() -> dealerService.processCars(dealerCenter, thirdBatch));

        // Засекаем время выполнения
        long start = System.currentTimeMillis();

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        long finish = System.currentTimeMillis();

        // Вывод результатов
        System.out.println("Processing time: " + (finish - start) + " ms");

        int showroomCarCount = dealerCenter.getCountShowroomCars();
        System.out.println("Cars in showroom: " + showroomCarCount);

        // Проверяем, что количество автомобилей соответствует ожиданиям
        assertEquals(3000, showroomCarCount, "Expected 3000 cars in showroom");
    }

    private List<CarDTO> generateCarList(int totalCars) {
        List<CarDTO> carList = new ArrayList<>();
        for (int index = 0; index < totalCars; index++) {
            CarModelDTO model = new CarModelDTO(index, "BMW", "X5", "GERMANY", "Year " + (2020 + (index % 5)));
            String color = "Black";
            CarDTO car = new CarDTO(index, model, dealership, "New", "SuperPremium", color, 10000.0);
            carList.add(car);
        }
        return carList;
    }
}
