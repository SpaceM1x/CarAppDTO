package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.CarModelDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.CarEntity;
import org.example.mapper.CarMapper;

import java.util.Random;

public class CarService {

    private final Random random = new Random();
    private final CarMapper carMapper;

    public CarService(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    public CarDTO createCarWithRandomValues(int id, DealershipDTO dealership) {
        long startTime = System.nanoTime();

        String[] states = {"Свободен", "На пути", "Доступен", "Продан", "Зарезервирован"};
        String[] configurations = {"Стандарт", "Особая версия", "Спорт"};
        String[] colors = {"Алый", "Лазурный", "Угольный", "Снежный", "Грифельный"};

        CarModelDTO carModel = new CarModelDTO(
                id,
                "Марка " + (random.nextInt(10) + 1),
                "Модель " + (random.nextInt(10) + 1),
                "Страна " + (random.nextInt(5) + 1),
                "Код " + (random.nextInt(5) + 1)
        );

        String state = states[random.nextInt(states.length)];
        String configuration = configurations[random.nextInt(configurations.length)];
        String color = colors[random.nextInt(colors.length)];
        double price = 50000 + (random.nextDouble() * 50000);

        CarDTO car = new CarDTO(id, carModel, dealership, state, configuration, color, price);

        long endTime = System.nanoTime();
        System.out.println("Время выполнения метода создания автомобиля: " + (endTime - startTime) + " нс");

        return car;
    }

    public void saveCar(CarDTO carDTO) {
        CarEntity carEntity = carMapper.carDTOToCarEntity(carDTO);
        System.out.println("Автомобиль успешно сохранён: " + carEntity);
    }

    public void measurePerformanceForMultipleCreations(int numberOfCreations, DealershipDTO dealership) {
        long startTime = System.nanoTime();
        for (int i = 0; i < numberOfCreations; i++) {
            CarDTO car = createCarWithRandomValues(i, dealership);
            saveCar(car);
        }
        long endTime = System.nanoTime();
        System.out.println("Общее время выполнения массового создания автомобилей: " + (endTime - startTime) + " нс");
    }
}
