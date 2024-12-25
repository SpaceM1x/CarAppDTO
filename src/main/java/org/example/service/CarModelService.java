package org.example.service;


import org.example.dto.CarModelDTO;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface CarModelService {
    void load(String fileName);
    List<CarModelDTO> getAllCarDTOs(String brand);
    Optional<CarModelDTO> findCarById(CarModelDTO car);
    Map<String, Integer> getCarModelGroupByModel(String brand);
    Set<String> getUniqueBrands();
    List<String> findModelsByBrand(String brand);
    Map<String, Integer> groupByBrand();
}