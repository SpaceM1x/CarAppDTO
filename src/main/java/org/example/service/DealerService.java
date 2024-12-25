package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.DealerCenterDTO;
import org.example.dto.CarModelDTO;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class DealerService {

    private final Set<String> validColors = new HashSet<>();
    private final Set<String> validBrands = new HashSet<>();
    private final Set<String> validModels = new HashSet<>();
    private final Set<String> validConfigurations = new HashSet<>();

    public DealerService() {
        validColors.add("Чёрный");
        validBrands.add("BMW");
        validModels.add("X5");
        validConfigurations.add("СуперПремиум");
    }

    public void processCars(DealerCenterDTO dealer, List<CarDTO> carList) {
        for (CarDTO car : carList) {
            performIntensiveProcessing(car);

            if (shouldAddToShowroom(car)) {
                dealer.addCarToShowroom(car);
            }
        }
    }

    private void performIntensiveProcessing(CarDTO car) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Обработка была прервана для автомобиля: " + car);
        }
    }

    private boolean shouldAddToShowroom(CarDTO car) {
        CarModelDTO carModel = car.getCarModel();
        return validColors.contains(car.getColor()) &&
                validBrands.contains(carModel.getBrand()) &&
                validModels.contains(carModel.getModel()) &&
                validConfigurations.contains(car.getConfiguration());
    }
}
