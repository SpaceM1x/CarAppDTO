package org.example.dto;

import org.example.service.DealerService;
import java.util.ArrayList;
import java.util.List;

public class DealerCenterDTO {

    private Long id;
    private Integer countShowroomCars;
    private Integer countCars;
    private List<CarDTO> cars;
    private List<CarDTO> carsInShowroom;

    public DealerCenterDTO(Long id) {
        this.id = id;
        this.countShowroomCars = 0;
        this.countCars = 0;
        this.cars = new ArrayList<>();
        this.carsInShowroom = new ArrayList<>();
    }

    public synchronized void addCar(CarDTO car) {
        cars.add(car);
        countCars++;
    }

    public synchronized void addCarToShowroom(CarDTO car) {
        carsInShowroom.add(car);
        countShowroomCars++;
    }

    public Long getId() {
        return id;
    }

    public Integer getCountShowroomCars() {
        return countShowroomCars;
    }

    public Integer getCountCars() {
        return countCars;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public List<CarDTO> getCarsInShowroom() {
        return carsInShowroom;
    }
}
