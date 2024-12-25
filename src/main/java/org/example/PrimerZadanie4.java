package org.example;

import org.example.database.DatabaseConnection;
import org.example.database.DatabaseSetup;
import org.example.dto.CarModelDTO;
import org.example.repository.CarModelRepository;
import org.example.service.CarModelService;
import org.example.service.CarModelServiceImpl;
import org.example.service.CarService;
import org.example.mapper.CarMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PrimerZadanie4 {

    public static void main(String[] args) {
        // Инициализация сервисов
        CarModelService carModelService = new CarModelServiceImpl();
        CarMapper carMapper = CarMapper.INSTANCE;
        CarService carService = new CarService(carMapper);

        // Загрузка данных
        String filePath = "src\\main\\resources\\cars.csv";
        carModelService.load(filePath);

        // Пример 1: Все автомобили марки Toyota
        System.out.println("[Пример 1] Список всех автомобилей марки Toyota:");
        List<CarModelDTO> toyotaCars = carModelService.getAllCarDTOs("Toyota");
        if (toyotaCars.isEmpty()) {
            System.out.println("Автомобили марки Toyota не найдены.");
        } else {
            toyotaCars.forEach(System.out::println);
        }

        // Пример 2: Поиск автомобиля по ID
        System.out.println("\n[Пример 2] Поиск автомобиля по идентификатору:");
        CarModelDTO carToFind = new CarModelDTO(3L, null, null, null, null); // ID = 3
        Optional<CarModelDTO> foundCar = carModelService.findCarById(carToFind);
        foundCar.ifPresentOrElse(
                car -> System.out.println("Автомобиль найден: " + car),
                () -> System.out.println("Автомобиль с таким идентификатором не найден.")
        );

        // Пример 3: Группировка автомобилей по моделям (марка BMW)
        System.out.println("\n[Пример 3] Группировка моделей автомобилей марки BMW:");
        Map<String, Integer> bmwModelsCount = carModelService.getCarModelGroupByModel("BMW");
        bmwModelsCount.forEach((model, count) ->
                System.out.println("Модель: " + model + ", Количество: " + count)
        );

        // Пример 4: Уникальные марки автомобилей
        System.out.println("\n[Пример 4] Уникальные марки автомобилей:");
        Set<String> uniqueCarBrands = carModelService.getUniqueBrands();
        uniqueCarBrands.forEach(System.out::println);

        // Пример 5: Модели автомобилей заданной марки
        String targetBrand = "Toyota";
        System.out.println("\n[Пример 5] Список моделей для марки " + targetBrand + ":");
        List<String> toyotaModels = carModelService.findModelsByBrand(targetBrand);
        if (toyotaModels.isEmpty()) {
            System.out.println("Для марки " + targetBrand + " модели отсутствуют.");
        } else {
            toyotaModels.forEach(System.out::println);
        }

        // Пример 6: Группировка автомобилей по маркам
        System.out.println("\n[Пример 6] Группировка автомобилей по маркам:");
        Map<String, Integer> brandGrouping = carModelService.groupByBrand();
        brandGrouping.forEach((brand, count) ->
                System.out.println("Марка: " + brand + ", Количество: " + count)
        );
    }
}
