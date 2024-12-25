package org.example.service;

import com.opencsv.exceptions.CsvValidationException;
import org.example.dto.CarModelDTO;
import org.example.entity.CarModelEntity;
import org.example.mapper.CarMapper;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CarModelServiceImpl implements CarModelService {
    private final List<CarModelDTO> carModelList = new ArrayList<>();
    private final CarMapper carMapper = CarMapper.INSTANCE;

    @Override
    public void load(String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] values;

            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                if (values.length < 5) {
                    System.err.println("Данные в строке неполные: " + String.join(", ", values));
                    continue;
                }

                try {
                    long id = Long.parseLong(values[0]);
                    String brand = values[1];
                    String model = values[2];
                    String countryOrigin = values[3];
                    String countryCode = values[4];

                    CarModelDTO carModel = new CarModelDTO(id, brand, model, countryOrigin, countryCode);
                    carModelList.add(carModel);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка преобразования числа: " + String.join(", ", values));
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public List<CarModelDTO> getAllCarDTOs(String brand) {
        return carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarModelDTO> findCarById(CarModelDTO car) {
        return carModelList.stream()
                .filter(c -> c.getId() == car.getId())
                .findFirst();
    }

    @Override
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        Map<String, Integer> modelCountMap = new HashMap<>();
        carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .forEach(car -> modelCountMap.put(car.getModel(), modelCountMap.getOrDefault(car.getModel(), 0) + 1));
        return modelCountMap;
    }

    @Override
    public Set<String> getUniqueBrands() {
        return carModelList.stream()
                .map(CarModelDTO::getBrand)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> findModelsByBrand(String brand) {
        return carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .map(CarModelDTO::getModel)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> groupByBrand() {
        Map<String, Integer> brandCountMap = new HashMap<>();
        carModelList.forEach(car -> brandCountMap.put(car.getBrand(), brandCountMap.getOrDefault(car.getBrand(), 0) + 1));
        return brandCountMap;
    }

    public CarModelEntity convertToEntity(CarModelDTO carModelDTO) {
        return carMapper.carModelDTOToCarModelEntity(carModelDTO);
    }

    public CarModelDTO convertToDTO(CarModelEntity carModelEntity) {
        return carMapper.carModelEntityToCarModelDTO(carModelEntity);
    }

    public List<CarModelEntity> convertAllToEntities() {
        return carModelList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public List<CarModelDTO> convertAllToDTOs(List<CarModelEntity> carModelEntities) {
        return carModelEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
