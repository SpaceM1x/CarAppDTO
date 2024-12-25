package org.example.dto;

import java.util.ArrayList;
import java.util.List;

public class DealershipDTO {

    private String name;
    private List<CarDTO> cars;

    public DealershipDTO(String name) {
        this.name = name;
        this.cars = new ArrayList<>();
    }

    public void addCar(CarDTO car) {
        cars.add(car);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "DealershipDTO{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}
